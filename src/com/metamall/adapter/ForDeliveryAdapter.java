package com.metamall.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import com.android.volley.toolbox.NetworkImageView;
import com.metamall.activity.DrawbackActivity;
import com.metamall.activity.ForDeliveryActivity;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.metamall.R;
import com.metamall.model.ProductData;
import com.metamall.network.NetworkManager;

import java.util.List;

public class ForDeliveryAdapter extends UniversalAdapter<ProductData> {





    public ForDeliveryAdapter(Context context, List<ProductData> datas,
                           int itemLayoutId) {
        super(context, datas, itemLayoutId);

    }

    @Override
    public void updateItem(final ViewHolder holder, final ProductData data) {

        NetworkImageView iv = holder.getView(R.id.item_for_delivery_iv);
        NetworkManager.getInstance().setImageUrl(iv, data.getImgUrl());
        holder.setTextViewText(R.id.item_for_delivery_tv_info, data.getInfo());
        holder.setTextViewText(R.id.item_for_delivery_tv_price, "￥" + data.getPrice());

        Button btnDrawback = holder.getView(R.id.item_for_delivery_btn_drawback);
        btnDrawback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(ForDeliveryActivity.forDeliveryActivity, DrawbackActivity.class);
                ForDeliveryActivity.forDeliveryActivity.startActivity(i);
                ForDeliveryActivity.forDeliveryActivity.finish();
            }
        });
        Button btnPress = holder.getView(R.id.item_for_delivery_btn_press);
        btnPress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SENDTO);
                String[] recipients = new String[]{"332866209@qq.com"};
                i.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"订单");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "催单啦："+data.getInfo()+data.getPrice());
                i.setType("text/plain");
                ForDeliveryActivity.forDeliveryActivity.startActivity(Intent.createChooser(i, "正在发送，请稍候..."));
                ForDeliveryActivity.forDeliveryActivity.finish();
            }
        });

    }






}
