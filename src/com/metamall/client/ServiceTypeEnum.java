package com.metamall.client;

/**
 * <p>请求服务器类型.</p>
 *
 * @author Magic Joey
 * @version ServiceTypeEnum.java 1.0 Created@2016-04-23 14:38 $
 */
public enum ServiceTypeEnum {
    REGISTER("register", "注册", "api/register", "POST"),
    LOGIN("login", "登陆", "api/login", "POST"),
    SMS("sms", "短信", "api/sms", "POST"),
    ;


    private String code;

    private String description;

    private String url;

    private String method;

    ServiceTypeEnum(String code, String description, String url, String method) {
        this.code = code;
        this.description = description;
        this.url = url;
        this.method = method;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public static ServiceTypeEnum getByCode(String code) {
        for (ServiceTypeEnum service : values()) {
            if (service.getCode().equals(code)) {
                return service;
            }
        }
        return null;
    }
}
