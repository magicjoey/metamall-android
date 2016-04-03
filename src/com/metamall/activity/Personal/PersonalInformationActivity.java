package com.metamall.activity.Personal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.metamall.R;
import com.metamall.View.RoundImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;


public class PersonalInformationActivity extends Activity {
    private Button btsex1;
    private Button btsex2;
    private Button btportrait;
    private Button btaddress1;
    private  Button btaddress2;
    private Button btsecure;
    private LinearLayout linearLayout;
    private RoundImageView view;
    Bundle bundle=this.getIntent().getExtras();
    private Integer c;




    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        initView();
        linearLayout=(LinearLayout) findViewById(R.id.my_listed);
        view=(RoundImageView) linearLayout.findViewById(R.id.portrait);

        if(bundle!=null){
            c=bundle.getInt("Num");
        }
        btaddress2.setText(c);

    }
    private void initView(){
        btsex1=(Button) findViewById(R.id.sex_personal1);
        btsex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this,PersonalSexActivity.class);
                finish();
            }
        });
        btsex2=(Button) findViewById(R.id.sex_personal2);
        btsex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this, PersonalSexActivity.class);
                finish();
            }
        });
        btportrait=(Button) findViewById(R.id.portrait_personal);
        btportrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();

            }
        });
        btaddress1=(Button) findViewById(R.id.address_personal1);
        btaddress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this,PersonalAddressActivity.class);
                finish();

            }
        });
        btaddress2=(Button) findViewById(R.id.address_personal2);
        btaddress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this,PersonalAddressActivity.class);
                finish();
            }
        });
        btsecure=(Button) findViewById(R.id.account_security);
        btsecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PersonalInformationActivity.this,PersonalSecureActivity.class);
                finish();
            }
        });

    }
    private void showPopwindow() {
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.camera_pop, null);

        Button btnCamera = (Button) popView.findViewById(R.id.btn_camera_pop_camera);
        Button btnAlbum = (Button) popView.findViewById(R.id.btn_camera_pop_album);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// ���������

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_camera_pop_camera:
                        String state = Environment.getExternalStorageState();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 1);
                        } else {
                            Toast.makeText(getApplicationContext(),"请确认已插入SD卡",Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.btn_camera_pop_album:
                        Intent localIntent = new Intent();
                        localIntent.setType("image/*");
                        localIntent.setAction("android.intent.action.GET_CONTENT");
                        Intent localIntent2 = Intent.createChooser(localIntent, "选择图片");
                        startActivityForResult(localIntent2, 2);

                        break;
                    case R.id.btn_camera_pop_cancel:

                        break;
                }
                popWindow.dismiss();
            }
        };

        btnCamera.setOnClickListener(listener);
        btnAlbum.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

            FileOutputStream b = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/Image/"+name;

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try
            {
                view.setImageBitmap(bitmap);// 将图片显示在ImageView里
            }catch(Exception e)
            {
                Log.e("error", e.getMessage());
            }

        }
    }
}
