package com.asto.a91recyclingtreasurepad.core;

import android.content.Context;

import com.asto.a91recyclingtreasurepad.util.ASimpleCache;
import com.asto.a91recyclingtreasurepad.util.SharedPreferencesManager;

/**
 * Created by ZhengJie on 2017/10/30.
 * is use for:静态地址
 */
public interface Constant {
    class url{
        public static String BASE = SharedPreferencesManager.getInstance().getHttpAddress(AIOApp.Companion.getInstance());//可选择服务器
//        public static String BASE = "https://znj.zz91.com/api/";//正式服务器
//        public static String BASE = "http://47.99.201.99:8071/api/";//测试服务器
//        public static final String BASE = "http://192.168.1.101:8081/api/";//DEBUG服务器
//        public static final String BASE = "";

        //测试地址
        public static final String TEST = "test.do";
        //android版本号
        public static final String Ver =  "android" + android.os.Build.VERSION.RELEASE;

        //老接口
//        public static final String LOGIN = BASE+"loginsave.html";
//        public static final String GET_WEIGH_MESSAGE = BASE+ "insertstorage_android.html";
        public static final String UPLOAD = "upload.html";

        //新接口
        public static final String LOGIN = "loginsave.action";
        public static final String LOGIN_BY_DEVICE_ID = "LoginMachine.action";//设备码登录
        public static final String UPDATE_APK = "updateakp.action";//自动更新
        public static final String INSERT_STORAGE = "InsertStorage.action";//过磅
        public static final String UPLOAD_NEWEST = "UploadPic.action";//上传图片最新
        public static final String SEARCH_ORDER_BY_ID_PRINT = "SearchOrderByIdPrint.action";//打印数据查询
        public static final String GET_WEIGH_MESSAGE =  "InsertStorageAndroid.action";
        public static final String GET_UPLOAD_TOKEN = "GetUploadToken.action";
        public static final String UPLOAD_NEW = "upload.action";
        public static final String GET_PRINT_CONTENT = "PrintInfo.action";

    }

    /**
     * 跳转测试设置
     */
    class jump {

    }

    /***
     * 静态参数
     */
    class sign{
        /**
         * 是否为Debug 模式
         */
        public static final boolean IS_DEBUG = false;
        public static final String CACHE_BLUE_TOOTH_MAC = "blueTooth";//蓝牙打印机mac地址

        public static final String NETWORK_CONNECTIONS = "Network Connections";//网络成功连接
        public static final String AUTO_PRINT = "1";//自动打印
        public static final String NEED_PRINT = "2";//手动打印
        public static final String NO_PRINT = "3";//不打印

        //public static final String ACTION_SELECT="android.net.xprinter.selectreceiver";
        public static final String ACTION_EDIT="android.net.xprinter.receiver";
        public static final String ACTION_FRAGMENT_LABEL_EDIT="android.net.xprinter.fragmentreceiver1";
        public static final String ACTION_FRAGMENT_BARCODE_EDIT="android.net.xprinter.fragmentreceiver2";
        public static final String ACTION_FRAGMENT_D2BARCODE_EDIT="android.net.xprinter.fragmentreceiver3";
        public static final String ACTION_FRAGMENT_MOBAN1_EDIT="android.net.xprinter.fragmentreceiver4";
        public static final String ACTION_FRAGMENT_MOBAN2_EDIT="android.net.xprinter.fragmentreceiver5";
        public static final String ACTION_FRAGMENT_MOBAN3_EDIT="android.net.xprinter.fragmentreceiver6";
        public static final String ACTION_FRAGMENT_MOBAN4_EDIT="android.net.xprinter.fragmentreceiver7";
        public static final String ACTION_FRAGMENT_IMAGE_EDIT="android.net.xprinter.fragmentreceiver8";
        public static final int ENABLE_BLUETOOTH=1;//支持蓝牙
        public static final int LABEL_PRINT=2;
        public static final int type_moban1=5;
        public static final int type_moban2=6;
        public static final int type_moban3=7;
        public static final int type_moban4=8;
        public static final int TAKE_PICTURE=100;
        public static final int LOAD_PICTURE_KITKAK=101;
        public static final int LOAD_PICTURE=102;
    }

    /**
     * 广播类
     */
    class boardcast{
    }

    /**
     * 系统
     */
    class System{
        public static final String OS = "AOI";
    }
}
