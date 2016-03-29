package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.metamall.R;
import com.metamall.activity.Personal.PersonalSexActivity;


public class PersonalInformationActivity extends Activity {
    private Button btsex1;
    private Button btsex2;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        initView();

    }
    private void initView(){
        btsex1=(Button) findViewById(R.id.sex_personal1);
        btsex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this,PersonalSexActivity.class);
                finish();
            }
        });
        btsex2=(Button) findViewById(R.id.sex_personal2);
        btsex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this, PersonalSexActivity.class);
                finish();
            }
        });

    }
}
