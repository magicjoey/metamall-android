package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.metamall.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class DrawbackActivity extends Activity {
    ImageButton ibBack;
    EditText etUrge;
    Button btnSure;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawback);
        initView();
    }
    private void initView(){
        ibBack=(ImageButton) findViewById(R.id.ib_back_drawback);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etUrge=(EditText) findViewById(R.id.et_drawback);
        etUrge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSure=(Button) findViewById(R.id.btn_drawback_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUrge.getText().toString().length()==0){
                    btnSure.setEnabled(false);
                }else {
                    btnSure.setEnabled(true);
                    Intent i=new Intent(Intent.ACTION_SENDTO);
                    String[] recipients = new String[]{"332866209@qq.com"};
                    i.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                    i.putExtra(android.content.Intent.EXTRA_SUBJECT,"订单");
                    i.putExtra(android.content.Intent.EXTRA_TEXT, "申请退款\n理由："+etUrge.getText().toString());
                    i.setType("text/plain");
                    startActivity(Intent.createChooser(i, "正在发送，请稍候..."));
                    finish();
                }

            }
        });

    }
}
