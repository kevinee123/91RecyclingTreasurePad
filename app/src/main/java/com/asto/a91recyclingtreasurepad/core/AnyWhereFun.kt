package com.asto.a91recyclingtreasurepad.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 获取IMEI
 * slotId ： 单卡穿0 双卡手机传0,1
 */
fun Context.getIMEI(slotId: Int): String {
    try {
        val manager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val method = manager.javaClass.getMethod("getImei", Int::class.javaPrimitiveType)
        return method.invoke(manager, slotId) as String
    } catch (e: Exception) {
        return ""
    }

}

fun Context.getIMEI(): String {
    return getIMEI(0)
}

/**
 * 隐藏键盘
 */
fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm != null) {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * 获取手机序列号
 *
 * @return 手机序列号
 */
@SuppressLint("PrivateApi")
fun getSerialNumber(): String {
    var serial = ""
    try {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            //8.0+
            serial = Build.SERIAL
        } else {
            //8.0-
            val c = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)
            serial = get.invoke(c, "ro.serialno") as String
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return serial
}
