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
	
	private ImageButton ibLogin;
	private TextView tvUsername;
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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		initView();
	}
	
	/**
	 * 初始化视图
	 * @param view 父视图
	 */
	private void initView(View view){
		ibLogin = (ImageButton) view.findViewById(R.id.my_ib_login);


		ibLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyActivity.this, LoginActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        tvUsername = (TextView) view.findViewById(R.id.my_tv_username);
        Bundle bundle1=this.getIntent().getExtras();
        if(bundle1!=null){
            telnum=bundle1.getString("TLE");
        }
        tvUsername.setText(telnum);

		btnForPay=(Button) view.findViewById(R.id.obligation);
		btnForPay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,ForPayActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnForDelivery=(Button) view.findViewById(R.id.DeliveryIng);
		btnForDelivery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,ForDeliveryActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnDeliveryed=(Button) view.findViewById(R.id.DeliveryEd);
		btnDeliveryed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(MyActivity.this,DeliveryedActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnCompleted=(Button) view.findViewById(R.id.completed);
		btnCompleted.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,CompletedActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnmyallaoder=(Button) view.findViewById(R.id.MyAllOrder);
		btnmyallaoder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,MyAllOrderActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnmycollection=(Button) view.findViewById(R.id.MyCollection);
		btnmycollection.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,myCollectionActivity.class);
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		btnmyfit=(Button) view.findViewById(R.id.MyFit);
		btnmyfit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyActivity.this,MyFitActivity.class);
                setResult(RESULT_CANCELED);
                finish();
			}
		});
        btnmyactivity=(Button) view.findViewById(R.id.MyActivity);
        btnmyactivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyActivity.this,MyActivityActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnrecommend=(Button) view.findViewById(R.id.recommend);
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




    public void Clickbt (View v1) {

        Intent intent = new Intent();

        intent.setClass(MyActivity.this,LoginActivity.class);

        startActivity(intent);


    }

}
