package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.metamall.R;

/**
 * Created by Administrator on 2016/3/16.
 */
public class myCollectionActivity extends Activity {
    private ImageButton ibBack;
    private Button goShopping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
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
                Intent intent_login_my=new Intent();
                intent_login_my.setClass(myCollectionActivity.this,MyActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        goShopping = (Button) findViewById(R.id.myCollection_shopping);
        goShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_collection_home=new Intent();
                intent_collection_home.setClass(myCollectionActivity.this,HomeActivity.class);
                finish();

            }
        });
        LinearLayout linearLayout1=(LinearLayout) findViewById(R.id.myCollection_noCollection);




    }
}
