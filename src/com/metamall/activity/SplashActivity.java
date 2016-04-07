package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.metamall.R;

/**
 * <p>.</p>
 *
 * @author Bells
 * @version SplashActivity.java 1.0 Created@2016-01-21 21:04 $
 */
public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_item);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,HomeActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.onDestroy();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}