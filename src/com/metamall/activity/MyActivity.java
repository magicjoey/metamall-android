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
import android.widget.TextView;
import com.metamall.R;

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
	private Button btnmyfit;
	private Button btnmyactivity;
	private Button btnrecommend;
    private String telnum;
	private Button btnsetting;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		initView();
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
		btnmyfit=(Button) findViewById(R.id.MyFit);
		btnmyfit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,MyFitActivity.class);
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

	}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_CODE){
                // 登录成功，取出登录数据
            }
        }
    }





}
