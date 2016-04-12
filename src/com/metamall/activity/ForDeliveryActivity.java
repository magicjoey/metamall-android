package com.metamall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.Application.MetaApp;
import com.metamall.R;
import com.metamall.adapter.CartListAdapter;
import com.metamall.model.Global;
import com.metamall.model.ProductData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/16.
 */
public class ForDeliveryActivity extends CartActivity {
    private ImageButton ibback;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fordelivery);
        initView();
    }


    MetaApp metaApp;

    /**
     * 顶部
     */
    private Button btnEdit;
    /**
     * 提示登录
     */
    private RelativeLayout suggestLayout;
    private Button btnLogin;
    /**
     * 购物车为空
     */
    private TextView tvEmpty;
    /**
     * 购物车列表
     */
    private LinearLayout listLayout;
    private ListView listView;
    private CartListAdapter adapter;
    private ArrayList<ProductData> products;
    /**
     * 底部付款
     */
    private RelativeLayout checkLayout;
    private CheckBox cbCheckAll;
    private TextView tvTotal;
    private Button btnBuy;



    @Override
    public void onStart() {
        super.onStart();
        if (Global.isLogin) {
            // 已登录
            // 隐藏提示登录的布局
            suggestLayout.setVisibility(View.GONE);
            getCartProducts();
        } else {
            // 未登录
            // 隐藏编辑按钮
            btnEdit.setVisibility(View.GONE);
            // 隐藏购物车列表
            listLayout.setVisibility(View.GONE);
            // 隐藏底部付款布局
            checkLayout.setVisibility(View.GONE);
            // 显示提示登录的布局
            suggestLayout.setVisibility(View.VISIBLE);
            // 显示购物车为空的布局
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化视图
     *
     * @param
     *
     */
    private void initView() {
        ibback=(ImageButton) findViewById(R.id.ib_back_for_deliveryed);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEdit = (Button) findViewById(R.id.for_delivery_setting);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEdit.getText().toString()
                        .equals(getResources().getString(R.string.edit))) {
                    btnEdit.setText(getResources().getString(
                            R.string.accomplish));
                    // 全部取消选中
                    cbCheckAll.setChecked(false);
                    // 隐藏价格总数文本框
                    tvTotal.setVisibility(View.INVISIBLE);
                    // 将结算按钮改成删除按钮
                    btnBuy.setText(getResources().getString(R.string.delete));
                    // 完成按钮
                } else {
                    btnEdit.setText(getResources().getString(R.string.edit));
                    // 全部选中
                    cbCheckAll.setChecked(true);
                    // 显示价格总数文本框
                    tvTotal.setVisibility(View.VISIBLE);
                    // 将删除按钮改成结算按钮
                    btnBuy.setText(getResources().getString(R.string.buy));
                }
            }
        });
        suggestLayout = (RelativeLayout) findViewById(R.id.for_delivery_suggest_layout);
        btnLogin = (Button) findViewById(R.id.for_delivery_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForDeliveryActivity.this,LoginActivity.class);
                startActivity(intent);
                ForDeliveryActivity.this.finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        tvEmpty = (TextView) findViewById(R.id.for_delivery_tv_empty);
        listLayout = (LinearLayout) findViewById(R.id.for_delivery_list_layout);
        listView = (ListView) findViewById(R.id.for_delivery_lv);
        listView.setAdapter(adapter);
        checkLayout = (RelativeLayout) findViewById(R.id.for_delivery_check_layout);
        cbCheckAll = (CheckBox) findViewById(R.id.for_delivery_cb_all);
        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // 全选监听
                for (int i = 0; i < products.size(); i++) {
                    adapter.getIsChecked().put(i, isChecked);
                }
                adapter.notifyDataSetChanged();
            }
        });
        tvTotal = (TextView) findViewById(R.id.for_delivery_tv_total);
        btnBuy = (Button) findViewById(R.id.for_delivery_btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 结算按钮
                if (btnBuy.getText().toString()
                        .equals(getResources().getString(R.string.buy))) {

                    // 删除按钮
                } else {

                }
            }
        });
        adapter.setOnPriceChangedListener(new CartListAdapter.OnPriceChangedListener() {

            @Override
            public void onPriceChanged(final float price) {
                tvTotal.post(new Runnable() {



                    @Override
                    public void run() {
                        tvTotal.setText("合计：￥" + price);
                    }
                });

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        products = new ArrayList<ProductData>();
        adapter = new CartListAdapter(this, products,
                R.layout.item_cart_lv);
    }

    /**
     * 从服务器获取购物车商品数据
     */
    private void getCartProducts() {
        ArrayList<ProductData> list = new ArrayList<ProductData>();
        for (int i = 0; i < 5; i++) {
            ProductData data = new ProductData();
            data.setId(i);
            data.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
            data.setInfo("上岛咖啡上岛咖啡上岛咖啡上岛咖啡上岛咖啡上岛咖啡");
            data.setPrice(120);
            list.add(data);
        }
        products.clear();
        products.addAll(list);
        for (int i = 0; i < products.size(); i++) {
            adapter.getIsChecked().put(i, true);
            adapter.getNums().put(i, 1);
        }
        cbCheckAll.setChecked(true);
        if (products.size() == 0) {
            // 购物车为空
            // 隐藏编辑按钮
            btnEdit.setVisibility(View.GONE);
            // 隐藏购物车列表布局
            listLayout.setVisibility(View.GONE);
            // 隐藏底部结算布局
            checkLayout.setVisibility(View.GONE);
            // 显示购物车为空的布局
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            // 购物车不为空
            // 隐藏购物车为空的布局
            tvEmpty.setVisibility(View.GONE);
            // 显示编辑按钮
            btnEdit.setVisibility(View.VISIBLE);
            // 显示购物车列表布局
            listLayout.setVisibility(View.VISIBLE);
            // 显示底部结算布局
            checkLayout.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
            tvTotal.setText("合计：￥" + adapter.calculatePrice());
        }
    }



}
