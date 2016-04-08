package com.metamall.activity.Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageStats;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.activity.LoginActivity;
import com.metamall.activity.Personal.telephoneChangeActivity;
import com.metamall.manage.DataCleanManager;
import com.metamall.manage.FileUtil;
import com.metamall.manage.NetState;
import com.metamall.network.NetReceiver;

import java.io.File;

/**
 * Created by Administrator on 2016/4/4.
 */
public class MySettingActivity extends Activity {
    private ImageButton ibback;
    private ImageButton ibwifi;
    private ImageButton ibflow;
    private ImageButton ibpush;
    private Button btfeedback;
    private Button btclear1;
    private Button btclear2;
    private Button btus;
    private Button btexit;
    private Integer count;
    private String type = "";
    private Context context;
    private Long cachesize ;
    private Long datasize  ;
    private Long codesize  ;
    private Long totalsize ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        initView();
        getCurrentNetType(this);

        btclear2.setText(totalsize.toString());

    }
    private void initView(){
        ibback=(ImageButton)findViewById(R.id.ib_back_setting);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibwifi=(ImageButton) findViewById(R.id.setting_Wifi_update_yes);
        ibwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%2==1){
                    ibwifi.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_no));

                }
                else{
                    ibwifi.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_yes));
                    if(isWifiConnected()){
                        Intent intent = new Intent();
                        intent.putExtra("autoUpdate", "yes");
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra("autoUpdate", "no");
                    }
                }
            }
        });

        ibflow=(ImageButton) findViewById(R.id.setting_flow_figure_yes);
        ibflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%2==1){
                    ibflow.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_no));

                }
                else{
                    ibflow.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_yes));
                    if(type.equals("2g")||type.equals("3g")||type.equals("4g")){
                        //todo:setting draw gone!

                    }
                }
            }
        });
        ibpush=(ImageButton) findViewById(R.id.setting_message_push_yes);
        btfeedback=(Button) findViewById(R.id.setting_feedback);
        btfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MySettingActivity.this,FeedbackActivity.class);
                finish();
            }
        });
        btclear1=(Button) findViewById(R.id.setting_clear1);
        btclear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupwindows();
            }
        });
        btclear2=(Button) findViewById(R.id.setting_clear2);
        btclear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupwindows();
            }
        });
        btus=(Button) findViewById(R.id.setting_about_us);
        btus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MySettingActivity.this,AboutUsActivity.class);
            }
        });
        btexit=(Button) findViewById(R.id.exit);
        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=MySettingActivity.this.
                        getSharedPreferences("actm", Context.MODE_PRIVATE);//获得SharedPreferences对象
                String uname=sp.getString(                 //从SharedPreferences中读取用户名
                        null,              //键值
                        null );            //默认值
                if(uname==null){                       //判断uname是否为空
                    Toast.makeText(getApplicationContext(), "请登录", Toast.LENGTH_SHORT).show();
                }
                Intent i=new Intent();
                i.setClass(MySettingActivity.this, LoginActivity.class);
                finish();

            }
        });

    }


    private void showpopupwindows(){
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.setting_clear_pop, null);

        Button btnclear = (Button) popView.findViewById(R.id.btn_setting_clear_pop_clear);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_setting_clear_pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);//
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_setting_clear_pop_clear:
                        //todo   clean  DataCleanManager.java

                    case R.id.btn_setting_clear_pop_cancel:

                        break;
                }
                popWindow.dismiss();
            }
        };

        btnclear.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    public String getCurrentNetType(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4g";
            }
        }
        return type;
    }
    private boolean isWifiConnected() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public class PkgSizeObserver extends IPackageStatsObserver.Stub{
        /*** �ص�������
         * @param ,�������ݷ�װ��PackageStats������
         * @param succeeded  ����ص��ɹ�
         */
        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                throws RemoteException {
            // TODO Auto-generated method stub
            cachesize = pStats.cacheSize  ; //�����С
            datasize = pStats.dataSize  ;  //���ݴ�С
            codesize =	pStats.codeSize  ;  //Ӧ�ó����С
            totalsize = cachesize + datasize + codesize ;

        }
    }

}


