package com.metamall.activity.Personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.activity.MyActivity;

/**
 * Created by Administrator on 2016/4/3.
 */
public class PersonalPasswordChangeActivity extends Activity {
    private ImageButton ibback;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etNewPassword_confirm;
    private Button btconfirm;
    MetaApp metaApp;
    private String pw=metaApp.getLogin_user_password();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_password_change);
        initView();
        metaApp=MetaApp.getApp();

    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_personal_password_change);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etOldPassword=(EditText) findViewById(R.id.change_et_password_old);
        etOldPassword.addTextChangedListener(new TextWatcher() {
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
        etNewPassword=(EditText) findViewById(R.id.change_et_password);
        etNewPassword_confirm=(EditText) findViewById(R.id.change_et_password_confirm);
        etNewPassword.addTextChangedListener(new TextWatcher() {
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
        etNewPassword_confirm.addTextChangedListener(new TextWatcher() {
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
        btconfirm=(Button) findViewById(R.id.password_change_bt_confirm);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etOldPassword.toString().equals(pw)){
                    Toast.makeText(getApplicationContext(),"请确认您的旧密码是否正确\n不记得旧密码可以到手机号更换重新设置",Toast.LENGTH_LONG).show();
                }else if(!etNewPassword.toString().equals(etNewPassword_confirm.toString())){
                    Toast.makeText(getApplicationContext(),"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    pw=etNewPassword.toString();
                    Intent i=new Intent();
                    i.setClass(PersonalPasswordChangeActivity.this, MyActivity.class);
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
