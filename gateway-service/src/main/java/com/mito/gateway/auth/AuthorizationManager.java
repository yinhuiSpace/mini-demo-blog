package com.mito.gateway.auth;

import cn.hutool.json.JSONUtil;
import com.mito.gateway.constants.RoleConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {

        String path = context.getExchange().getRequest().getURI().getPath();
        List<String> list = stringRedisTemplate.opsForList().range(RoleConstants.PERMISSION_PATH + path, 0, -1);

        if (list==null||list.isEmpty()){
            return Mono.just(new AuthorizationDecision(false));
        }

        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(new Function<GrantedAuthority, String>() {
            @Override
            public String apply(GrantedAuthority grantedAuthority) {
                return grantedAuthority.getAuthority();
            }
        }).collect(Collectors.toList());





        return Mono.just(new AuthorizationDecision(false));
    }
}
