package com.mito.common.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class AutoFieldFillConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", Timestamp.valueOf(LocalDateTime.now()),metaObject);
        setFieldValByName("updateTime",Timestamp.valueOf(LocalDateTime.now()),metaObject);

        setFieldValByName("createBy",Long.parseLong(getUserId()),metaObject);
        setFieldValByName("updateBy",Long.parseLong(getUserId()),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",Timestamp.valueOf(LocalDateTime.now()),metaObject);

        String userId = getUserId();
        if (userId!=null){
            setFieldValByName("updateBy",Long.parseLong(userId),metaObject);
        }else {
            setFieldValByName("updateBy","",metaObject);
        }

    }

    private static String getUserId(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String userId = null;
        if (requestAttributes!=null) {
            HttpServletRequest request = requestAttributes.getRequest();

            userId = request.getHeader("userId");
            if (!StringUtils.hasText(userId)){
                userId="-1";
            }
        }
        return userId;
    }
}
