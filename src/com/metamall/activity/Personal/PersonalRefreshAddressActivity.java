package com.metamall.activity.Personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.Clutter.SharedPreUtil;
import com.metamall.Clutter.UserEntity;
import com.metamall.R;
import com.metamall.WheelView.ChangeAddressDialog;
import com.metamall.activity.Personal.PersonalAddAddressActivity;
import com.metamall.adapter.OnWheelScrollListener;
import com.metamall.adapter.WheelView;

/**
 * Created by Administrator on 2016/4/1.
 */
public class PersonalRefreshAddressActivity extends PersonalAddAddressActivity {
    UserEntity user;
    private String name=user.getUserName();
    private String telenumber=user.getnumber();
    private String province=user.getProvince();
    private String city=user.getCity();

    private String details=user.getaddressDetails();



    View include1=(View) findViewById(R.id.add_address_refresh_2);
    private EditText etname;
    private EditText etnumber;
    private EditText etdetails;
    private TextView btaddress;
    private ImageButton ibrecoginition;
    private ImageButton ibback;
    private Button btconfirm;
    private String mprovince;
    private String mcity;
    private Integer count;
    MetaApp metaApp;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_refresh_address);
        initView();
        SharedPreUtil.initSharedPreference(getApplicationContext());
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.address_refresh_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etname=(EditText) include1.findViewById(R.id.address_person_name);
        etname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etname.setText(name);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etnumber=(EditText) include1.findViewById(R.id.address_person_number);
        etnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etnumber.setText(telenumber);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etdetails=(EditText) include1.findViewById(R.id.address_verbose_details);
        etdetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etdetails.setText(details);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btaddress=(TextView) include1.findViewById(R.id.address_verbose);
        btaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
                        PersonalRefreshAddressActivity.this);
                mChangeAddressDialog.setAddress("四川", "自贡");
                mChangeAddressDialog.show();
                mChangeAddressDialog.setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city) {

                        btaddress.setText(province+"\t"+city);

                        // TODO Auto-generated method stub
                        Toast.makeText(PersonalRefreshAddressActivity.this,
                                province + "-" + city,
                                Toast.LENGTH_LONG).show();
                        mprovince=province;
                        mcity=city;
                    }
                });
            }
        });

        ibrecoginition=(ImageButton) include1.findViewById(R.id.slient_recognition_address_no);
        ibrecoginition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%2==1){
                    ibrecoginition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_yes));
                    metaApp=(MetaApp)getApplication();
                    metaApp.setRecognition(etname.getText().toString(),etnumber.getText().toString()
                            ,etdetails.getText().toString(),mprovince,mcity);
                }
                else{
                    ibrecoginition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_no));
                }
            }
        });

        btconfirm=(Button) findViewById(R.id.address_refresh);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(etname.toString());
                user.setnumber(etnumber.toString());
                user.setaddressDetails(etdetails.toString());
                user.setProvince(mprovince);
                user.setCity(mcity);
                SharedPreUtil.getInstance().putUser(user);
                finish();
            }
        });

    }
}
