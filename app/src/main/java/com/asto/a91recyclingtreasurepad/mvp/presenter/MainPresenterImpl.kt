package com.asto.a91recyclingtreasurepad.mvp.presenter

import android.app.Activity
import com.asto.a91recyclingtreasurepad.base.BaseContract
import com.asto.a91recyclingtreasurepad.base.BaseView
import com.asto.a91recyclingtreasurepad.bean.*
import com.asto.a91recyclingtreasurepad.net.*
import java.io.File

/**
 * Created by zj on 2018/7/3.
 * is use for: 过磅界面 p层
 */
class MainPresenterImpl:BaseContract.MainAcPresenter {

    //自动更新
    override fun updateakp() {
//        RetrofitAPIManager.getInstance()
//                .updateakp(ProgressSubscriber(mListener1, mView as Activity?, 1, 6))
    }

    //过磅
    override fun insertStorage(cardNumber: String, company_id: String, group_id: String, weight_num: String,equipment_id : String) {
        RetrofitAPIManager.getInstance()
                .insertStorage(ProgressSubscriber(mListener1, mView as Activity?, 1, 2), cardNumber,
                        company_id,group_id,weight_num,equipment_id)
    }

    //设备码登录
    override fun loginByDeviceId(deviceId: String) {
        RetrofitAPIManager.getInstance()
                .loginByDeviceId(ProgressSubscriber(mListener1, mView as Activity?, 1, 1), deviceId)
    }

    private val mListener1 = object : SubscriberOnNextListener1 {
        override fun err(baseResponse: BaseResponse<*>?, requestCode: Int) {
            when(requestCode){
                1 ->{
                    mView?.showToast(baseResponse?.msg.toString())//登录失败toast
                }
                2 ->{
                    mView?.setInsertStorageErr(baseResponse?.msg.toString(), baseResponse?.code!!)//过磅报错信息
                }
            }
        }

        override fun onNext(baseResponse: BaseResponse<*>?, requestCode: Int) {
            when(requestCode){
                1 ->{
                    //登录成功
                    if(baseResponse?.data == null){
                        mView?.showToast("该设备暂未绑定分站")
                        return
                    }
                    val bean = baseResponse.data as LoginBean
                    mView?.setLoginByDeviceIdBean(bean)
                    mView?.showToast("登录成功")//登录成功toast
                }
                2 ->{
                    mView?.setInsertStorageResult()
                }
                6->{
                    val updateBean = baseResponse?.data as UpdateBean
                    mView?.setUpDateApp(updateBean)
                }
        }
    }
}
    private var mView: BaseContract.MainAcView? = null

    override fun attachView(view: BaseView?) {
        mView = view as BaseContract.MainAcView?
    }

    override fun detachView() {
        mView = null
    }
}