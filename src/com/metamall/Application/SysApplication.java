package com.metamall.Application;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version SysApplication.java 1.0 Created@2016-05-10 20:02 $
 */
public class SysApplication extends Application {
    private List<Activity> mList = new LinkedList<Activity>();
    private static SysApplication instance;


    private SysApplication() {
    }


    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }


    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }


    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }


    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
