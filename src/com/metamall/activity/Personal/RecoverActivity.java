package com.metamall.activity.Personal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.Clutter.TimeButton.TimeButton;
import com.metamall.activity.MyActivity;
import com.metamall.activity.WelcomeActivity;

/**
 * Created by Administrator on 2016/4/9.
 */
public class RecoverActivity extends Activity {
    MetaApp metaApp;
    private TimeButton v;
    private ImageButton ibback;
    private EditText etnumber;
    private EditText etcode;
    private Button btconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        v = (TimeButton) findViewById(R.id.recover_getAgain);
        v.onCreate(savedInstanceState);
        v.setTextAfter("秒后重新获取").setTextBefore("点击获取验证码").setLenght(15 * 1000);
        v.setOnClickListener(v);

        metaApp= MetaApp.getApp();

        initView();

    }

    private void initView(){
        ibback = (ImageButton) findViewById(R.id.recover_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RecoverActivity.this);
                builder.setMessage("用错误的手机号码不利于保护您的隐私，确定返回并重新开始？");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        RecoverActivity.this.finish();
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
        etnumber=(EditText) findViewById(R.id.recover_telephone);
        etnumber.addTextChangedListener(new TextWatcher() {
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
        etcode=(EditText) findViewById(R.id.recover_message_num) ;
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






        btconfirm=(Button)findViewById(R.id.recover_confirm);
        final String registerIdentifyCode=etcode.getText().toString();
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerIdentifyCode.equals("")){
                    //TODO: getReturnCode()  code
                    String etnumber1=etnumber.toString().trim();
                    String head1=etnumber1.substring(0,3);  // "hamburger".substring(4, 8) returns "urge"//"smiles".substring(1, 5) returns "mile"
                    if(etnumber1.length()!=11){
                        Toast.makeText(getApplicationContext(),"请输入十一位手机号码",Toast.LENGTH_LONG).show();
                    } else if(head1.equals("135") || head1.equals("136")
                            || head1.equals("137") || head1.equals("138")
                            || head1.equals("139") || head1.equals("147")
                            || head1.equals("150") || head1.equals("151")
                            || head1.equals("152") || head1.equals("157")
                            || head1.equals("158") || head1.equals("159")
                            || head1.equals("182") || head1.equals("187")
                            || head1.equals("188") ||head1.equals("134")
                            ||head1.equals("130") || head1.equals("131")
                            || head1.equals("132") || head1.equals("145")
                            || head1.equals("155") || head1.equals("156")
                            || head1.equals("185") || head1.equals("186")
                            ||head1.equals("133") || head1.equals("153")
                            || head1.equals("180") || head1.equals("189")){
                        Intent i=new Intent();
                        i.setClass(RecoverActivity.this, MyActivity.class);
                        Toast.makeText(getApplicationContext(),"更改成功",Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    }


                    Intent intent_register=new Intent();
                    intent_register.setClass(RecoverActivity.this, WelcomeActivity.class);
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                else{
                    Toast.makeText(RecoverActivity.this,"您输入的验证码不正确，请重新输入",Toast.LENGTH_LONG).show();
                }


            }
        });


    }




    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        v.onDestroy();
        super.onDestroy();
    }


}
