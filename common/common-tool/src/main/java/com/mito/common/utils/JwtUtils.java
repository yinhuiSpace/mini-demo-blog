package com.mito.common.utils;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.util.Date;

/**
 * jwt工具类
 * */
public class JwtUtils {

    /**
     * 维护一个签名算法和密钥对象
     */
//    private static final JWTSigner signer = JWTSignerUtil.hs512("afsffffffffffffffffffffffsfsfafsafafafafafafafsfsfddfdjksdfjksjfiajfijaojfafekfjijfoef".getBytes());

    /**
     * 创建token，根据负载
     */
    public static String createToken(String key, Object data, long expires) {

        JWT jwt = JWT.create();

        byte[] bytes = "afsfff".getBytes();

//        JWTSigner signer = JWTSignerUtil.hs256(bytes);

//        jwt.setSigner(signer);
        jwt.setIssuer("root");
        jwt.setKey(bytes);

        jwt.setPayload(key, data);

        jwt.setExpiresAt(new Date(System.currentTimeMillis() + expires * 1000L));

        return jwt.sign();
    }

    /**
     * 解析负载
     */
    public static <T> T parse(String token, Class<T> requiredType) {

        return ((JSONObject) JWT.of(token).getPayload("data")).toBean(requiredType);
    }

    public static Object parse(String token,String key){

        JWT jwt = JWT.of(token);
        Object value = jwt.getPayload(key);

        return value;
    }
    /**
     * 校验令牌的有效性
     */
    public static boolean validate(String token) {

        try {
            JWTValidator jwtValidator = JWTValidator.of(token);

            jwtValidator.validateAlgorithm(null);
            jwtValidator.validateDate();

            return true;
        } catch (ValidateException e) {
            return false;
        }

    }

    /**
     * 解析成jwt对象
     */
    public static JWT validateAndParse(String token) {
        if (!validate(token)) {
            return null;
        }
        return JWT.of(token);
    }
}

