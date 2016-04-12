package com.metamall.activity.Personal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.activity.MessageActivity;
import com.metamall.activity.MyActivity;
import com.metamall.activity.RegisterActivity;

/**
 * Created by Administrator on 2016/4/3.
 */
public class telephoneChangeActivity extends Activity {
    private ImageButton ibback;
    private Button btconfirm;
    private TextView tvpassword;
    private EditText etnewtelephone;
    private EditText etpassword;
    SharedPreferences sharedPre=getSharedPreferences("config", MODE_PRIVATE);

    String password=sharedPre.getString("password", "");



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone_change);
        initView();
        btconfirm.setEnabled(false);


    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_personal_telephone_change);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etnewtelephone=(EditText) findViewById(R.id.change_et_telephone);
        etnewtelephone.addTextChangedListener(new TextWatcher() {
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
        etpassword=(EditText) findViewById(R.id.change_et_telephone_password);
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
        if(etpassword.getText().length()!=0||etnewtelephone.getText().length()!=0){
            btconfirm.setEnabled(true);
        }
        btconfirm=(Button) findViewById(R.id.telephone_change_confirm_bt);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etnumber1=etnewtelephone.toString().trim();
                String head1=etnumber1.substring(0,3);  // "hamburger".substring(4, 8) returns "urge"//"smiles".substring(1, 5) returns "mile"
                if(etnumber1.length()!=11){
                    Toast.makeText(getApplicationContext(),"请输入十一位手机号码",Toast.LENGTH_LONG).show();
                }
                else if(!etpassword.toString().equals(password)){
                    Toast.makeText(getApplicationContext(),"确定输入的密码是否正确",Toast.LENGTH_SHORT).show();


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
                        i.setClass(telephoneChangeActivity.this,MyActivity.class);
                        Toast.makeText(getApplicationContext(),"更改成功",Toast.LENGTH_SHORT).show();
                        finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }

                else{
                        Toast.makeText(getApplicationContext(),"请检查手机号码格式",Toast.LENGTH_LONG).show();


                }

            }
        });
        tvpassword=(TextView) findViewById(R.id.telephone_change_confirm_tv);
        tvpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(telephoneChangeActivity.this, RecoverActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }
        });

    }
}
