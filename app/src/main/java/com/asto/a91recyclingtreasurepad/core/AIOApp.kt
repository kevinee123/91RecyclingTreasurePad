package com.asto.a91recyclingtreasurepad.core

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.DisplayMetrics
import android.view.WindowManager
import com.lzy.okgo.OkGo
import com.lzy.okserver.OkDownload
import com.umeng.commonsdk.UMConfigure
import com.vondear.rxtool.RxTool



/**
 * Created by zj on 2018/7/2.
 * is use for: 初始化app
 */
class AIOApp : MultiDexApplication() {
    //单例
    var SCREEN_WIDTH = -1
    var SCREEN_HEIGHT = -1
    var DIMEN_RATE = -1.0f
    var DIMEN_DPI = -1
    companion object {
        var instanceTmp: AIOApp? = null
        fun getInstance(): AIOApp? {
            if (instanceTmp == null) {
                instanceTmp = AIOApp()
            }
            return instanceTmp
        }
    }

    override fun onCreate() {
        super.onCreate()
        instanceTmp = this

        getScreenSize()

        RxTool.init(this)
        //初始化OkGo
        OkGo.getInstance().init(this)
        //设置下载路径
        OkDownload.getInstance().folder = filesDir.toString()
        //友盟
        UMConfigure.init(this, "5db11426570df3e68f0002d5", getSerialNumber(), UMConfigure.DEVICE_TYPE_PHONE, null)

    }

    //初始化屏幕宽高
    private fun getScreenSize() {
        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        val display = windowManager.defaultDisplay
        display.getMetrics(dm)
        DIMEN_RATE = dm.density / 1.0f
        DIMEN_DPI = dm.densityDpi
        SCREEN_WIDTH = dm.widthPixels
        SCREEN_HEIGHT = dm.heightPixels
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            val t = SCREEN_HEIGHT
            SCREEN_HEIGHT = SCREEN_WIDTH
            SCREEN_WIDTH = t
        }
    }
}