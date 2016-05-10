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
import com.metamall.MySQL.MySqlUtil;
import com.metamall.R;
import com.metamall.activity.Personal.PersonalSexActivity;
import com.metamall.model.Global;

import java.sql.Connection;

/**
 * Created by Administrator on 2016/4/3.
 */
public class WelcomeActivity extends Activity {
    private ImageButton ibback;
    private Button btconfirm;
    private EditText etcurtain;
    private EditText etpassword;
    private EditText etpasswordConfirm;
    private Button btsex1;
    private Button btsex2;
    private static final String URL = "jdbc:mysql://10.120.57.107/basicinfo";
    private static final String USER = "mark";
    private static final String PASSWORD = "123456";
    Intent intent=getIntent();
    String sex=intent.getStringExtra("sex");
    MetaApp metaApp;


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
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setMessage("确定返回并重新开始？");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences preferences=getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                        preferences.edit().clear().commit();
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
        btsex1=(Button) findViewById(R.id.welcome_sex_personal1);
        btsex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(WelcomeActivity.this,PersonalSexActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        btsex2=(Button) findViewById(R.id.welcome_sex_personal2);
        btsex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(WelcomeActivity.this, PersonalSexActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        btconfirm=(Button)findViewById(R.id.welcome_bt_confirm);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcurtain.getText().length()!=0&&etpasswordConfirm.getText().length()!=0&&etpassword.getText().length()!=0){
                    btconfirm.setEnabled(true);
                    if(etpassword.getText()!=etpasswordConfirm.getText()){
                        Toast.makeText(getApplicationContext(),"两次输入密码不一致，请检查",Toast.LENGTH_LONG).show();
                    }else if(etpassword.getText().toString().length()<6||etpassword.getText().toString().length()>18){
                        Toast.makeText(getApplicationContext(),"请输入6~18位密码",Toast.LENGTH_LONG).show();

                    }else{
                        Connection conn = MySqlUtil.openConnection(URL, USER, PASSWORD);
                        MySqlUtil.execSQL(conn, "insert into basicinfo values(56,'小李')");



                        Intent i=new Intent();
                        i.setClass(WelcomeActivity.this,MyActivity.class);
                        Global.isLogin=true;
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

                    }
                }

            }
        });




    }


}
