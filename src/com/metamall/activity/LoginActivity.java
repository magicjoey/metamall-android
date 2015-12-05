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
import com.metamall.utils.HttpUtil;
import com.metamall.utils.RemoteServiceEnum;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
		setContentView(R.layout.activity_login);
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
        try {
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
                    //登陆操作
                    String accountNo = username.getText().toString();
                    String password1 = password.getText().toString();
                    //远程调用登陆服务
//                Cursor c=ac.rawQuery(accountNo,null);                     //查询数据库

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("accountNo", accountNo);
                    map.put("password1", password1);
                    //返回json格式数据类型
                    String response = HttpUtil.post(RemoteServiceEnum.LOGIN, map);

                    if (1 == 1) {
                        Intent intent =
                                new Intent(LoginActivity.this, HomeFragment.class);       //意图跳不到主页
                        startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("警告");
                        builder.setMessage("账号密码不匹配");
                        builder.setPositiveButton("确定", null);
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
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);


                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
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
