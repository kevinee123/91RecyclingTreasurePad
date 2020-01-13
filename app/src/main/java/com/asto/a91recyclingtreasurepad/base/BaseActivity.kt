package com.asto.a91recyclingtreasurepad.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.djkg.widget.defaultPopupDialog.DefaultPopupDialog
import com.example.djkg.widget.defaultPopupDialog.OnCancelListener
import com.example.djkg.widget.defaultPopupDialog.OnConfirmListener
import com.asto.a91recyclingtreasurepad.R
import com.asto.a91recyclingtreasurepad.net.ProgressDialogHandler
import com.asto.a91recyclingtreasurepad.util.ChangeStatusBarDark
import com.jiezheng.wms_android.widget.customToast.CustomToast
import com.umeng.analytics.MobclickAgent
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by zj on 17-10-30.
 * 基类Activity的封装
 */

abstract class BaseActivity<T : BasePresenter<BaseView>> : AppCompatActivity(), BaseView, EasyPermissions.PermissionCallbacks {

    protected open var mPresenter: T? = null
    protected open var mContext: Activity? = null

    private var loadDialog: ProgressDialogHandler? = null

//    abstract val  StatusBarUtil: Any

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        //        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        //锁定竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

//        val window = window
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            //透明导航栏
//            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //透明状态栏/导航栏
//            window.statusBarColor = Color.TRANSPARENT
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏/导航栏
            //            window.getStatusBarColor() = Color.TRANSPARENT;
            //            Window window = getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = resources.getColor(R.color.blue_right)

        }

        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        initFontScale()
        mContext = this
////     StatusBarUtil.setColor(mContext, Color.parseColor("#1886e3"))//设置状态栏背景颜色为appbg
//        StatusBarUtil.setTransparent(mContext)
//         StatusBarCompat.compat(mContext,Color.parseColor("#1886e3"))

        createPresenter()

        mPresenter?.attachView(this)

        loadDialog = ProgressDialogHandler(this, null, true)

        initEventAndData()

    }


    protected abstract fun getLayout(): Int

    protected abstract fun initEventAndData()

    protected abstract fun createPresenter()

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        //showProgressDialog()
    }

    override fun closeLoading() {
        //dismissProgressDialog()
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    private fun initFontScale() {
        val configuration = resources.configuration
        configuration.fontScale = 1.0F
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }

    override fun beginActivity(goToClass: Class<*>, bundle: Bundle, requestCode: Int) {

        var intent = Intent(this, goToClass)
        intent.putExtras(bundle)

        if (requestCode == -1000) {
            startActivity(intent)
        } else {
            startActivityForResult(intent, requestCode)
        }
    }

    fun setStatusBarDark() {
        try {
            ChangeStatusBarDark.setDark(window, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setStatusBarWhite() {
        try {
            ChangeStatusBarDark.setWhite(window, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    open fun backbtn(v: View) {
        this.finish()
    }

    //默认图片的弹框
    override fun showDialog(msg: String, onConfirmListener: OnConfirmListener) {
        val popupDialog = DefaultPopupDialog(this@BaseActivity)
        popupDialog.setMsg(msg)
        popupDialog.setOnConfirmListener(onConfirmListener)
        popupDialog.show()
    }

    //非默认图片的弹框
    override fun showDialog(r: Int, msg: String, onConfirmListener: OnConfirmListener) {
        val popupDialog = DefaultPopupDialog(this@BaseActivity)
        popupDialog.setImage(r)
        popupDialog.setMsg(msg)
        popupDialog.setOnConfirmListener(onConfirmListener)
        popupDialog.show()
    }

    //可设置按钮文字
    override fun showDialog(msg: String, cancelText: String, confirmText: String,
                            onCancelListener: OnCancelListener?, onConfirmListener: OnConfirmListener?) {
        val popupDialog = DefaultPopupDialog(this@BaseActivity)
        if ("" == cancelText) {
            popupDialog.setBtnCancelVisibility(false)
        } else {
            popupDialog.setBtnCancelText(cancelText)
        }
        popupDialog.setBtnOkText(confirmText)
        popupDialog.setMsg(msg)
        popupDialog.setOnConfirmListener(onConfirmListener)
        popupDialog.setOnCancelListener(onCancelListener)
        popupDialog.show()
    }

    //可设置按钮文字 图片
    override fun showDialog(r: Int, msg: String, cancelText: String, confirmText: String,
                            onCancelListener: OnCancelListener?, onConfirmListener: OnConfirmListener?) {
        val popupDialog = DefaultPopupDialog(this@BaseActivity)
        popupDialog.setImage(r)
        popupDialog.setBtnCancelText(cancelText)
        popupDialog.setBtnOkText(confirmText)
        popupDialog.setMsg(msg)
        popupDialog.setOnConfirmListener(onConfirmListener)
        popupDialog.setOnConfirmListener(onConfirmListener)
        popupDialog.show()
    }

    //自定义的toast
    override fun showCustomToast(msg: String) {
        CustomToast().showToast(this, msg)
    }

    private fun showProgressDialog() {
        loadDialog?.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)?.sendToTarget()

    }

    private fun dismissProgressDialog() {
        loadDialog?.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)?.sendToTarget()
        loadDialog = null
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.i("EasyPermissions", "获取成功的权限$perms")
    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }
}
