package com.asto.a91recyclingtreasurepad.net;

import com.asto.a91recyclingtreasurepad.bean.InsertStorageBean;
import com.asto.a91recyclingtreasurepad.bean.LoginBean;
import com.asto.a91recyclingtreasurepad.bean.PrintContentBean;
import com.asto.a91recyclingtreasurepad.bean.TokenBean;
import com.asto.a91recyclingtreasurepad.bean.UpdateBean;
import com.asto.a91recyclingtreasurepad.bean.UserInfoBean;
import com.asto.a91recyclingtreasurepad.bean.WeighMessageBean;
import com.asto.a91recyclingtreasurepad.core.Constant;
import com.asto.a91recyclingtreasurepad.util.Encrypt;

import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhengjie on 2017/10/30.
 * is use for:处理请求的封装
 */

public class RetrofitAPIManager {
    ApiService mApiService;
    private String ver;
    private static RetrofitAPIManager mInstance;
    private static final int CONN_TIMEOUT = 10;//连接超时时间,单位  秒
    private static final int READ_TIMEOUT = 20;//读写超时时间,单位  秒

    private Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    RequestBody body;

    private RetrofitAPIManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.url.BASE)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(genericClient())
                .build();

        mApiService = retrofit.create(ApiService.class);
        ver = Constant.url.Ver;
    }

    public static RetrofitAPIManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitAPIManager.class) {
                mInstance = new RetrofitAPIManager();
            }
        }
        return mInstance;
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new ParamsInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
        return httpClient;
    }

    /**
     * 设备码登录
     * @param subscriber
     * @param machine_code 设备码
     */
    public void loginByDeviceId(Subscriber<BaseResponse<LoginBean>> subscriber, String machine_code) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("machine_code", machine_code);

            Observable observable = mApiService.loginByDeviceId(getRequestBody(jsonObject.toString()));
            toSubscribe(observable, subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动更新
     * @param subscriber
     */
    public void updateakp(Subscriber<BaseResponse<UpdateBean>> subscriber) {
        JSONObject jsonObject = new JSONObject();
        try {
            Observable observable = mApiService.updateakp(getRequestBody(jsonObject.toString()));
            toSubscribe(observable, subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 过磅
     * @param subscriber
     * @param code 卡号
     * @param company_id 分站id
     * @param group_id 集团id
     * @param weight_num 重量
     * @param equipment_id 设备ID
     */
    public void insertStorage(Subscriber<BaseResponse<InsertStorageBean>> subscriber, String code, String company_id,
                              String  group_id, String weight_num,String equipment_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code);
            jsonObject.put("company_id", company_id);
            jsonObject.put("group_id", group_id);
            jsonObject.put("weight_num", weight_num);
            jsonObject.put("equipment_id", equipment_id);

            Observable observable = mApiService.insertStorage(getRequestBody(jsonObject.toString()));
            toSubscribe(observable, subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取过磅情况
     * @param subscriber
     * @param iccode
     * @param company_id
     * @param group_id
     * @param eqtype
     * @param storagenum
     */
        public void getWeighMessage(Subscriber<BaseResponse<WeighMessageBean>> subscriber, String iccode, int company_id,
                                    int group_id, int eqtype, String storagenum) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("iccode", iccode);
                jsonObject.put("company_id", company_id);
                jsonObject.put("group_id", group_id);
                jsonObject.put("eqtype", eqtype);
                jsonObject.put("storagenum", storagenum);

                Observable observable = mApiService.getWeighMessage(getRequestBody(jsonObject.toString()));
                toSubscribe(observable, subscriber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    // * subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // * observeOn(): 指定 Subscriber 的回调发生在主线程
    //添加线程管理并订阅
    private void toSubscribe(Observable o, Subscriber s) {
        o.subscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNet(2*1000,2))//请求不通情况下自动重连3次
//                .retry() //无网状况下一直请求，直至网络连上
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public RequestBody getRequestBody(String str) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str);
        return body;
    }

    public ApiService getmApiService() {
        return mApiService;
    }

    public void setmApiService(ApiService mApiService) {
        this.mApiService = mApiService;
    }
}
