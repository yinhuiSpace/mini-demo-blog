package com.mito.common.utils;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BeanCopyUtil {

    public static <T> T copyBean(Object source,Class<T> clazz){

        T result=null;

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            result = constructor.newInstance();
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
