package com.asto.a91recyclingtreasurepad.util

import android.content.Context
import android.content.pm.PackageManager
import com.asto.a91recyclingtreasurepad.mvp.view.MainActivity

class UpdateUtil {

    fun MainActivity.update() {
    }
}

/**
 * 获取软件版本
 */
fun getLocalVersion(ctx: Context): Int {
    var localVersion = 0
    try {
        val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
        localVersion = packageInfo.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return localVersion
}

/**
 * 获取本地软件版本号
 */
fun getLocalVersionNo(ctx: Context): String {
    var localVersion = ""
    try {
        val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
        localVersion = packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return localVersion
}