package com.metamall.activity;

        import android.app.Activity;
		import android.content.Intent;
		import android.os.Bundle;
        import android.text.Editable;
		import android.text.TextUtils;
		import android.text.TextWatcher;
		import android.text.method.HideReturnsTransformationMethod;
		import android.text.method.PasswordTransformationMethod;
		import android.util.Log;
		import android.view.View;
        import android.view.View.OnClickListener;
		import android.widget.*;
		import com.metamall.R;
		import com.metamall.Service.FileService;

		import java.util.Map;

/**
 + * 注册和登录Activity
 + *
 + */
		public class LoginActivity extends Activity {

			private ImageButton ibBack;
    	    private EditText etAccount;
			private EditText etPassword;
    	    private Button btnLogin;
    		private Button btnRegister;
			private FileService fileService;
	        private CheckBox btnPassYN;
	        private static final String TAG = "LoginActivity";



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
        		ibBack = (ImageButton) findViewById(R.id.login_ib_back);
        		ibBack.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent_login_my=new Intent();
						intent_login_my.setClass(LoginActivity.this,MyActivity.class);
						setResult(RESULT_CANCELED);
						finish();
					}
				});
        		MyWatcher watcher = new MyWatcher();
        		etAccount = (EditText) findViewById(R.id.login_et_account);
        		etAccount.addTextChangedListener(watcher);
        		etPassword = (EditText) findViewById(R.id.login_et_password);
        		etPassword.addTextChangedListener(watcher);
        		btnLogin = (Button) findViewById(R.id.login_btn_login);
        		btnLogin.setOnClickListener(new OnClickListener() {
            			@Override
            			public void onClick(View v) {
                                //TODO:登陆操作
							String accountNo = etAccount.getText().toString();
							String password = etPassword.getText().toString();


							try {
								Map<String,String> map = fileService.getUserInfo("private.txt");
								etAccount.setText(map.get("username"));
								etPassword.setText(map.get("password"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							fileService = new FileService(LoginActivity.this);
							if(TextUtils.isEmpty(accountNo) || TextUtils.isEmpty(password)){
								Toast.makeText(LoginActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
								}
							else

							{
								Log.i(TAG, "保存用户名和密码，"+accountNo+":"+password);
								try {
									boolean result = fileService.saveToRom(password, accountNo, "private.txt");
									if(result){
										Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
										Intent intent_login_my=new Intent();
										intent_login_my.setClass(LoginActivity.this,MyActivity.class);

									}else{
										Toast.makeText(getApplicationContext(), R.string.failed, Toast.LENGTH_SHORT).show();
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(getApplicationContext(), R.string.failed, Toast.LENGTH_SHORT).show();
								}
							}



						}
				});
				btnPassYN=(CheckBox)findViewById(R.id.passYN);
				btnPassYN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
						// TODO Auto-generated method stub
						if (btnPassYN.isChecked()) {
							//设置EditText的密码为可见的
							etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
						} else {
							//设置密码为隐藏的
							etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
						}
					}
				});


        		btnRegister = (Button) findViewById(R.id.login_btn_register);
        		btnRegister.setOnClickListener(new OnClickListener() {
            			@Override
            			public void onClick(View v) {
							Intent intent_register=new Intent();
							intent_register.setClass(LoginActivity.this, RegisterActivity.class);
							finish();


                                			}
            		});
        	}

            	/**
     +	 * 监听文本框
     +	 */
            	class MyWatcher implements TextWatcher {

                		@Override
        		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                           				int arg3) {
            			if ("".equals(etAccount.getText().toString())
                    					|| "".equals(etPassword.getText().toString())) {
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