package com.mito.auth.handler;

import cn.hutool.json.JSONUtil;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf8");
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(RestResult.fail(RestResultEnum.NO_OPERATOR)));
    }
}
