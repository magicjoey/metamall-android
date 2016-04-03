package com.metamall.activity.Personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.metamall.Clutter.SharedPreUtil;
import com.metamall.Clutter.UserEntity;
import com.metamall.R;
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
    private String district=user.getdistrict();
    private String details=user.getaddressDetails();



    View include1=(View) findViewById(R.id.add_address_refresh_2);
    private EditText etname;
    private EditText etnumber;
    private EditText etdetails;
    private Button btaddress;
    private ImageButton ibrecoginition;
    private ImageButton ibback;
    private Button btconfirm;


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
        btaddress=(Button) include1.findViewById(R.id.address_verbose);
        btaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        final WheelView mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewProvince.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {

            }
        });
        final WheelView mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewCity.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                //todo
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {

            }
        });
        final WheelView mViewDistrict = (WheelView) findViewById(R.id.id_district);
        mViewDistrict.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                //todo
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
            }
        });
        ibrecoginition=(ImageButton) include1.findViewById(R.id.slient_recognition_address_no);

        btconfirm=(Button) findViewById(R.id.address_refresh);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(etname.toString());
                user.setnumber(etnumber.toString());
                user.setaddressDetails(etdetails.toString());
                user.setProvince(mViewProvince.toString());
                user.setCity(mViewCity.toString());
                user.setdistrict(mViewDistrict.toString());
                SharedPreUtil.getInstance().putUser(user);
                finish();
            }
        });

    }
}
