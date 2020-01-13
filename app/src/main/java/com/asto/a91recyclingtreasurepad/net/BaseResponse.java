package com.asto.a91recyclingtreasurepad.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zj on 2018/1/12.
 * 数据返回
 */

public class BaseResponse<T> {
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;

}
