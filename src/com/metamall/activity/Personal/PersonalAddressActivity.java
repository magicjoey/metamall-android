package com.metamall.activity.Personal;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.Clutter.UserEntity;
import com.metamall.R;

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

    UserEntity user;

    String aprovince=user.getProvince();
    String acity=user.getCity();
    String anumber=user.getnumber();
    String adetails=user.getaddressDetails();
    String aname1=user.getUserName();
    String actualAddress="收货地址："+aprovince+acity+adetails;
    String aname="收货人:"+aname1+"\t"+anumber;


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
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        listLayout = (LinearLayout) findViewById(R.id.address_suggest_layout);
        listView = (ListView) findViewById(R.id.personal_address_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent in=new Intent();
                in.setClass(PersonalAddressActivity.this,PersonalRefreshAddressActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }
        });
        Intent in=new Intent(this,PersonalInformationActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("Num",listView.getCount());
        in.putExtras(bundle);
        startActivity(in);
    }
}
