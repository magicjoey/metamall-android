package com.metamall.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.*;
import com.android.volley.toolbox.NetworkImageView;
import com.metamall.R;
import com.metamall.activity.CompletedActivity;
import com.metamall.activity.UserCommentActivity;
import com.metamall.model.ProductData;
import com.metamall.network.NetworkManager;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CompletedAdapter extends UniversalAdapter<ProductData> {


    private SparseIntArray nums;



    public CompletedAdapter(Context context, List<ProductData> datas,
                           int itemLayoutId) {
        super(context, datas, itemLayoutId);
        nums = new SparseIntArray();
    }

    @Override
    public void updateItem(final ViewHolder holder, final ProductData data) {

        NetworkImageView iv = holder.getView(R.id.item_completed_iv);
        NetworkManager.getInstance().setImageUrl(iv, data.getImgUrl());
        holder.setTextViewText(R.id.item_completed_tv_info, data.getInfo());
        holder.setTextViewText(R.id.item_completed_tv_price, "￥" + data.getPrice());
        Button btnAgain = holder.getView(R.id.item_completed_btn_buy_again);
        Button btnComment = holder.getView(R.id.item_completed_btn_comment);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:加入购物车
                Toast.makeText(context,"已加入购物车",Toast.LENGTH_SHORT).show();
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(CompletedActivity.completedActivity, UserCommentActivity.class);
                CompletedActivity.completedActivity.startActivity(i);
                CompletedActivity.completedActivity.finish();

            }
        });


    }



    public SparseIntArray getNums() {
        return nums;
    }





}