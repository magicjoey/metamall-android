package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;
import com.metamall.R;

import java.io.*;

/**
 * Created by Administrator on 2016/3/13.
 */
public class MessageActivity extends Activity {
    private ImageButton iBackm;
    private TextView message_suggest;
    private CheckBox sendAgain;
    private Button register_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();

    }
    private void initView(){
        iBackm = (ImageButton) findViewById(R.id.login_ib_back);
        iBackm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_register_my=new Intent();
                intent_register_my.setClass(MessageActivity.this,MyActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }

        });
        message_suggest = (TextView) findViewById(R.id.message);
        Intent intent_message=getIntent();
        String message_suggest1=intent_message.getStringExtra("TLE");
        String message_suggest2="我们将把验证码发到："+message_suggest1+",请注意查看";
        message_suggest.setText(message_suggest2);

        sendAgain=(CheckBox)findViewById(R.id.getAgain);
        final String code;
        //TODO：send message identifying code!  Watch out the"TODO"below

        register_login=(Button)findViewById(R.id.register_login);
        final String registerIdentifyCode=register_login.getText().toString();
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerIdentifyCode.equals("")){
                    //TODO: getReturnCode()  code


                Intent intent_register=new Intent();
                intent_register.setClass(MessageActivity.this, MyActivity.class);
                finish();
                }
                else{
                    Toast.makeText(MessageActivity.this,"您输入的验证码不正确，请重新输入",Toast.LENGTH_LONG).show();
                }


            }
        });







    }



}
