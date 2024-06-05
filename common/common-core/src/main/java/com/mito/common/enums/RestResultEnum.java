package com.mito.common.enums;


public enum RestResultEnum {

    SUCCESS(200, "操作成功"),
    INVALID_PARAM(400, "非法参数"),
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR(403, "无操作权限"),
    SYSTEM_ERROR(500, "系统发生错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_NUMBER_EXIST(502, "手机号已存在"),
    REQUIRE_USERNAME(503, "必须填写用户名"),
    LOGIN_ERROR(504, "用户名或密码错误"),
    EMAIL_EXIST(505,"邮箱已存在");

    Integer code;

    String message;

    RestResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
