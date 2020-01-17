package com.asto.a91recyclingtreasurepad.mvp.view

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.graphics.PixelFormat
import android.net.ConnectivityManager
import android.os.*
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import com.asto.a91recyclingtreasurepad.R
import com.asto.a91recyclingtreasurepad.base.BaseActivity
import com.asto.a91recyclingtreasurepad.base.BaseContract
import com.asto.a91recyclingtreasurepad.bean.*
import com.asto.a91recyclingtreasurepad.core.Constant
import com.asto.a91recyclingtreasurepad.core.getSerialNumber
import com.asto.a91recyclingtreasurepad.mvp.presenter.MainPresenterImpl
import com.asto.a91recyclingtreasurepad.net.update.UpdateService
import com.asto.a91recyclingtreasurepad.util.*
import com.asto.a91recyclingtreasurepad.widget.CustomPopwindow.BottomPopWindow
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_main.*
import org.MediaPlayer.PlayM4.Player
import pub.devrel.easypermissions.EasyPermissions
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*


/**
 * Created by zj on 2018/7/3.binder
 * is use for: 过磅界面(首页)
 */
class MainActivity : BaseActivity<MainPresenterImpl>(), BaseContract.MainAcView {


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("m_iPort", m_iPort)
        outState.putInt("m_iPort2", m_iPort2)
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        m_iPort = savedInstanceState.getInt("m_iPort")
        m_iPort2 = savedInstanceState.getInt("m_iPort2")
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")
    }

    private var serverSocket: ServerSocket? = null
    private val TAG = "main"
    private var m_iPort: Int = -1
    private var m_iPort2: Int = -1
    private var storagenum: String = ""

    private var s1: String = "" //handler 1 对应的重量
    private var popupView: View? = null//详情页弹窗
    private lateinit var popwindow: BottomPopWindow
    private var rlPopBottom: LinearLayout? = null
    private var tvPopTotal: TextView? = null
    private var tvTime1: TextView? = null
    private var tvTime2: TextView? = null
    private var btnPopCancel: Button? = null
    private var mCardNoTv: TextView? = null
    private var mNameTv: TextView? = null
    private var mGrossWeightTv: TextView? = null
    private var mTareTv: TextView? = null
    private var mPureWeightTv: TextView? = null
    private var mPNameTv: TextView? = null
    private var mPriceTv: TextView? = null

    private var tvPopNum: TextView? = null
    private var mGrossWeightTitleTv: TextView? = null
    private var mTareTitleTv: TextView? = null
    private var mPureWeightTitleTv: TextView? = null
    private var mPriceTitleTv: TextView? = null
    private var tvPopTotalTitle: TextView? = null
    private var tvPopTotalUnit: TextView? = null
    private var mTabLl: LinearLayout? = null
    private var time_price: LinearLayout? = null

    var jishi: Int = 0
    var jishiStart: Int = 20
    var timer: Timer? = null
    private var tvPopDown: TextView? = null
    private var playMusicUtils: PlayMusicUtil? = null
    private var config: Configuration? = null
    private var uploadManager: UploadManager? = null
    var mLoginBean: LoginBean? = null
    var mInsertStorageBean = InsertStorageBean()

    //设备登录
    override fun setLoginByDeviceIdBean(bean: LoginBean) {
        mLoginBean = bean
        tvStatus.text = "已连接"
        showOrDissmissSetting()
        //友盟统计账号
        MobclickAgent.onProfileSignIn(bean.equipment.name + "-" + bean.company.name)
        isLogin = true
        try {
            //开启服务监听重量
            readWagonBalanceDatas(
                12334,//端口号
                "=",//起始符
                0,//开始坐标
                8,//长度
                true,//是否有小数点
                false//是否正序
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //自动更新
    override fun setUpDateApp(bean: UpdateBean) {
        val newVersion = bean.version
        if (newVersion > getLocalVersion(this)) {
            showToast(resources.getString(R.string.update_app))
            //需要更新
            val intent = Intent(this, UpdateService::class.java)
            val bundle = Bundle()
            bundle.putString("url", bean.apk_url)
            intent.putExtras(bundle)
            startService(intent)
        }
    }

    //过磅报错
    override fun setInsertStorageErr(errMsg: String, errCode: Int) {
        if (errCode == 1007) {
            playMusicUtils?.playSound(1, PlayMusicUtil.NOR_LOOP)
        }
        showToast(errMsg)
    }

    //过磅信息展示
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun setInsertStorageResult() {
//        //刷卡成功，展示信息
        playMusicUtils?.playSound(0, PlayMusicUtil.NOR_LOOP)//播放声音
        showToast("刷卡成功，请使用手机继续操作")
//        try {
//        mInsertStorageBean = bean
//        if (popwindow.isShowing) {
//            popwindow.dismiss()
//        }
//
//        timer?.cancel()
//        rlPopBottom = popupView?.findViewById(R.id.rlPopBottom)
//        tvPopTotal = popupView?.findViewById(R.id.tvPopTotal)
//        tvTime1 = popupView?.findViewById(R.id.tvTime1)
//        tvTime2 = popupView?.findViewById(R.id.tvTime2)
//        btnPopCancel = popupView?.findViewById(R.id.btnPopCancel)
//        tvPopDown = popupView?.findViewById(R.id.tvPopDown)
//        mCardNoTv = popupView?.findViewById(R.id.mCardNoTv)
//        mNameTv = popupView?.findViewById(R.id.mNameTv)
//        mGrossWeightTv = popupView?.findViewById(R.id.mGrossWeightTv)
//        mTareTv = popupView?.findViewById(R.id.mTareTv)
//        mPureWeightTv = popupView?.findViewById(R.id.mPureWeightTv)
//        mPNameTv = popupView?.findViewById(R.id.mPNameTv)
//        mPriceTv = popupView?.findViewById(R.id.mPriceTv)
//
//        tvPopNum = popupView?.findViewById(R.id.tvPopNum)
//        mGrossWeightTitleTv = popupView?.findViewById(R.id.mGrossWeightTitleTv)
//        mTareTitleTv = popupView?.findViewById(R.id.mTareTitleTv)
//        mPureWeightTitleTv = popupView?.findViewById(R.id.mPureWeightTitleTv)
//        mPriceTitleTv = popupView?.findViewById(R.id.mPriceTitleTv)
//        tvPopTotalTitle = popupView?.findViewById(R.id.tvPopTotalTitle)
//        tvPopTotalUnit = popupView?.findViewById(R.id.tvPopTotalUnit)
//        mTabLl = popupView?.findViewById(R.id.mTabLl)
//        time_price = popupView?.findViewById(R.id.time_price)
//        adaptive2()
//        val cardNumber = bean.card_number //卡号
//        val sName = bean.sname //供应商姓名
//        val grossWeight: String = bean.gross_weight//毛重
//        val collectNumber = bean.collect_number //编号
//        val tare = bean.tare_weight //皮重
//        val pName = bean.pname //品类
//        val price = bean.price //单价
//        val collect_id = bean.collect_id//单号id
//
//        btnPopCancel?.setOnClickListener {
//            if (popwindow.isShowing) {
//                popwindow.dismiss()
//                btnPopCancel?.visibility = View.INVISIBLE
//            }
//            timer?.cancel()
//        }
//        mCardNoTv?.text = "卡号：$cardNumber"
//        mNameTv?.text = "姓名：$sName"
//        mGrossWeightTv?.text = grossWeight
//
//        popwindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0)
//        val lp = window.attributes
//        lp.alpha = 0.7f
//        window.attributes = lp
//        popwindow.setOnDismissListener {
//            val lp = window.attributes
//            lp.alpha = 1f
//            window.attributes = lp
//        }
//
//        //皮重为0 说明是第一次操作
//        if (bean.tare_check == 0) {
//            mTareTv?.text = "--"
//            mPureWeightTv?.text = "--"
//            mPNameTv?.text = "--"
//            mPriceTv?.text = "--"
//
//            tvTime1?.text = "毛重时间: " + bean.created_at
//            tvTime2?.text = "皮重时间: " + "--"
//
//            tvPopTotal?.text = " -- "
//        } else {
//            //皮重不为0 说明是第二次操作
//            mTareTv?.text = tare
//            mPureWeightTv?.text = bean.pure_weight
//            mPNameTv?.text = pName
//            mPriceTv?.text = price
//            tvTime1?.text = "毛重时间: " + bean.created_at
//            tvTime2?.text = "皮重时间: " + bean.out_time
//
//            tvPopTotal?.text = bean.total_price
//
//        }
//        val metric = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(metric)
//        val width = metric.widthPixels     // 屏幕宽度（像素）
//
//        showProgress()

    }

    private fun showProgress() {
        jishi = jishiStart//实际使用
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                jishi -= 1
                val msg = handler.obtainMessage()
                msg.what = 3
                msg.obj = jishi
                handler.sendMessage(msg)
            }
        }, 0, 1000)
    }

    companion object {
        var isLogin: Boolean = false

        var btnSetting: ImageView? = null

        fun showOrDissmissSetting() {
            if (isLogin) {
                btnSetting?.visibility = View.INVISIBLE
            } else {
                btnSetting?.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVersionTv.text = "版本号：" + getLocalVersionNo(this) + getAPPServiceAddress()
        btnSetting = findViewById(R.id.btn_setting)

        btnSetting?.setOnClickListener {
            val bundle = Bundle()
            beginActivity(SettingActivity::class.java, bundle, 1)
        }
    }


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun checkUserPermission() {
        val perms = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        EasyPermissions.requestPermissions(this, "应用需要以下权限，否则可能影响正常使用，请允许", 0, *perms)

    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ) {
                }
            }
        }
    }

    private var lastTime: Long = 0
    private var deviceId: String = ""

    @SuppressLint("SimpleDateFormat", "SdCardPath")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initEventAndData() {
        //详情页弹窗
        //TODO 测试通知
//        startBTSocketClientService(5)
        popupView = LayoutInflater.from(mContext).inflate(R.layout.pop_data, null)
        popwindow = BottomPopWindow(mContext, popupView, false)
        adaptive()//适配屏幕

        /**
         * 动态权限
         */
        checkUserPermission()
        config = Configuration.Builder()
            .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
            .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
            .connectTimeout(10)           // 链接超时。默认10秒
            .useHttps(false)               // 是否使用https上传域名
            .responseTimeout(60)          // 服务器响应超时。默认60秒
            .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
            .build()
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = UploadManager(config)
        //检查更新
        mPresenter?.updateakp()
        //用户登录 --用设备码进行登录
        try {
            if (SharedPreferencesManager.getInstance().getData(
                    this,
                    "user",
                    "deviceId"
                ) == null || SharedPreferencesManager.getInstance().getData(
                    this,
                    "user",
                    "deviceId"
                ).isEmpty()
            ) {
                //原来获取设备mac码，现改为用户手动输入
                deviceId = getSerialNumber()
                SharedPreferencesManager.getInstance()
                    .putData(this@MainActivity, "user", "deviceId", deviceId)
            } else {
                deviceId = SharedPreferencesManager.getInstance().getData(this, "user", "deviceId")
            }
            mPresenter?.loginByDeviceId(deviceId)
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("获取设备码失败")
        }

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
                surfaceView.holder.setFormat(PixelFormat.TRANSLUCENT)
                Log.e("tagH", "surface is created$m_iPort")
                if (-1 == m_iPort) {
                    return
                }
                val surface = holder?.surface
                if (true == surface?.isValid) {
                    if (!Player.getInstance().setVideoWindow(m_iPort, 0, holder)) {
                        Log.e("tagH", "Player setVideoWindow failed!")
                    }
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if (-1 == m_iPort) {
                    Log.e("tagH", "tagH")
                    return
                }
                if (true == holder?.surface?.isValid) {
                    if (!Player.getInstance().setVideoWindow(m_iPort, 0, null)) {
                        Log.e("tagH", "Player setVideoWindow failed!")
                    }
                }
            }

        })

        surfaceView2.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
                surfaceView2.holder.setFormat(PixelFormat.TRANSLUCENT)
                Log.e("tagH", "surface is created$m_iPort2")
                if (-1 == m_iPort2) {
                    return
                }
                val surface = holder?.surface
                if (true == surface?.isValid) {
                    if (!Player.getInstance().setVideoWindow(m_iPort2, 0, holder)) {
                        Log.e("tagH", "Player setVideoWindow failed!")
                    }
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {

            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if (-1 == m_iPort2) {
                    Log.e("tagH", "tagH")
                    return
                }
                if (true == holder?.surface?.isValid) {
                    if (!Player.getInstance().setVideoWindow(m_iPort2, 0, null)) {
                        Log.e("tagH", "Player setVideoWindow failed!")
                    }
                }
            }

        })

        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        val width = metric.widthPixels     // 屏幕宽度（像素）
        //播放mp3
        playMusicUtils = PlayMusicUtil(this)


        lastTime = System.currentTimeMillis()

        //刷id卡  --真实
        etCardNum.isCursorVisible = false
        etCardNum.setOnEditorActionListener { v, actionId, event ->
            swipCard()
            false
        }
        //TODO 测试按钮 正式拿掉
//        icon.setOnClickListener {
//            etCardNum.setText("0002003975")
//            s1 = testWeigh--.toString()
//            swipCard()
//        }
    }

    fun swipCard() {
        val string = etCardNum.text.toString()
//            showToast(string)
        etCardNum.setText("")
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastTime) < 1000) {
            showToast("刷卡太过频繁")
            lastTime = System.currentTimeMillis()
        } else if (string.length != 10) {
            showToast("卡号异常,请重新刷卡")
        } else {
            lastTime = currentTime
            // 地磅没有重量
            if (s1 == "0" || s1 == "0.0" || s1 == "--" || s1.isEmpty() || s1 == "00" || s1 == "000") {
                showToast("无过磅重量")
            } else {
                // 地磅有重量
                storagenum = s1

                mPresenter?.insertStorage(
                    string,
                    mLoginBean?.company?.id.toString(),
                    mLoginBean?.group?.id.toString(),
                    storagenum,
                    mLoginBean?.equipment?.id.toString()
                )
            }
        }
    }

    fun dp2px(context: Context, dp: Int): Float {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f)
    }

    /**
     * 适配
     */
    fun adaptive() {
        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        val width = metric.widthPixels     // 屏幕宽度（像素）
        if (width < 1900) {
            //适配壁挂(1024*768)
            btn_setting.setPadding(40, 40, 40, 40)
            tvStatus.textSize = 30f
            tv1.textSize = 30f
            mVersionTv.textSize = 30f
            var margin = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            margin.leftMargin = 40
            margin.addRule(RelativeLayout.CENTER_VERTICAL)
            icon.layoutParams = margin
            tvWait1.textSize = 40f
            tvNumber1.textSize = 150f
            tv2.textSize = 30f
        } else {
            //立式（1920*1080）
        }
    }

    /**
     * 适配刷卡成功界面
     */
    fun adaptive2() {
        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        val width = metric.widthPixels     // 屏幕宽度（像素）
        if (width < 1900) {
            mNameTv?.textSize = 25f
            mCardNoTv?.textSize = 25f
            tvPopDown?.textSize = 25f
            mGrossWeightTv?.textSize = 50f
            mTareTv?.textSize = 50f
            mPNameTv?.textSize = 35f
            mPureWeightTv?.textSize = 50f
            mPriceTv?.textSize = 50f
            tvPopTotal?.textSize = 60f
            tvTime1?.textSize = 25f
            tvTime2?.textSize = 25f
            tvPopNum?.textSize = 25f
            mGrossWeightTitleTv?.textSize = 35f
            mTareTitleTv?.textSize = 35f
            mPureWeightTitleTv?.textSize = 35f
            mPriceTitleTv?.textSize = 35f
            tvPopTotalTitle?.textSize = 35f
            tvPopTotalUnit?.textSize = 35f

            mNameTv?.setTextColor(resources.getColor(R.color.grey))
            mCardNoTv?.setTextColor(resources.getColor(R.color.grey))
            tvPopDown?.setTextColor(resources.getColor(R.color.grey))
            mGrossWeightTitleTv?.setTextColor(resources.getColor(R.color.grey))
            mTareTitleTv?.setTextColor(resources.getColor(R.color.grey))
            mPureWeightTitleTv?.setTextColor(resources.getColor(R.color.grey))
            mPriceTitleTv?.setTextColor(resources.getColor(R.color.grey))
            tvPopTotalTitle?.setTextColor(resources.getColor(R.color.grey))

            val layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topMargin = dp2px(this, 30).toInt()
            mNameTv?.layoutParams = layoutParams

            val layoutParams2 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams2.topMargin = dp2px(this, 30).toInt()
            layoutParams2.leftMargin = dp2px(this, 30).toInt()
            layoutParams2.addRule(RelativeLayout.RIGHT_OF, R.id.mNameTv)
            mCardNoTv?.layoutParams = layoutParams2

            val layoutParams3 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams3.topMargin = dp2px(this, 30).toInt()
            layoutParams3.addRule(RelativeLayout.LEFT_OF, R.id.tvPopDown)
            tvPopNum?.layoutParams = layoutParams3

            val layoutParams4 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams4.topMargin = dp2px(this, 30).toInt()
            layoutParams4.leftMargin = dp2px(this, 5).toInt()
            layoutParams4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            tvPopDown?.layoutParams = layoutParams4

            val layoutParams5 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams5.topMargin = dp2px(this, 30).toInt()
            layoutParams5.addRule(RelativeLayout.BELOW, R.id.mNameTv)
            layoutParams5.addRule(RelativeLayout.ABOVE, R.id.time_price)
            mTabLl?.layoutParams = layoutParams5

            val layoutParams6 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams6.topMargin = dp2px(this, 30).toInt()
            layoutParams6.bottomMargin = dp2px(this, 30).toInt()
            layoutParams6.addRule(RelativeLayout.ABOVE, R.id.rlPopBottom)
            time_price?.layoutParams = layoutParams6

            val layoutParams7 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams7.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            rlPopBottom?.layoutParams = layoutParams7
            rlPopBottom?.setPadding(0, 0, 0, dp2px(this, 30).toInt())

            val layoutParams9 = LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                dp2px(this, 60).toInt()
            )
            layoutParams9.leftMargin = dp2px(this, 50).toInt()
            btnPopCancel?.layoutParams = layoutParams9
            btnPopCancel?.setPadding(dp2px(this, 60).toInt(), 0, dp2px(this, 60).toInt(), 0)
        }
    }

    private var thread: Thread? = null
    private var socket: Socket? = null
    /**
     * 读取地磅显示器数据
     */
    private fun readWagonBalanceDatas(
        port: Int, starCharacter1: String, startPosition: Int,
        length: Int, havePoint: Boolean, isSequence: Boolean
    ) {
        serverSocket = ServerSocket(port)//建立连接
        Log.e("tag123", "1号服务连接")
        thread = Thread(Runnable {
            try {
                while (true) {
                    try {
                        socket = serverSocket?.accept()
                        socket?.keepAlive = true
                        Log.e("tag123", "此处")
                        try {
                            val reader = BufferedReader(
                                InputStreamReader(socket?.getInputStream())
                            )
                            val writer = BufferedWriter(
                                OutputStreamWriter(socket?.getOutputStream())
                            )
                            while (true) {
                                var weight: String = "0"
                                val b: Char = reader.read().toChar()
                                val d = b.toString()
                                if (d == starCharacter1) {
//                                            val d1 = CharArray(length)//d1是符合要求的一长串数据
//                                            reader.read(d1, startPosition, length)
                                    var d1 = StringBuffer()
                                    for (position in 0..length) {
                                        if (position >= startPosition) d1.append(reader.read().toChar())
                                    }
                                    if (isSequence) {
                                        //正序
                                        if (havePoint) {
                                            //有小数点  1 2 3 4 点 5 6
                                            var dNum = StringBuffer()
                                            for (i in 1..(length)) {
                                                dNum = dNum.append(d1[i])
                                            }
                                            weight = dNum.toString()
                                        } else {
                                            //无小数点  1 2 3 4 5 6 点位
                                            var dNum = StringBuffer()
                                            for (i in 1..(length - 1)) {
                                                dNum = dNum.append(d1[i])
                                            }
                                            val dString: String = dNum.toString()
                                            val dDouble: Double = dString.toDouble()
                                            when (d1[6].toString()) {
                                                "0" -> {
                                                    weight = dString
                                                }
                                                "1" -> {
                                                    weight = (dDouble / 10).toString()
                                                }
                                                "2" -> {
                                                    weight = (dDouble / 100).toString()
                                                }
                                                "3" -> {
                                                    weight = (dDouble / 1000).toString()
                                                }
                                            }
                                        }
                                    } else {
                                        //反序
                                        if (havePoint) {
                                            //有小数点
                                            //数据格式  1 2 3 4 点 5 6
                                            var dNum = StringBuffer()
                                            for (i in 1..(length)) {
                                                dNum = dNum.append(d1[length - i])
                                            }
                                            weight = dNum.toString()
                                        } else {
                                            //数据格式  1 2 3 4 5 6 点位
                                            var dNum = StringBuffer()
                                            for (i in 1..(length - 1)) {
                                                dNum = dNum.append(d1[length - 1 - i])
                                            }
                                            val dString: String = dNum.toString()
                                            val dDouble: Double = dString.toDouble()
                                            when (d1[6].toString()) {
                                                "0" -> {
                                                    weight = dString
                                                }
                                                "1" -> {
                                                    weight = (dDouble / 10).toString()
                                                }
                                                "2" -> {
                                                    weight = (dDouble / 100).toString()
                                                }
                                                "3" -> {
                                                    weight = (dDouble / 1000).toString()
                                                }
                                            }
                                        }
                                    }
                                    //发送handlerTODO
                                    val weightDouble = weight.toDouble()
                                    weight = weightDouble.toString()
                                    Log.e("tagWei", "$weight---重量")
                                    val msg = handler.obtainMessage()
                                    msg.what = 1
                                    msg.obj = weight
                                    handler.sendMessage(msg)
                                }
                            }
                            reader.close()
                            socket?.close()
                        } catch (e: Exception) {
                            runOnUiThread {
                                showToast("异常:" + e.toString())
                            }
                            e.printStackTrace()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            showToast("异常:" + e.toString())
                        }
                        e.printStackTrace()
                    }
                }
            } catch (e: IOException) {
                e.stackTrace
            }
        })
        thread?.start()
    }

    //刷新界面UI重量变化
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {
                1 -> {
                    val w1: String = msg.obj.toString()
                    Log.e("tagFinish", "$w1---数据")
                    when {
                        w1 == "0" -> {
                            tvWait1.text = "无过磅数据"
                            tvNumber1.text = "0"
                            s1 = "0"
                        }
                        w1 == "0.0" -> {
                            tvWait1.text = "无过磅数据"
                            tvNumber1.text = "0"
                            s1 = "0"
                        }
                        w1.isEmpty() -> {
                            tvWait1.text = "无过磅数据"
                            tvNumber1.text = "0"
                            s1 = "0"
                        }
                        w1 == "--" -> {
                            tvWait1.text = "无过磅数据"
                            tvNumber1.text = "0"
                            s1 = "0"
                        }
                        else -> {
                            tvWait1.text = "正在过磅"
                        }
                    }
                    if (tvWait1.text.toString() == "正在过磅") {
                        tvNumber1.text = w1
                        s1 = w1
                    }
                }
                //倒计时
                3 -> {
                    val time: Int = msg.obj as Int
                    if (time > 0) {
                        tvPopDown?.text = "(" + msg.obj.toString() + ")"
                    } else {
                        timer?.cancel()
                        if (popwindow.isShowing) {
                            popwindow.dismiss()
                            btnPopCancel?.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun createPresenter() {
        mPresenter = MainPresenterImpl()
    }

    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) run {
                Toast.makeText(
                    this, "再按一次退出程序！",
                    Toast.LENGTH_SHORT
                ).show()
                exitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(netWorkStateReceiver, filter)
        showOrDissmissSetting()
        super.onResume()

    }

    var netWorkStateReceiver = NetWorkStateReceiver()

    inner class NetWorkStateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                val connMgr =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                //获取ConnectivityManager对象对应的NetworkInfo对象
                //获取WIFI连接的信息
                val wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                //获取以太网数据连接的信息
                if (wifiNetworkInfo.isConnected) {
                    if (!isLogin && deviceId.isNotEmpty()) {
                        mPresenter?.loginByDeviceId(deviceId)
                    }
                } else {
//                    showToast("无线网未连接")
                }

                try {
                    val ethNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET)

                    if (ethNetworkInfo.isConnected) {
                        if (!isLogin && deviceId.isNotEmpty()) {
                            mPresenter?.loginByDeviceId(deviceId)
                        }
                    } else {
                        showToast("有线网未连接")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPause() {
        super.onPause()
//        unregisterReceiver(netWorkStateReceiver)
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        //关闭socket服务
        if (!serverSocket?.isClosed!!) {
            serverSocket?.close()
        }

        System.exit(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 2) {
            deviceId = SharedPreferencesManager.getInstance().getData(this, "user", "deviceId")
            mPresenter?.loginByDeviceId(deviceId)
        }
    }

    fun getAPPServiceAddress(): String {
        if (Constant.url.BASE.equals("http://47.99.201.99:8051/api/")) {
            return "(测试)"
        } else if (Constant.url.BASE.equals("http://192.168.1.101:7011/api/")) {
            return "(Debug)"
        }
        return ""
    }
}

