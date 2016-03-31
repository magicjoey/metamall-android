package com.metamall.activity.Personal;

import android.app.Activity;
import android.os.Bundle;
import com.metamall.R;

/**
 * Created by Administrator on 2016/3/29.
 */
public class PersonalAddressActivity extends Activity {
    Bundle bundle=this.getIntent().getExtras();
    Bundle bundle1=this.getIntent().getExtras();
    String address1;
    String address2;
    String address3;
    String aname;
    String Anumber;
    String adetails;
    if (bundle!=null){
        address1=bundle.getString("Province");
        address2=bundle.getString("City");
        address3=bundle.getString("District");

    }
    if(bundle1!=null){
        aname=bundle1.getString("Name");
        Anumber=bundle1.getString("Number");
        adetails=bundle1.getString("AddressDetails");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_address);
        initView();
    }
    private void initView(){

    }
}
