package com.metamall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.metamall.model.Global;

/**
 * Created by Administrator on 2016/4/3.
 */
public class WelcomeActivity extends Activity {
    private ImageButton ibback;
    private Button btconfirm;
    private EditText etcurtain;
    private EditText etpassword;
    private EditText etpasswordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        btconfirm.setEnabled(false);

    }
    private void initView(){
        ibback=(ImageButton)findViewById(R.id.welcome_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setMessage("确定返回并重新开始？");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        WelcomeActivity.this.finish();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });
        etcurtain=(EditText)findViewById(R.id.welcome_et_account);
        etcurtain.addTextChangedListener(new TextWatcher() {
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
        etpassword=(EditText)findViewById(R.id.welcome_et_password);
        etpassword.addTextChangedListener(new TextWatcher() {
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
        etpasswordConfirm=(EditText)findViewById(R.id.welcome_et_password_confirm);
        etpasswordConfirm.addTextChangedListener(new TextWatcher() {
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
        btconfirm=(Button)findViewById(R.id.welcome_bt_confirm);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcurtain.getText().length()!=0||etpasswordConfirm.getText().length()!=0||etpassword.getText().length()!=0){
                    btconfirm.setEnabled(true);
                    if(etpassword.getText()!=etpasswordConfirm.getText()){
                        Toast.makeText(getApplicationContext(),"两次输入密码不一致，请检查",Toast.LENGTH_LONG).show();
                    }else{

                        Intent i=new Intent();
                        i.setClass(WelcomeActivity.this,MyActivity.class);
                        Global.isLogin=true;
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

                    }
                }

            }
        });
        SharedPreferences preference = getSharedPreferences("person", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();
        String User=etcurtain.getText().toString();
        String Psw=etpassword.getText().toString();
        edit.putString("User",User);
        edit.putString("Psw",Psw);
        edit.apply();


    }


}
