package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.metamall.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MyActivityActivity extends Activity {
    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        ibBack = (ImageButton) findViewById(R.id.login_ib_back);
        ibBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_login_my = new Intent();
                intent_login_my.setClass(MyActivityActivity.this, MyActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
