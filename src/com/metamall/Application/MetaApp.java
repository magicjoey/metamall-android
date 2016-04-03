package com.metamall.Application;

import android.app.Application;
import android.content.Context;
import com.metamall.R;
import com.metamall.manage.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/20.
 */
public class MetaApp extends Application {

    private static Context sContext=null;
    private static MetaApp sApp = null;

    public static Map<String, Long> map;
    public static boolean mIsRuning = false;	//是否从ManiActivity进入启动应用的

    private String  recognition;
    public static boolean ibrecognition;

    public static String GCRASHFILEPATH = "/sdcard/GoodPlaceError/";	//Crash路径

    @Override
    public void onCreate() {

        super.onCreate();
        initStaticApp(this);
        initStaticContext(getApplicationContext());
        // 注册crashHandler
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(MetaApp.getContext());

        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private static void initStaticApp(MetaApp app) {
        sApp = app;
    }

    private static void initStaticContext(Context context) {
        sContext = context;
    }

    public static MetaApp getApp() {
        return sApp;
    }

    public static Context getContext() {
        return sContext;
    }


    //默认收货地址
    public String getRecognition() {
        return recognition;
    }
    public void setRecognition(String recognition){
        this.recognition=recognition;
    }
    private String login_user_name = "";
    private String login_user_password="";

    public String getLoginUserName() {
        return login_user_name;
    }
    public void setLoginUserName(String login_user_name) {
        this.login_user_name = login_user_name;
    }
    public String getLogin_user_password(){
        return login_user_password;

    }
    public void setLogin_user_password(String login_user_password){
        this.login_user_password = login_user_password;
    }





}
