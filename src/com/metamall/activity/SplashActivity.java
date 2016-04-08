package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.metamall.R;

/**
 * <p>.</p>
 *
 * @author Bells
 * @version SplashActivity.java 1.0 Created@2016-01-21 21:04 $
 */
public class SplashActivity extends Activity {
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(getApplication(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setBackgroundDrawableResource(R.drawable.splash_9);
        mMainHandler.sendEmptyMessageDelayed(0, 5000);
    }

    // much easier to handle key events
    @Override
    public void onBackPressed() {
    }


}