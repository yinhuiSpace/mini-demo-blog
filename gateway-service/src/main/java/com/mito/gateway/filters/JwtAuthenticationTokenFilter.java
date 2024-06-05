package com.mito.gateway.filters;

import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.mito.common.utils.JwtUtils;
import com.mito.gateway.po.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter implements GlobalFilter, Ordered {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取token及其载荷
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (path.contains("login")||path.contains("register")){
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");
        if (!StringUtils.hasText(token)){
            response.setStatusCode(HttpStatusCode.valueOf(401));
            return response.setComplete();

        }
        String userId = JwtUtils.parse(token, "userId").toString();

        //缓存到请求域
        exchange = exchange.mutate()
                .request(
                        request.mutate().header("userId",userId).build()
                ).build();

//        //从redis中获取用户信息
//        String loginUserDetailsJson = stringRedisTemplate.opsForValue().get("bloglogin:" + userId);
//        if (!StringUtils.hasText(loginUserDetailsJson)){
//            response.setStatusCode(HttpStatusCode.valueOf(403));
//            return  response.setComplete();
//        }
//
//        LoginUserDetails loginUserDetails = JSONUtil.toBean(loginUserDetailsJson, LoginUserDetails.class);

        //缓存到当前线程
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, null);
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
