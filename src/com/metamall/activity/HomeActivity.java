package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.metamall.R;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HomeActivity extends Activity {
    private EditText ethome_top_et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }
    private void initView(){
        ethome_top_et_search=(EditText) findViewById(R.id.home_top_et_search);
        ethome_top_et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this,SearchActivity.class);
                finish();
            }
        });




    }
}
