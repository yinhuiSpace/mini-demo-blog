package com.mito.auth.handler;

import cn.hutool.json.JSONUtil;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf8");
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(RestResult.fail(RestResultEnum.LOGIN_ERROR)));
    }
}
