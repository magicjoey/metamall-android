package com.metamall.LoginRegister;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;
import com.metamall.Dialog.CustomProgressDialog;
import com.metamall.R;
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


import com.metamall.Application.SysApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version Register.java 1.0 Created@2016-05-10 19:41 $
 */
public class Register extends Activity implements View.OnClickListener {
    private ImageView register_back, user_name_clear, password_clear,
            confirm_password_clear;
    private TextView user_name_error, password_error, confirm_password_error;
    private Button register_button;
    private EditText user_name_edit, password_edit, confirm_password_edit;
    private String responseMsg = "";
    private static final int REQUEST_TIMEOUT = 5 * 1000;// 设置请求超时10秒钟
    private static final int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟
    private static final int LOGIN_OK = 1;
    private registerTask mregisterTask = null;
    private CustomProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        SysApplication.getInstance().addActivity(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mregisterTask = new registerTask(this);
        mregisterTask.execute();
        InitView();

    }

    private void InitView() {
        // TODO Auto-generated method stub
        register_back = (ImageView) findViewById(R.id.register_back);
        user_name_clear = (ImageView) findViewById(R.id.user_name_clear);
        password_clear = (ImageView) findViewById(R.id.password_clear);
        confirm_password_clear = (ImageView) findViewById(R.id.confirm_password_clear);
        register_button = (Button) findViewById(R.id.register_button);
        user_name_edit = (EditText) findViewById(R.id.user_name_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        confirm_password_edit = (EditText) findViewById(R.id.confirm_password_edit);
        user_name_error = (TextView) findViewById(R.id.user_name_error);
        password_error = (TextView) findViewById(R.id.password_error);
        confirm_password_error = (TextView) findViewById(R.id.confirm_password_error);

        register_back.setOnClickListener(this);
        register_button.setOnClickListener(this);
        user_name_edit.setOnClickListener(this);
        password_edit.setOnClickListener(this);
        confirm_password_edit.setOnClickListener(this);
        user_name_clear.setOnClickListener(this);
        password_clear.setOnClickListener(this);
        confirm_password_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:

                this.finish();
                break;
            case R.id.register_button:

                RegisterUser();
                break;
            case R.id.user_name_edit:
                user_name_error.setVisibility(View.GONE);
                user_name_clear.setVisibility(View.VISIBLE);
                password_clear.setVisibility(View.GONE);
                confirm_password_clear.setVisibility(View.GONE);
                break;
            case R.id.password_edit:
                password_error.setVisibility(View.GONE);
                user_name_clear.setVisibility(View.GONE);
                password_clear.setVisibility(View.VISIBLE);
                confirm_password_clear.setVisibility(View.GONE);

                break;
            case R.id.confirm_password_edit:
                confirm_password_error.setVisibility(View.GONE);
                user_name_clear.setVisibility(View.GONE);
                password_clear.setVisibility(View.GONE);
                confirm_password_clear.setVisibility(View.VISIBLE);
                break;
            case R.id.user_name_clear:
                user_name_edit.setText("");
                break;

            case R.id.password_clear:
                password_edit.setText("");
                break;
            case R.id.confirm_password_clear:
                confirm_password_edit.setText("");
                break;

            default:
                break;
        }
    }

    public void RegisterUser() {

        if (user_name_edit.getText().toString().trim().equals("")
                || user_name_edit.getText().toString().trim().length() > 20
                || user_name_edit.getText().toString().trim().length() < 4) {
            user_name_error.setVisibility(View.VISIBLE);
        } else if (password_edit.getText().toString().trim().equals("")
                || password_edit.getText().toString().trim().length() > 16
                || password_edit.getText().toString().trim().length() < 6) {
            password_error.setVisibility(View.VISIBLE);
        } else if (!confirm_password_edit.getText().toString().trim()
                .equals(password_edit.getText().toString().trim())) {
            confirm_password_error.setVisibility(View.VISIBLE);
        } else {

            String newusername = user_name_edit.getText().toString();
            String newpassword = Encrypt
                    .md5(password_edit.getText().toString());
            String confirmpwd = Encrypt.md5(confirm_password_edit.getText()
                    .toString());
            startProgressDialog();
            Thread loginThread = new Thread(new RegisterThread());
            loginThread.start();
        }

    }

    // 初始化HttpClient，并设置超时
    public HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        HttpClient client = new DefaultHttpClient(httpParams);
        return client;
    }

    private boolean registerServer(String username, String password) {
        boolean loginValidate = false;
        // 使用apache HTTP客户端实现
        String urlStr = "http://10.254.1.233:80/LoginServlet/servlet/RegisterServlet";
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

    // Handler
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    stopProgressDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", user_name_edit.getText()
                            .toString());
                    bundle.putString("password", password_edit.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    // 返回intent
                    setResult(RESULT_OK, intent);
                    Register.this.finish();
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    stopProgressDialog();
                    Toast.makeText(getApplicationContext(), "注册失败",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    stopProgressDialog();
                    Toast.makeText(getApplicationContext(), "服务器连接失败！",
                            Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };

    // RegisterThread线程类
    class RegisterThread implements Runnable {

        @Override
        public void run() {
            String username = user_name_edit.getText().toString();
            String password = Encrypt.md5(password_edit.getText().toString());

            // URL合法，但是这一步并不验证密码是否正确
            boolean registerValidate = registerServer(username, password);
            // System.out.println("----------------------------bool is :"+registerValidate+"----------response:"+responseMsg);
            Message msg = handler.obtainMessage();
            if (registerValidate) {
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


    /**
     * dialog
     */

    @Override
    protected void onDestroy() {
        stopProgressDialog();

        if (mregisterTask != null && !mregisterTask.isCancelled()){
            mregisterTask.cancel(true);
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

    public class registerTask extends AsyncTask<Integer, String, Integer> {
        private Register register = null;

        public registerTask(Register register){
            this.register = register;
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