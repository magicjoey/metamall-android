package com.metamall.utils;

/**
 * <p>.</p>
 *
 * @author Magic Joey
 * @version RemoteUrl.java 1.0 Created@2015-12-05 16:24 $
 */
public enum RemoteServiceEnum {


    LOGIN("login","登陆","http://localhost/vosex/index.php/api/auth/login");

    RemoteServiceEnum(String key, String description, String remoteUrl) {
        this.key = key;
        this.description = description;
        this.remoteUrl = remoteUrl;
    }

    private String key;
    private String description;
    private String remoteUrl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

}
