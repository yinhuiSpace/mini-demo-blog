package com.mito.common.aspect;

import com.mito.common.annotations.WebLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Pointcut("@annotation(com.mito.common.annotations.WebLog)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object webLogInfo(ProceedingJoinPoint joinPoint)throws Throwable{

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        //获取注解
        WebLog webLog = method.getDeclaredAnnotation(WebLog.class);
        String businessName = webLog.businessName();
        String typeName = methodSignature.getDeclaringTypeName();
        String methodName = method.getName();

        Object result;
        try {
            log.info("=============Start=============");
            log.info("URL             :{}",request.getRequestURL());
            log.info("BusinessName    :{}",businessName);
            log.info("Http Method     :{}",request.getMethod());
            log.info("Class Method    :{}-{}",typeName,methodName);
            log.info("IP              :{}",request.getRemoteHost());
            log.info("request args    :{}", Arrays.toString(joinPoint.getArgs()));
            result = joinPoint.proceed();
            log.info("response body   :{}",result);
        } finally {
            log.info("==============End=============="+System.lineSeparator());
        }

        return result;

    }

}
