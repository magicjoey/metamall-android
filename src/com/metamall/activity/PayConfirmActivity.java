package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.activity.Personal.PersonalAddAddressActivity;
import com.metamall.activity.Personal.PersonalAddressActivity;

/**
 * Created by Administrator on 2016/3/17.
 */
public class PayConfirmActivity extends Activity {
    private ImageButton ibback;
    private Button btaddress;
    private Button btConfirm;
    private TextView tv1;
    private TextView tv2;
    private RadioButton rbalipay;
    private RadioButton rbweixin;
    private RadioButton rbCOD;
    private RadioGroup rgPay;
    private TextView   tvpostsfree;
    private TextView tvallpay;
    private EditText etleave;
    private Integer a;
    private String bname;
    private MetaApp metaApp;
    private LinearLayout linearLayout;
    private String aname;
    private String anumber;
    private String aaddressDetails;
    private String aprovince;
    private String acity;
    private float aprice;
    private Integer bprice;
    private float priceall;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_confirm);
        initView();
        metaApp=MetaApp.getApp();
        bname=metaApp.getRecognition_name();
        aprice=metaApp.getprice();
        if(bname.length() !=0){
            btaddress.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            aname=bname;
            anumber=metaApp.getRecognition_number();
            aaddressDetails=metaApp.getRecognition_addressDetails();
            aprovince=metaApp.getRecognition_province();
            acity=metaApp.getRecognition_city();

        }
        else{
            btaddress.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }

    }

    private void initView(){
        ibback=(ImageButton) findViewById(R.id.pay_confirm_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btaddress=(Button) findViewById(R.id.pay_address);
        btaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PayConfirmActivity.this,PersonalAddAddressActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }
        });
        linearLayout=(LinearLayout)findViewById(R.id.confirm_address_suggest_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PayConfirmActivity.this,PersonalAddressActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        tv1=(TextView) findViewById(R.id.confirm_address_name);
        tv1.setText(aname+"\t"+"\t"+"\t"+"\t"+anumber);
        tv2=(TextView) findViewById(R.id.confirm_address_details);
        tv2.setText(aprovince+"\t"+acity+"\t"+aaddressDetails);
        rgPay=(RadioGroup) findViewById(R.id.payMode_radio) ;
        rbalipay=(RadioButton) findViewById(R.id.AliPay);
        rbweixin=(RadioButton) findViewById(R.id.winXin);
        rbCOD=(RadioButton) findViewById(R.id.COD);
        rgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId==rbalipay.getId()){
                    a=1;

                }else if(checkedId==rbweixin.getId()){
                    a=2;

                }else if(checkedId==rbCOD.getId()){
                    a=3;

                }
            }
        });
        tvpostsfree=(TextView) findViewById(R.id.Order_confirm_goodsPostsFree);
        if(metaApp.getRecognition_province().equals("西藏")&&aprice<200
                ||metaApp.getRecognition_province().equals("新疆")&&aprice<200){
            tvpostsfree.setText("10元");
            bprice=10;
        }else{
            tvpostsfree.setText("0元");
            bprice=0;
        }
        tvallpay=(TextView) findViewById(R.id.allPay);
        priceall=aprice+bprice;

        tvallpay.setText(priceall+"元");

        etleave=(EditText) findViewById(R.id.leave_message);
        etleave.addTextChangedListener(new TextWatcher() {
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

        btConfirm=(Button) findViewById(R.id.confirmPay);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(a){
                    case 1:{
                        //Todo Alipay

                    }
                    case 2:{
                        //todo weixinpay
                    }
                    case 3:{
                        //todo CODpay
                    }
                }

                Intent i=new Intent(Intent.ACTION_SENDTO);
                String[] recipients = new String[]{"332866209@qq.com"};
                i.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"订单");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "姓名："+aname+"\n"+"电话："+anumber+"\n"+"省市："+aprovince+"\t"+acity
                        +"\n"+"具体地点："+aaddressDetails+"\n"+"留言："+etleave.getText());
                i.setType("text/plain");
                startActivity(Intent.createChooser(i, "正在发送，请稍候..."));
                finish();

            }
        });



    }

}
