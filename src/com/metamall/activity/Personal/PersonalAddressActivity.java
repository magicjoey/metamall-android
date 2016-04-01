package com.metamall.activity.Personal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.R;
import com.metamall.adapter.CartListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/3/29.
 */
public class PersonalAddressActivity extends ListActivity {
    private ImageButton ibback;
    private Button btadd;
    private List<Map<String, String>> data = new ArrayList<Map<String,String>>();
    private ListView listView;
    private LinearLayout listLayout;

    Bundle bundle=this.getIntent().getExtras();
    Bundle bundle1=this.getIntent().getExtras();
    String address1=bundle.getString("Province");
    String address2=bundle.getString("City");
    String address3=bundle.getString("District");
    String anumber=bundle1.getString("Number");
    String adetails=bundle1.getString("AddressDetails");
    String actualAddress="收货地址："+address1+address2+address3+adetails;
    String aname="收货人:"+bundle1.getString("Name")+"\t"+anumber;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_address);
        initView();
        setListAdapter(new SimpleAdapter(this,data,R.layout.activity_personal_address_list,
                new String[]{"收货信息","收货地址"},            //每行显示一组收货地址和收货信息
                new int[]{R.id.personal_address_name, R.id.personal_address_details}));
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("收货信息", aname);
        map1.put("收货地址", actualAddress);
        data.add(map1);
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btadd=(Button) findViewById(R.id.add_address);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalAddressActivity.this,PersonalAddAddressActivity.class);
                finish();
            }
        });
        listLayout = (LinearLayout) findViewById(R.id.address_suggest_layout);
        listView = (ListView) findViewById(R.id.personal_address_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent();
                i.setClass(PersonalAddressActivity.this,PersonalRefreshAddressActivity.class);
                finish();

            }
        });
        






    }
}
