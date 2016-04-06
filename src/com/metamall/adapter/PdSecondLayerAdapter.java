package com.metamall.adapter;

import android.content.Context;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.metamall.R;
import com.metamall.model.ProductData;
import com.metamall.network.NetworkManager;

import java.util.List;

public class PdSecondLayerAdapter extends UniversalAdapter<ProductData> {

    public PdSecondLayerAdapter(Context context, List<ProductData> datas,
                                int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void updateItem(ViewHolder viewHolder, ProductData data) {
        NetworkImageView imageView = viewHolder
                .getView(R.id.iv_pd_second_layer);
        TextView tv_price = viewHolder.getView(R.id.tv_item_pd_price);
        TextView tv_info = viewHolder.getView(R.id.tv_item_pd_info);

        NetworkManager.getInstance().setImageUrl(imageView, data.getImgUrl());
        tv_price.setText("￥" + data.getPrice());
        tv_info.setText(data.getInfo());
    }

}
