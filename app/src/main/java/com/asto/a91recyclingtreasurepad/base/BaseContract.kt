package com.asto.a91recyclingtreasurepad.base

import com.asto.a91recyclingtreasurepad.bean.*
import okhttp3.MultipartBody
import java.io.File

/**
 * Created by zhengjie on 17-10-30.
 * mvp模式c层
 */

class BaseContract {
    //test
    interface TestView : BaseView


    interface TestPresenter : BasePresenter<BaseView>

    //MainActivity 主页
    interface MainAcView : BaseView {
        fun setLoginByDeviceIdBean(bean: LoginBean)
//        fun setInsertStorageResult(bean: InsertStorageBean)
        fun setInsertStorageResult()
        fun setInsertStorageErr(errMsg: String, errCode: Int)
        fun setUpDateApp(bean : UpdateBean)
    }


    interface MainAcPresenter : BasePresenter<BaseView> {
        fun loginByDeviceId(deviceId: String)
        fun insertStorage(cardNumber: String, company_id: String, group_id: String, weight_num: String,equipment_id : String)
        fun updateakp()
    }

    //信息展示界面
    interface InfoView : BaseView

    interface InfoPresenter : BasePresenter<BaseView>
}