package com.asto.a91recyclingtreasurepad.net.update;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call call, Response response) {
        if (response.body() != null) {
            Gson gson = new Gson();

            try {
                JSONObject jsonObject = new JSONObject(gson.toJson(response.body()));
                onSuccess(call, (T) response.body());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (response.errorBody() != null) {
//            try {
//                if (TextUtils.isEmpty(response.errorBody().string())){
            onError(String.format("服务端错误 code：%d,提示：", response.code()) + response.message());
//                }else {
//                    Logger.e(response.errorBody().string());
//                    onError(response.errorBody().string());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            onError("未知异常");
        }
    }

    @Override
    public void onFailure(Call call, Throwable throwable) {
        onError("连接异常");
    }

    public abstract void onSuccess(Call call, T body);

    public abstract void onError(String error);

    public abstract void onAutoLoginFailed();

}
