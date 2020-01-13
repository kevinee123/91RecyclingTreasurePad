package com.asto.a91recyclingtreasurepad.net;

import android.app.Activity;
import android.widget.Toast;

import com.asto.a91recyclingtreasurepad.core.AIOApp;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by zhengjie on 2017/11/1.
 * SubscriberOnNextListener 用于处理统一返回
 * SubscriberOnNextListener1 用于处理当有前端界面判断err时调动
 * 有异常时 直接执行 onError 解析正常时则走onNext->onCompleted
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private SubscriberOnNextListener mListener;
    private SubscriberOnNextListener1 mListener1;
    private ProgressDialogHandler mHandler;
    private int requestCode;
    private final static String TAG = "errorLog";


    public ProgressSubscriber(SubscriberOnNextListener listener, Activity context, int tag, int requestCode){
        this.mListener = listener;
        this.requestCode=requestCode;
        if (tag==1){
            mHandler = new ProgressDialogHandler(context,this,true);
        }
    }

    public ProgressSubscriber(SubscriberOnNextListener1 listener, Activity context, int tag, int requestCode){
        this.mListener1 = listener;
        this.requestCode=requestCode;
        if (tag==1){
            mHandler = new ProgressDialogHandler(context,this,true);
        }
    }

    private void showProgressDialog(){
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    //统一拦截rxJava onError情况
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(AIOApp.Companion.getInstance(), "连接超时！！！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(AIOApp.Companion.getInstance(), "无法连接到服务器，请检查网络！！！", Toast.LENGTH_SHORT).show();
        }else if (e instanceof FileNotFoundException){
            Toast.makeText(AIOApp.Companion.getInstance(), "找不到指定的文件或者路径！！！", Toast.LENGTH_SHORT).show();
        }else {
            e.printStackTrace();
//            Log.e(TAG, "错误原因为：" + e);
            Toast.makeText(AIOApp.Companion.getInstance(), "错误原因为：" + e , Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        BaseResponse baseResponse= (BaseResponse) t;
            if (baseResponse.code == 0){
                if (mListener != null){
                    mListener.onNext(baseResponse,requestCode);
                }
                if (mListener1 != null){
                    mListener1.onNext(baseResponse,requestCode);
                }
            }else {
                if (mListener1 != null){
                    mListener1.err(baseResponse,requestCode);
                }
            }
        }

    @Override
    public void onCancelProgress(){
        if (!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
