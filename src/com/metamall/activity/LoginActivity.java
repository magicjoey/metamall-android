package com.metamall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.metamall.R;
import com.metamall.SQLite.account;
import com.metamall.fragment.HomeFragment;

/**
 * 注册和登录Activity
 *
 */
public class LoginActivity extends Activity {

	private ImageButton ibBack;
	private EditText username;
	private EditText password;
	private Button btnLogin;
	private Button btnRegister;
    private SQLiteDatabase ac;
    private account acHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		ibBack = (ImageButton) findViewById(R.id.login_ib_back);
		ibBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		MyWatcher watcher = new MyWatcher();
		username = (EditText) findViewById(R.id.login_et_account);
		username.addTextChangedListener(watcher);
		password = (EditText) findViewById(R.id.login_et_password);
		password.addTextChangedListener(watcher);
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                //TODO:登陆操作
                String accountNo = username.getText().toString();
                String password1 = password.getText().toString();
                Cursor c=ac.rawQuery(accountNo,null);                     //查询数据库



                if( 1==1 ){
                    Integer intent=
                            new Intent(this,HomeFragment.class);       //意图跳不到主页
                    startActivity(intent);

                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder .setTitle("警告");
                    builder.setMessage("账号密码不匹配");
                    builder.setPositiveButton("确定",null);
                    builder.show();
                return;
                    //登陆失败

                }

            }
		});
		btnRegister = (Button) findViewById(R.id.login_btn_register1);
		btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                //TODO:跳转注册界面
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);



			}
		});
	}

	/**
	 * 监听文本框
	 */
	class MyWatcher implements TextWatcher {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			if ("".equals(username.getText().toString())
					|| "".equals(password.getText().toString())) {
				btnLogin.setEnabled(false);
				btnRegister.setEnabled(false);
			} else {
				btnLogin.setEnabled(true);
				btnRegister.setEnabled(true);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void afterTextChanged(Editable arg0) {
		}

	}

}
