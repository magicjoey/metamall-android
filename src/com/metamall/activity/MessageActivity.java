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
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.Clutter.TimeButton.TimeButton;

/**
 * Created by Administrator on 2016/3/13.
 */
public class MessageActivity extends Activity implements View.OnClickListener {
    private ImageButton iBackm;
    private TextView message_suggest;
    private Button register_login;
    private TimeButton v;
    private EditText etcode;
    MetaApp metaApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        v = (TimeButton) findViewById(R.id.getAgain);
        v.onCreate(savedInstanceState);
        v.setTextAfter("秒后重新获取").setTextBefore("点击获取验证码").setLenght(15 * 1000);
        v.setOnClickListener(this);

        metaApp=MetaApp.getApp();

        initView();

    }

    private void initView(){
        iBackm = (ImageButton) findViewById(R.id.login_ib_back);
        iBackm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                builder.setMessage("短信接收需要时间，确定返回并重新开始？");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        MessageActivity.this.finish();
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
        message_suggest = (TextView) findViewById(R.id.message);
        String message_suggest1=metaApp.getnumber();
        String message_suggest2="我们将把验证码发到："+message_suggest1+",请注意查看";
        message_suggest.setText(message_suggest2);
        etcode=(EditText) findViewById(R.id.message_num);
        etcode.addTextChangedListener(new TextWatcher() {
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





        register_login=(Button)findViewById(R.id.register_login);
        final String registerIdentifyCode=etcode.getText().toString();
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerIdentifyCode.equals("")){
                    //TODO: getReturnCode()  code



                    Intent intent_register=new Intent();
                    intent_register.setClass(MessageActivity.this, WelcomeActivity.class);
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                else{
                    Toast.makeText(MessageActivity.this,"您输入的验证码不正确，请重新输入",Toast.LENGTH_LONG).show();
                }


            }
        });


    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Toast.makeText(MessageActivity.this, "这是处理调用者onclicklistnenr",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        v.onDestroy();
        super.onDestroy();
    }
    /**
     * 使用SharedPreferences保存用户登录信息
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context, String username, String password,String number){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        //提交
        editor.commit();
    }



}
