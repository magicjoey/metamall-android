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
import android.widget.Toast;
import com.metamall.Application.MetaApp;
import com.metamall.R;

 /**
 * Created by Administrator on 2016/4/3.
 */
public class WelcomeActivity extends Activity {
    private ImageButton ibback;
    private Button btconfirm;
    private EditText etcurtain;
    private EditText etpassword;
    private EditText etpasswordConfirm;
     private MetaApp metaApp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        btconfirm.setEnabled(false);
        metaApp=MetaApp.getApp();
    }
    private void initView(){
        ibback=(ImageButton)findViewById(R.id.welcome_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                        metaApp.setName(etcurtain.toString());
                        metaApp.setpassword(etpassword.toString());
                        Intent i=new Intent();
                        i.setClass(WelcomeActivity.this,MyActivity.class);
                        finish();

                    }
                }

            }
        });


    }
}
