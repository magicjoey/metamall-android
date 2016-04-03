package com.metamall.activity.Personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.metamall.R;


/**
 * Created by Administrator on 2016/3/29.
 */
public class PersonalSexActivity extends Activity {
    private ImageButton ibback;
    private Button btmale;
    private Button btfemale;
    private Button btserect;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_sex);
        initView();
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.personal_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalSexActivity.this, PersonalInformationActivity.class);
                finish();
            }
        });
        btmale=(Button) findViewById(R.id.male_select);
        btfemale=(Button) findViewById(R.id.female_select);
        btserect=(Button) findViewById(R.id.secret_select);
        btmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable1= getResources().getDrawable(R.drawable.cb_circle_big_c);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                btmale.setCompoundDrawables(null,null,drawable1,null);
                btfemale.setCompoundDrawables(null,null,null,null);
                btserect.setCompoundDrawables(null,null,null,null);
                Button btsex2=(Button) findViewById(R.id.sex_personal2);
                btsex2.setText("男");
                Intent i=new Intent();
                i.setClass(PersonalSexActivity.this,PersonalInformationActivity.class);
                finish();
            }
        });
        btfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable1= getResources().getDrawable(R.drawable.cb_circle_big_c);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                btmale.setCompoundDrawables(null,null,null,null);
                btfemale.setCompoundDrawables(null,null,drawable1,null);
                btserect.setCompoundDrawables(null,null,null,null);
                Button btsex2=(Button) findViewById(R.id.sex_personal2);
                btsex2.setText("女");
                Intent i=new Intent();
                i.setClass(PersonalSexActivity.this,PersonalInformationActivity.class);
                finish();

            }
        });
        btserect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable1= getResources().getDrawable(R.drawable.cb_circle_big_c);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                btmale.setCompoundDrawables(null,null,null,null);
                btfemale.setCompoundDrawables(null,null,null,null);
                btserect.setCompoundDrawables(null,null,drawable1,null);
                Button btsex2=(Button) findViewById(R.id.sex_personal2);
                btsex2.setText("保密");
                Intent i=new Intent();
                i.setClass(PersonalSexActivity.this,PersonalInformationActivity.class);
                finish();
            }
        });

    }
}
