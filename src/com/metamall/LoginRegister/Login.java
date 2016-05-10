package com.metamall.LoginRegister;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;
import com.metamall.Application.SysApplication;
import com.metamall.Dialog.CustomProgressDialog;
import com.metamall.activity.HomeActivity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.metamall.*;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version Login.java 1.0 Created@2016-05-10 19:52 $
 */
public class Login extends Activity implements OnClickListener {
    private TextView registertextview, login_cancle, recode_textview;
    private TextView usernameerrorid, passworderrorid;
    private EditText LoginName, Password;
    private ImageView login_name_clear_btn, clear_btn2;
    private CheckBox autologin;
    private Button BtnMenulogin;
    private String responseMsg = "";
    private static final int REQUEST_TIMEOUT = 5 * 1000;// 设置请求超时10秒钟
    private static final int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟
    private SharedPreferences sp;
    private LoginTask mLoginTask = null;
    private CustomProgressDialog progressDialog = null;
    int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        SysApplication.getInstance().addActivity(this);
        sp = getSharedPreferences("userdata", 0);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mLoginTask = new LoginTask(this);
        mLoginTask.execute();
        InitView();
        // 监听记住密码选项
        autologin
                .setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if (isChecked) {
                            Editor editor = sp.edit();
                            editor.putString("username", LoginName.getText()
                                    .toString());
                            editor.putString("password", Password.getText()
                                    .toString());
                            editor.commit();
                            LoginName.setText(sp.getString("username", ""));
                            Password.setText(sp.getString("password", ""));
                        }

                    }

                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE || resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            LoginName.setText(extras.getString("username"));
            Password.setText(extras.getString("password"));

        }
    }

    private void InitView() {
        // TODO Auto-generated method stub
        registertextview = (TextView) findViewById(R.id.register_text);
        login_cancle = (TextView) findViewById(R.id.login_cancle);

        LoginName = (EditText) findViewById(R.id.LoginName);
        Password = (EditText) findViewById(R.id.password);

        recode_textview = (TextView) findViewById(R.id.recode_textview);
        usernameerrorid = (TextView) findViewById(R.id.usernameerrorid);
        passworderrorid = (TextView) findViewById(R.id.passworderrorid);

        login_name_clear_btn = (ImageView) findViewById(R.id.login_name_clear_btn);
        clear_btn2 = (ImageView) findViewById(R.id.clear_btn2);

        autologin = (CheckBox) findViewById(R.id.autologin);

        BtnMenulogin = (Button) findViewById(R.id.BtnMenulogin);

        registertextview.setOnClickListener(this);
        login_cancle.setOnClickListener(this);
        LoginName.setOnClickListener(this);
        Password.setOnClickListener(this);
        login_name_clear_btn.setOnClickListener(this);
        clear_btn2.setOnClickListener(this);
        recode_textview.setOnClickListener(this);
        BtnMenulogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.register_text:

                Intent intent = new Intent(Login.this, Register.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.login_cancle:
                this.finish();
                break;

            case R.id.LoginName:
                usernameerrorid.setVisibility(View.GONE);
                login_name_clear_btn.setVisibility(View.VISIBLE);
                clear_btn2.setVisibility(View.GONE);
                break;
            case R.id.password:
                passworderrorid.setVisibility(View.GONE);
                clear_btn2.setVisibility(View.VISIBLE);
                login_name_clear_btn.setVisibility(View.GONE);
                break;
            case R.id.login_name_clear_btn:
                LoginName.setText("");
                passworderrorid.setVisibility(View.GONE);
                passworderrorid.setVisibility(View.GONE);
                break;
            case R.id.clear_btn2:
                passworderrorid.setVisibility(View.GONE);
                passworderrorid.setVisibility(View.GONE);
                Password.setText("");
                break;

            case R.id.recode_textview:

                Toast.makeText(this, "未处理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BtnMenulogin:

                Loginbtn();

                break;

            default:
                break;
        }
    }

    public void Loginbtn() {

        if (LoginName.getText().toString().trim().equals("")
                || LoginName.getText().toString().trim().length() > 20
                || LoginName.getText().toString().trim().length() < 4) {
            usernameerrorid.setVisibility(View.VISIBLE);
            usernameerrorid.setText("用户名错误");
        } else if (Password.getText().toString().trim().equals("")
                || Password.getText().toString().trim().length() > 16
                || Password.getText().toString().trim().length() < 6) {
            passworderrorid.setVisibility(View.VISIBLE);
            passworderrorid.setText("密码错误");
        } else {

            startProgressDialog();

            Thread loginThread = new Thread(new LoginThread());

            loginThread.start();
        }

    }

    // LoginThread线程类
    class LoginThread implements Runnable {

        @Override
        public void run() {
            String username = LoginName.getText().toString();
            String password = Password.getText().toString();
            boolean checkstatus = sp.getBoolean("checkstatus", false);
            if (checkstatus) {
                // 获取已经存在的用户名和密码
                String realUsername = sp.getString("username", "");
                String realPassword = sp.getString("password", "");
                if ((!realUsername.equals("")) && !(realUsername == null)
                        || (!realPassword.equals(""))
                        || !(realPassword == null)) {
                    if (username.equals(realUsername)
                            && password.equals(realPassword)) {
                        username = LoginName.getText().toString();
                        password = Password.getText().toString();
                    }
                }
            } else {
                password = Encrypt.md5(password);
            }

            // URL合法，但是这一步并不验证密码是否正确
            boolean loginValidate = loginServer(username, password);
            Message msg = handler.obtainMessage();
            if (loginValidate) {
                if (responseMsg.equals("success")) {
                    msg.what = 0;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 1;
                    handler.sendMessage(msg);
                }

            } else {
                msg.what = 2;
                handler.sendMessage(msg);
            }
        }

    }

    // Handler
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    stopProgressDialog();
                    Toast.makeText(getApplicationContext(), "登录成功！",
                            Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", LoginName.getText().toString());
                    Intent intent = new Intent(Login.this, HomeActivity.class);
                    intent.putExtras(bundle);
                    // 返回intent
                    setResult(RESULT_OK, intent);
                    Login.this.finish();

                    break;
                case 1:
                    stopProgressDialog();
                    Toast.makeText(getApplicationContext(), "输入的密码错误",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    stopProgressDialog();
                    Toast.makeText(getApplicationContext(), "服务器连接失败",
                            Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    private boolean loginServer(String username, String password) {
        boolean loginValidate = false;
        // 使用apache HTTP客户端实现
        String urlStr = "http://10.254.1.233:80/LoginServlet/servlet/LoginServlet";
        HttpPost request = new HttpPost(urlStr);
        // 如果传递参数多的话，可以丢传递的参数进行封装
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 添加用户名和密码
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        try {
            // 设置请求参数项
            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpClient client = getHttpClient();
            // 执行请求返回相应
            HttpResponse response = client.execute(request);

            // 判断是否请求成功
            if (response.getStatusLine().getStatusCode() == 200) {
                loginValidate = true;
                // 获得响应信息
                responseMsg = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginValidate;
    }

    // // 初始化HttpClient，并设置超时
    public HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        HttpClient client = new DefaultHttpClient(httpParams);
        return client;
    }
    /**
     * dialog
     */

    @Override
    protected void onDestroy() {
        stopProgressDialog();

        if (mLoginTask != null && !mLoginTask.isCancelled()){
            mLoginTask.cancel(true);
        }

        super.onDestroy();
    }

    private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("正在加载中...");
        }

        progressDialog.show();
    }

    private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public class LoginTask extends AsyncTask<Integer, String, Integer> {
        private Login login = null;

        public LoginTask(Login login){
            this.login = login;
        }

        @Override
        protected void onCancelled() {
            stopProgressDialog();
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Integer... params) {

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            startProgressDialog();
        }

        @Override
        protected void onPostExecute(Integer result) {
            stopProgressDialog();
        }



    }
}
