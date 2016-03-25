package com.metamall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.metamall.R;

/**
 * Created by Administrator on 2016/3/16.
 */
public class ForPayActivity extends CartActivity {
    private ImageButton ibback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forpay);
        initView();
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_forpay);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(ForPayActivity.this,MyActivity.class);
                finish();
            }
        });

    }

}
