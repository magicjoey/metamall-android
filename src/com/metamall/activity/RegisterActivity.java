package com.metamall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.metamall.R;


/**
 * <p>.</p>
 *
 * @author Wang Yi
 * @version RegisterActivity.java 1.0 Created@2015-11-14 20:43 $
 */
public class RegisterActivity extends Activity {
    private EditText username;
    private EditText password=null;
    private EditText repassword=null;
    private Button register;
    private Button cancel;
    public String id;
    public String password2=password.getText().toString();
    String repassword2=repassword.getText().toString();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        Button register = (Button) findViewById(R.id.register);
        Button cancel = (Button) findViewById(R.id.cancel);
    }


    public void register_Click(View view) {


        if (username.getText().toString().equals("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("警告")
                    .setMessage("请输入用户名")
                    .setPositiveButton("确定", null)
                    .show();
            return;
        }
        if (!password2.equals(repassword2)) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("警告")
                    .setMessage("两次密码输入不相同")
                    .setPositiveButton("确定", null)
                    .show();
            return;
        }





        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);             //启动这个activity
        this.finish();                   //结束本个Activity

    }

    public void cancel_Click(View view){
        finish();
    }

}












