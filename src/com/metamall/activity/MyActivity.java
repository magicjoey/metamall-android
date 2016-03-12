package com.metamall.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.metamall.R;

/**
 * 我的信息Fragment
 */
public class MyActivity extends Activity {
	
	private static final int REQUEST_CODE = 15342;
	
	private ImageButton ibLogin;
	private TextView tvUsername;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		//initView();
	}
	
	/**
	 * 初始化视图
	 * @param view 父视图
	 */
	private void initView(View view){
		ibLogin = (ImageButton) view.findViewById(R.id.my_ib_login);
		tvUsername = (TextView) view.findViewById(R.id.my_tv_username);

		ibLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyActivity.this, LoginActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
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
