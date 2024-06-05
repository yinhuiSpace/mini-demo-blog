package com.mito.common.utils;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityContextUtil {

    public static String getUserId(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        return request.getHeader("userId");
    }
}
