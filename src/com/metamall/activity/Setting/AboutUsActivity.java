package com.metamall.activity.Setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.metamall.R;

/**
 * Created by Administrator on 2016/4/4.
 */
public class AboutUsActivity extends Activity{
        private ImageButton ibback;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
    }
    private void initView(){
        ibback=(ImageButton)findViewById(R.id.ib_back_about_us);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
