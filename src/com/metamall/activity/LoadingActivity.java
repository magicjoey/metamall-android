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
 * @version LoadingActivity.java 1.0 Created@2016-01-21 21:04 $
 */
public class LoadingActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_item);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingActivity.this,MainActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}