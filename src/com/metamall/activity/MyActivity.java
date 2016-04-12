package com.metamall.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.activity.Setting.MySettingActivity;

import java.net.FileNameMap;

/**
 * 我的信息Fragment
 */
public class MyActivity extends Activity {
	
	private static final int REQUEST_CODE = 15342;

	private Button btnForPay;
	private Button btnForDelivery;
	private Button btnDeliveryed;
	private Button btnCompleted;
	private Button btnmyallaoder;
	private Button btnmycollection;
	private Button btnmyactivity;
	private Button btnrecommend;
	private Button btnsetting;
    private Button btlogin;
    private Button btregister;
    private LinearLayout linearLayout_unlisted;
    private LinearLayout linearLayout_listed;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		initView();
        linearLayout_unlisted=(LinearLayout) findViewById(R.id.my_unlisted);
        linearLayout_listed=(LinearLayout) findViewById(R.id.my_listed);
        linearLayout_listed.setVisibility(View.GONE);
        linearLayout_unlisted.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 初始化视图
	 * @param
	 */
	private void initView(){


		btnForPay=(Button) findViewById(R.id.obligation);
		btnForPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,ForPayActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnForDelivery=(Button) findViewById(R.id.DeliveryIng);
		btnForDelivery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,ForDeliveryActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnDeliveryed=(Button) findViewById(R.id.DeliveryEd);
		btnDeliveryed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(MyActivity.this,DeliveryedActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnCompleted=(Button) findViewById(R.id.completed);
		btnCompleted.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,CompletedActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnmyallaoder=(Button) findViewById(R.id.MyAllOrder);
		btnmyallaoder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,MyAllOrderActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnmycollection=(Button) findViewById(R.id.MyCollection);
		btnmycollection.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,myCollectionActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});

        btnmyactivity=(Button) findViewById(R.id.MyActivity);
        btnmyactivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyActivity.this,MyActivityActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnrecommend=(Button) findViewById(R.id.recommend);
        btnrecommend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyActivity.this,MyRecommendActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btlogin=(Button) findViewById(R.id.my_login);
        btlogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MyActivity.this,LoginActivity.class);
                finish();
            }
        });
        btregister=(Button) findViewById(R.id.my_register);
        btregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MyActivity.this,RegisterActivity.class);
                finish();
            }
        });
        btnsetting=(Button) findViewById(R.id.my_btn_edit);
        btnsetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MyActivity.this, MySettingActivity.class);
                finish();
            }
        });

	}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_CODE){
                linearLayout_listed.setVisibility(View.VISIBLE);
                linearLayout_unlisted.setVisibility(View.GONE);
            }
        }
    }





}
