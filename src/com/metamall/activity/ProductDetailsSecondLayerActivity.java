package com.metamall.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.metamall.R;
import com.metamall.adapter.PdSecondLayerAdapter;
import com.metamall.model.ProductData;
import com.metamall.noscrollview.NoScrollListView;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

public class ProductDetailsSecondLayerActivity extends Activity {
	/**
	 * 显示商品信息的listview
	 * */
	private NoScrollListView listView;
	/**
	 * 存储url的容器
	 * */
	private ArrayList<String> urls = new ArrayList<String>();

	/**
	 * listview的数据
	 * */
	private ArrayList<ProductData> list_datas = new ArrayList<ProductData>();
    /**
     * popupWindow
     */
    private PopupWindow mPopWindow;
    /**
	 * 二级分类的适配器
	 * */
	private PdSecondLayerAdapter adapter;
    private PdSecondLayerAdapter adapter1;

	/**
	 * buttons
	 * */
	private ImageButton btn_back;
	private Button btn_total; // 综合
	private Button btn_sales; // 销量
	private Button btn_price; // 价格
	private Button btn_select; // 筛选
    private ImageButton btn_switchStyle;//转变显示方式
    /**
     * 记录按钮点击次数
     */
    private int count;
    /**
     * 布局隐藏
     *
     */
    private GridLayout gridLayout;
    private LinearLayout linearLayout;
    /**
     * populayout
     */
    private Button btnson1;
    private Button btnson2;
    private ScrollView scrollView1;
    private ScrollView scrollView2;


	/**
	 * 控制价格图标上下箭头的标志位
	 * */
	private boolean flag_price_transition = false;
	private Drawable[] layers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetails_second_layer);
		initView();
		initButtons();
		initAdapter();
	}

	/**
	 * 初始化布局
	 * */
	private void initView() {
		listView = (NoScrollListView) findViewById(R.id.lv_pd_second_layer);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(
						ProductDetailsSecondLayerActivity.this,
						ProductDetailsThirdLayerActivity.class));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 得到url
	 * */
	private String getUrls() {
		String urlString = "http://i2.sinaimg.cn/IT/h/2009-12-05/1259942752_UQ03Yv.jpg";
		return urlString;
	}

	/**
	 * 得到listview数据
	 * */
	private void getListData() {
		for (int i = 0; i < 10; i++) {
			ProductData data = new ProductData();
			data.setId(i);
			data.setImgUrl(getUrls());
			data.setInfo("樱桃（Cherry） G80-3060HLCUS-2 红轴黑橙二色键帽 60周年限量版机械键盘");
			data.setPrice(1953);
			list_datas.add(data);
		}
	}

	/**
	 * 初始化 适配器
	 * */
	private void initAdapter() {
		getListData();
		adapter = new PdSecondLayerAdapter(this, list_datas,
				R.layout.item_pd_second_layer);
		listView.setAdapter(adapter);
        adapter1=new PdSecondLayerAdapter(this,list_datas,R.layout.item_pd_fouth_layer);
	}

	/**
	 * 初始化buttons
	 * */
	private void initButtons() {
		btn_back = (ImageButton) findViewById(R.id.btn_pd_second_back);
		btn_back.setFocusable(true);
		btn_back.setFocusableInTouchMode(true);
		btn_back.requestFocus();
		btn_back.requestFocusFromTouch();
		btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(ProductDetailsSecondLayerActivity.this,HomeActivity.class);
                finish();
            }
        });

		btn_price = (Button) findViewById(R.id.btn_pd2_price);
		btn_sales = (Button) findViewById(R.id.btn_pd2_sales);
		btn_select = (Button) findViewById(R.id.btn_pd2_select);
		btn_total = (Button) findViewById(R.id.btn_pd2_total);

		// 首次进入
		btn_sales.setTextColor(Color.RED);
		layers = new Drawable[3];
		layers[0] = getResources().getDrawable(
				R.drawable.pd_button_price_normal);
		layers[1] = getResources().getDrawable(R.drawable.pd_button_price_up);
		layers[2] = getResources().getDrawable(R.drawable.pd_button_price_down);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_pd2_price: {
					if (!flag_price_transition) {
						btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
								null, layers[1], null);
						flag_price_transition = true;
					} else {
						btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
								null, layers[2], null);
						flag_price_transition = false;
					}
					btn_sales.setTextColor(Color.BLACK);
					btn_price.setTextColor(Color.RED);
					break;
				}
				case R.id.btn_pd2_sales: {
					btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
							null, layers[0], null);
					flag_price_transition = false;
					btn_price.setTextColor(Color.BLACK);
					btn_sales.setTextColor(Color.RED);
					break;
				}
                 case R.id.btn_pd2_select:{



                    }
				case R.id.btn_pd_second_back:
					finish();
					break;
				default:
					break;
				}
			}
		};

		btn_price.setOnClickListener(listener);
		btn_sales.setOnClickListener(listener);
		btn_total.setOnClickListener(listener);
        gridLayout=(GridLayout) findViewById(R.id.switch_grid);
        linearLayout=(LinearLayout) findViewById(R.id.switch_linear);
        btn_switchStyle=(ImageButton) findViewById(R.id.switchStyle);
        btn_switchStyle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%2==1){
                    btn_switchStyle.setBackgroundResource(R.drawable.selector_press_list);
                    gridLayout.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    listView.setAdapter(adapter1);
                }
                else{
                    btn_switchStyle.setBackgroundResource(R.drawable.selector_press_grid);
                    gridLayout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                    listView.setAdapter(adapter);
                }


            }
        });
        btn_select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
	}
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ProductDetailsSecondLayerActivity.this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        scrollView1=(ScrollView) findViewById(R.id.son_son_popuplayout1);
        scrollView2=(ScrollView) findViewById(R.id.son_son_popuplayout2);
        btnson1=(Button) findViewById(R.id.son_popuplayout1);
        btnson1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView1.setVisibility(View.VISIBLE);
                scrollView2.setVisibility(View.INVISIBLE);
            }
        });
        btnson2=(Button) findViewById(R.id.son_popuplayout2);
        btnson2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView1.setVisibility(View.INVISIBLE);
                scrollView2.setVisibility(View.VISIBLE);
            }
        });
        //todo:confirm classify;

        //显示PopupWindow
		View rootview = LayoutInflater.from(ProductDetailsSecondLayerActivity.this)
                .inflate(R.layout.activity_productdetails_second_layer, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }



}
