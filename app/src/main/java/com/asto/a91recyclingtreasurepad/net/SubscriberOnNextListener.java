package com.asto.a91recyclingtreasurepad.net;


public interface SubscriberOnNextListener {
    void onNext(BaseResponse baseResponse, int requestCode);
    void err(BaseResponse baseResponse, int requestCode);
}
