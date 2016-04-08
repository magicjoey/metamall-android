package com.metamall.activity.Personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.metamall.R;

/**
 * Created by Administrator on 2016/4/3.
 */
public class PersonalSecureActivity extends Activity {
    private ImageButton ibback;
    private Button bttel1;
    private Button bttel2;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_secure);
        initView();
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_personal_secure);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bttel1=(Button) findViewById(R.id.login_secure_telephone1);
        bttel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();

            }
        });
        bttel2=(Button) findViewById(R.id.login_secure_telephone_3);
        bttel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
        linearLayout=(LinearLayout) findViewById(R.id.login_secure_password);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalSecureActivity.this,PersonalPasswordChangeActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });

    }
    private void showPopwindow(){
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.tele_change_pop, null);

        Button btnchange = (Button) popView.findViewById(R.id.btn_tele_change_pop_change);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_tele_change_pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);//
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_tele_change_pop_change:
                        Intent i=new Intent();
                        i.setClass(PersonalSecureActivity.this,telephoneChangeActivity.class);
                        finish();
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                        break;

                    case R.id.btn_tele_change_pop_cancel:

                        break;
                }
                popWindow.dismiss();
            }
        };

        btnchange.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
