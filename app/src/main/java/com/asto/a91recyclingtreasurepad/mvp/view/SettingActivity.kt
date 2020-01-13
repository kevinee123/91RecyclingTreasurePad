package com.asto.a91recyclingtreasurepad.mvp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import cn.addapp.pickers.picker.SinglePicker
import com.asto.a91recyclingtreasurepad.R
import com.asto.a91recyclingtreasurepad.core.Constant
import com.asto.a91recyclingtreasurepad.util.ASimpleCache
import com.asto.a91recyclingtreasurepad.util.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.dialog_login.*

class SettingActivity : Activity() {
    var deviceId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mLoginCv.setOnClickListener {
            //登录设置
            if (MainActivity.isLogin) {
                Toast.makeText(this, "设备已登录,不要重复设置", Toast.LENGTH_SHORT).show()
            } else {
                deviceId = SharedPreferencesManager.getInstance().getData(this, "user", "deviceId")
                showPasswordDialog(true)
            }
        }
        mChangeServiceCv.setOnClickListener {
            //切换服务器
            showPasswordDialog(false)

        }
        mBackIv.setOnClickListener {
            finish()
        }
    }

    fun changeService() {
        val picker = SinglePicker(this, listOf("正式服务器", "测试服务器", "debug服务器"))
        picker.setCanLoop(true)//不禁用循环
        picker.setTextSize(30)
        picker.setTitleText("分站选择")
        picker.setTitleTextSize(30)
        picker.setSubmitTextSize(30)
        picker.setCancelTextSize(30)
        picker.setSubmitTextColor(resources.getColor(R.color.blue_scanner))
        picker.setCancelTextColor(resources.getColor(R.color.blue_scanner))
        picker.setWheelModeEnable(false)
        //启用权重 setWeightWidth 才起作用
        picker.setWeightEnable(true)
        picker.setWeightWidth(1f)
        picker.setItemWidth(300)
        picker.setSelectedTextColor(Color.BLACK)//前四位值是透明度
        picker.setUnSelectedTextColor(Color.GRAY)
        picker.setOnSingleWheelListener { _, _ ->

        }
        picker.setOnItemPickListener { position, _ ->
            when (position) {
                0 -> {
                    //正式服务器
                    ASimpleCache.get(this).put("httpAddress", "https://znj.zz91.com/api/")
                    SharedPreferencesManager.getInstance().putData(this, "user", "httpAddress", "https://znj.zz91.com/api/")
                }
                1 -> {
                    //测试服务器
                    ASimpleCache.get(this).put("httpAddress", "http://47.99.201.99:8071/api/")
                    SharedPreferencesManager.getInstance().putData(this, "user", "httpAddress", "http://47.99.201.99:8071/api/")
                }
                2 -> {
                    //debug服务器
                    ASimpleCache.get(this).put("httpAddress", "http://192.168.1.101:7011/api/")
                    SharedPreferencesManager.getInstance().putData(this, "user", "httpAddress", "http://192.168.1.101:7011/api/")
                }
            }
        }
        picker.show()
    }

    private fun showPasswordDialog(isLogin: Boolean) {
        val dialog = AlertDialog.Builder(this)
                .setView(R.layout.dialog_login)
                .create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        dialog.popup_title.text = "管理员登录"
        dialog.mTitleTv.text = "密码"
        dialog.etUserName.hint = "请输入管理员密码"
        dialog.btnLogin.setOnClickListener {
            if (dialog.etUserName.text.toString().trim().equals("12334")) {
                dialog.cancel()
                if (isLogin)
                    showLoginDialog()
                else
                    changeService()
            } else {
                Toast.makeText(this, "密码错误，请咨询管理员", Toast.LENGTH_LONG).show()
            }
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun showLoginDialog() {
        val dialog = AlertDialog.Builder(this)
                .setView(R.layout.dialog_login)
                .create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        if (deviceId.isNotEmpty())
            dialog.etUserName.setText(deviceId)
        dialog.btnLogin.setOnClickListener {
            deviceId = dialog.etUserName.text.toString()
            SharedPreferencesManager.getInstance().putData(this, "user", "deviceId", deviceId)
            setResult(2)
            dialog.cancel()
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show()
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.cancel()
        }
    }
}