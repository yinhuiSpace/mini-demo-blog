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
    EMAIL_EXIST(505,"邮箱已存在"),

    UPLOAD_ERROR(506,"图片上传失败"),
    FILE_SIZE_EXCEED_LIMIT(507,"文件大小超过限制"),
    FILE_TYPE_ILLEGAL(508,"不支持该文件类型"),

    CAN_NOT_FOLLOW(509,"暂时无法关注该作者"),

    CAN_NOT_REPEAT_FOLLOW(510,"不能重复关注"),

    CAN_NOT_SELF_FOLLOW(511,"不能关注自己");

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
