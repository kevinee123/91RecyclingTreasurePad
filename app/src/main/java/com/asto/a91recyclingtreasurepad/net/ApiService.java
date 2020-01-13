package com.asto.a91recyclingtreasurepad.net;


import com.asto.a91recyclingtreasurepad.bean.InsertStorageBean;
import com.asto.a91recyclingtreasurepad.bean.LoginBean;
import com.asto.a91recyclingtreasurepad.bean.PrintContentBean;
import com.asto.a91recyclingtreasurepad.bean.TokenBean;
import com.asto.a91recyclingtreasurepad.bean.UpdateBean;
import com.asto.a91recyclingtreasurepad.bean.UserInfoBean;
import com.asto.a91recyclingtreasurepad.bean.WeighMessageBean;
import com.asto.a91recyclingtreasurepad.core.Constant;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 请求地址
 */

public interface ApiService {

    @POST(Constant.url.LOGIN)
    Observable<BaseResponse<UserInfoBean>> login(@Body RequestBody requestBody);

    @POST(Constant.url.LOGIN_BY_DEVICE_ID)
    Observable<BaseResponse<LoginBean>> loginByDeviceId(@Body RequestBody requestBody);

    @POST(Constant.url.UPDATE_APK)
    Observable<BaseResponse<UpdateBean>> updateakp(@Body RequestBody requestBody);

    @POST(Constant.url.INSERT_STORAGE)
    Observable<BaseResponse<String>> insertStorage(@Body RequestBody requestBody);

    @POST(Constant.url.GET_WEIGH_MESSAGE)
    Observable<BaseResponse<WeighMessageBean>> getWeighMessage(@Body RequestBody requestBody);

    /**
     * 下载文件
     * @return
     */
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

}

