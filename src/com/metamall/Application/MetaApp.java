package com.metamall.Application;

import android.app.Application;
import android.content.Context;
import com.metamall.manage.CrashHandler;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/20.
 */
public class MetaApp extends Application {

    private static Context sContext=null;
    private static MetaApp sApp = null;

    public static Map<String, Long> map;
    public static boolean mIsRuning = false;	//是否从ManiActivity进入启动应用的


    public static String GCRASHFILEPATH = "/sdcard/GoodPlaceError/";	//Crash路径



    @Override
    public void onCreate() {

        super.onCreate();
        initStaticApp(this);
        initStaticContext(getApplicationContext());



        // 注册crashHandler
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(MetaApp.getContext());
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


}
