package com.metamall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.metamall.R;
import com.metamall.fragment.HomeFragment;
import com.metamall.utils.HttpUtil;
import com.metamall.utils.RemoteServiceEnum;


import java.util.HashMap;
import java.util.Map;

/**
 * 注册和登录Activity
 *
 */
public class LoginActivity extends Activity {

	private ImageButton ibBack;
	private EditText username;
	private EditText password=null;
    private CheckBox check=null;
	private Button btnLogin;
	private Button btnRegister;
    private String password1;
    private String password3;
    private String username3;
    private String username1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        initView();
        this.password=(EditText)super.findViewById(R.id.password);
        this.check=(CheckBox)super.findViewById(R.id.check);
        Bundle bundle1=this.getIntent().getExtras();
        if(bundle1!= null){
            password3=bundle1.getString("password2");
            username3=bundle1.getString("username2");
        }
        //为check设置监听选项，控制密码框的显示方式
        this.check.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(check.isChecked())
                {
                        //设置密码可见
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else
                    {
                        //设置密码隐藏
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });
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
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("accountNo", accountNo);
                    map.put("password1", password1);
                    //返回json格式数据类型
                    String response = HttpUtil.post(RemoteServiceEnum.LOGIN, map);

                    if (password1.equals("password2")) {
                        Intent intent =
                                new Intent(LoginActivity.this, HomeFragment.class);       //意图跳到主页
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
