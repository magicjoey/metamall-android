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



/**
 * <p>.</p>
 *
 * @author Wang Yi
 * @version RegisterActivity.java 1.0 Created@2015-11-14 20:43 $
 */
public class RegisterActivity extends Activity {
    private ImageButton iBack;
    private EditText etTelephone;
    private Button btnRegister;
    MetaApp metaApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        metaApp=MetaApp.getApp();
    }
    private void initView(){
        iBack = (ImageButton) findViewById(R.id.login_ib_back);
        iBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("userInfo",Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                finish();
            }
        });

        etTelephone = (EditText) findViewById(R.id.telephone);
        etTelephone.addTextChangedListener(new TextWatcher() {
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
        btnRegister = (Button) findViewById(R.id.register_message);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String etnumber1=etTelephone.toString().trim();
                String head1=etnumber1.substring(0,3);  // "hamburger".substring(4, 8) returns "urge"//"smiles".substring(1, 5) returns "mile"
                if(etnumber1.length()!=11){
                    Toast.makeText(getApplicationContext(),"请输入十一位手机号码",Toast.LENGTH_LONG).show();
                }
                else{
                    if(head1.equals("135") || head1.equals("136")
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("我们将把短信发到"+etnumber1+"上，请注意查收。");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                metaApp.setTeleNumber(etnumber1);
                                Intent i=new Intent();
                                i.setClass(RegisterActivity.this,MessageActivity.class);
                                finish();
                                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);


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
                    else{
                        Toast.makeText(getApplicationContext(),"请检查手机号码格式",Toast.LENGTH_LONG).show();
                    }

                }


            }


        });
    }
    /**
     +	 * 监听文本框
     +	 */
    class MyWatcher implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            if ("".equals(etTelephone.getText().toString())) {
                btnRegister.setEnabled(false);
            } else {
                btnRegister.setEnabled(true);
            }
        }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
            int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
        }
    }



}


