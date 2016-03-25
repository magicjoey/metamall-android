package com.metamall.activity.SonActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.activity.ProductDetailsSecondLayerActivity;

/**
 * Created by Administrator on 2016/3/25.
 */
public class TopHotActivity extends ProductDetailsSecondLayerActivity {
    private TextView tv_top;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_hot);
        initView();
    }
    private void initView(){
        tv_top=(TextView) findViewById(R.id.Page_name);
        tv_top.setText("TOP热卖");
    }

}
