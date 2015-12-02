package com.metamall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.metamall.R;
import com.metamall.SQLite.account;

/**
 * <p>.</p>
 *
 * @author Wang Yi
 * @version RegisterActivity.java 1.0 Created@2015-11-14 20:43 $
 */
public class RegisterActivity extends Activity {
    private EditText username;
    private EditText password;
    private EditText repassword;
    private Button register;
    private Button cancel;
    private SQLiteDatabase ac;
    private account acHelper;
    public String id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        if (!password.getText().toString().equals(repassword.getText().toString())) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("警告")
                    .setMessage("两次密码输入不相同")
                    .setPositiveButton("确定", null)
                    .show();
            return;
        }
        acHelper=new account(this);                                             //打开数据库ac
        ac=acHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("_id",Integer.parseInt(id.toString()));
        cv.put("username",username.getText().toString());
        cv.put("password",password.getText().toString());//加入数据库中




        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);             //启动这个activity
        this.finish();                   //结束本个Activity

    }

    public void cancel_Click(View view){
        finish();
    }

}












