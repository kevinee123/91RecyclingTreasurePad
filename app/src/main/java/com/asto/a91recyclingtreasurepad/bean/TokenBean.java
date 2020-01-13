package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/8/17.
 * is use for: 图片token
 */
public class TokenBean implements Serializable{
    private String token;
    private String key;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
