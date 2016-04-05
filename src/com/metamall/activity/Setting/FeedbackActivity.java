package com.metamall.activity.Setting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.metamall.R;

/**
 * Created by Administrator on 2016/4/4.
 */
public class FeedbackActivity extends Activity {
    private ImageButton ibback;
    private Button btsend;
    private EditText etsuggestion;
    private EditText ettelephone;
    private String bsuggestion;
    private String btelephone;
    private String b;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        b=btelephone+"\n"+bsuggestion;
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_feedback);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etsuggestion=(EditText) findViewById(R.id.feedback_suggest);
        etsuggestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bsuggestion=etsuggestion.getText().toString();

            }
        });
        ettelephone=(EditText) findViewById(R.id.feedback_telephone_et);
        ettelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btelephone=ettelephone.getText().toString();
            }
        });
        btsend=(Button) findViewById(R.id.feedback_send);
        btsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Intent.ACTION_SENDTO);
                String[] recipients = new String[]{"332866209@qq.com"};
                i.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"一路惊喜问题反馈");
                i.putExtra(android.content.Intent.EXTRA_TEXT, b);
                i.setType("text/plain");
                startActivity(Intent.createChooser(i, "正在发送，请稍候..."));
                finish();
            }
        });
    }

}
