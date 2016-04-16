package com.metamall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.metamall.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class LogisticsActivity extends Activity {
    private ImageButton ibBack;
    private TextView tvLogistics;

    @Override
    protected void onCreate(Bundle savedStanceState){
        super.onCreate(savedStanceState);
        setContentView(R.layout.activity_logistics);
        initView();
    }
    private void initView(){
        ibBack=(ImageButton) findViewById(R.id.ib_back_logistics);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLogistics=(TextView) findViewById(R.id.logistics_tv);


    }
}
