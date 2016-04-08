package com.metamall.activity.Personal;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.Clutter.UserEntity;
import com.metamall.R;
import com.metamall.WheelView.ChangeAddressDialog;
import com.metamall.WheelView.ChangeAddressDialog.OnAddressCListener;

/**
 * Created by Administrator on 2016/3/30.
 */
public class PersonalAddAddressActivity extends Activity {
    private ImageButton ibback;
    private EditText etname;
    private EditText etnumber;
    private TextView btaddress;
    private EditText etaddress_details;
    private ImageButton ibrecognition;
    private Button btaddressKeep;
    private MetaApp metaApp;
    private String mprovince;
    private String mcity;
    UserEntity user = new UserEntity();




    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addresss);
        initView();


    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.address_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        etname=(EditText) findViewById(R.id.address_person_name);
        etname.addTextChangedListener(new TextWatcher() {
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
        etnumber=(EditText) findViewById(R.id.address_person_number);
        etnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String etnumber1=etnumber.toString().trim();
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
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"请检查手机号格式",Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etaddress_details=(EditText) findViewById(R.id.address_verbose_details);
        etaddress_details.addTextChangedListener(new TextWatcher() {
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
        btaddress=(TextView) findViewById(R.id.address_verbose);
        btaddress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
                        PersonalAddAddressActivity.this);
                mChangeAddressDialog.setAddress("四川", "自贡");
                mChangeAddressDialog.show();
                mChangeAddressDialog.setAddresskListener(new OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city) {

                        btaddress.setText(province+"\t"+city);

                        // TODO Auto-generated method stub
                        Toast.makeText(PersonalAddAddressActivity.this,
                                province + "-" + city,
                                Toast.LENGTH_LONG).show();
                        mprovince=province;
                        mcity=city;
                    }
                });
            }
        });
        ibrecognition=(ImageButton) findViewById(R.id.slient_recognition_address_no);
        ibrecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                if(count%2==1){
                    ibrecognition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_yes));
                    metaApp=(MetaApp)getApplication();
                    metaApp.setRecognition(etname.getText().toString(),etnumber.getText().toString()
                            ,etaddress_details.getText().toString(),mprovince,mcity);
                }
                else{
                    ibrecognition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_no));
                }
            }
        });
        btaddressKeep=(Button) findViewById(R.id.address_keep);


        if(etname.getText().length()!=0||etnumber.getText().length()!=0||etaddress_details.getText().length()!=0
                ||btaddress.getText().length()!=0) {

            btaddressKeep.setBackgroundColor(Color.YELLOW);
            btaddressKeep.setEnabled(true);
            btaddressKeep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setUserName(etname.getText().toString());
                    user.setnumber(etnumber.getText().toString());
                    user.setaddressDetails(etaddress_details.getText().toString());
                    user.setProvince(mprovince);
                    user.setCity(mcity);
                    Intent in=new Intent();
                    in.setClass(PersonalAddAddressActivity.this,PersonalAddressActivity.class);
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else{
            btaddressKeep.setEnabled(false);
        }




    }



}










