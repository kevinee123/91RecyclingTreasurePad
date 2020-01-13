package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/7/10.
 * is use for:
 */

public class InfoBean implements Serializable{
    private String content;
    private String message;

    public InfoBean(String content, String message) {
        this.content = content;
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
