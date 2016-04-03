package com.metamall.activity.Personal;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.Clutter.UserEntity;
import com.metamall.R;
import com.metamall.activity.Personal.ChinaCity.AddressBaseActivity;
import com.metamall.adapter.ArrayWheelAdapter;
import com.metamall.adapter.OnWheelChangedListener;
import com.metamall.adapter.WheelView;

/**
 * Created by Administrator on 2016/3/30.
 */
public class PersonalAddAddressActivity extends AddressBaseActivity {
    private ImageButton ibback;
    private EditText etname;
    private EditText etnumber;
    private Button btaddress;
    private EditText etaddress_details;
    private ImageButton ibrecognition;
    private Button btaddressKeep;
    private MetaApp metaApp;

    WheelView mViewProvince = (WheelView) findViewById(R.id.id_province);
    WheelView mViewCity = (WheelView) findViewById(R.id.id_city);
    WheelView mViewDistrict = (WheelView) findViewById(R.id.id_district);
    Button mBtnConfirm = (Button) findViewById(R.id.btn_confirm);



    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addresss);
        initView();

    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.address_ib_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        etname=(EditText) findViewById(R.id.address_person_name);
        etname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etnumber=(EditText) findViewById(R.id.address_person_number);
        etnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String etnumber1=etnumber.toString().trim();
                String head1=etnumber1.substring(0,3);  // "hamburger".substring(4, 8) returns "urge"//"smiles".substring(1, 5) returns "mile"
                if(etnumber1.length()!=11){
                    Toast.makeText(getApplicationContext(),"请输入十一位手机号码",Toast.LENGTH_LONG).show();
                }
                else{
                    if(head1.equals("135") || head1.equals("136")
                            || head1.equals("137") || head1.equals("138")
                            || head1.equals("139") || head1.equals("147")
                            || head1.equals("150") || head1.equals("151")
                            || head1.equals("152") || head1.equals("157")
                            || head1.equals("158") || head1.equals("159")
                            || head1.equals("182") || head1.equals("187")
                            || head1.equals("188") ||head1.equals("134")
                            ||head1.equals("130") || head1.equals("131")
                            || head1.equals("132") || head1.equals("145")
                            || head1.equals("155") || head1.equals("156")
                            || head1.equals("185") || head1.equals("186")
                            ||head1.equals("133") || head1.equals("153")
                            || head1.equals("180") || head1.equals("189")){
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"请检查手机号格式",Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etaddress_details=(EditText) findViewById(R.id.address_verbose_details);
        etaddress_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btaddress=(Button) findViewById(R.id.address_verbose);
        btaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow();
               //todo
            }
        });
        ibrecognition=(ImageButton) findViewById(R.id.slient_recognition_address_no);
        ibrecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                if(count%2==1){
                    ibrecognition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_yes));
                    metaApp=(MetaApp)getApplication();
                    metaApp.setRecognition("Recognition");
                }
                else{
                    ibrecognition.setImageDrawable(getResources().getDrawable(R.drawable.btn_psd_no));
                }
            }
        });
        btaddressKeep=(Button) findViewById(R.id.address_keep);


        if(etname.getText().length()!=0||etnumber.getText().length()!=0||etaddress_details.getText().length()!=0
                ||mViewProvince.getItemsCount()!=0) {

            btaddressKeep.setBackgroundColor(Color.YELLOW);
            btaddressKeep.setEnabled(true);
            btaddressKeep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mname=etname.getText().toString();
                    String mnumber=etnumber.getText().toString();
                    String maddressDetails=etaddress_details.getText().toString();
                    String mProvince=mViewProvince.toString();
                    String mCity=mViewCity.toString();
                    String mDistrict=mViewDistrict.toString();
                    UserEntity user = new UserEntity();
                    user.setUserName(mname);
                    user.setdistrict(mDistrict);
                    user.setProvince(mProvince);
                    user.setCity(mCity);
                    user.setnumber(mnumber);
                    user.setaddressDetails(maddressDetails);



                    Intent in=new Intent();
                    in.setClass(PersonalAddAddressActivity.this,PersonalAddressActivity.class);
                    finish();
                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else{
            btaddressKeep.setEnabled(false);
        }




    }
    private void initPopWindow(){

        View popView = View.inflate(this, R.layout.address_popupWindows, null);
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        mViewProvince.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {

                updateCities();
            }
        });
        mViewCity.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateAreas();
            }
        });
        mViewDistrict.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
                mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
            }
        });


        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        double height1=height/3;
        int i=Integer.parseInt(new java.text.DecimalFormat("0").format(height1));//四舍五入
        final PopupWindow popWindow = new PopupWindow(popView,width,i);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();





            }
        });


        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setOutsideTouchable(true);
        backgroundAlpha(1f);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popWindow.dismiss();
                    popWindow.setOnDismissListener(new poponDismissListener());
                    return true;
                }
                return false;
            }
        });

        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }




    /**
     * ���ݵ�ǰ���У�������WheelView����Ϣ
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * ���ݵ�ǰ��ʡ��������WheelView����Ϣ
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

}










