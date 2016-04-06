package com.metamall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.metamall.R;
import com.metamall.model.HomeFloorData;
import com.metamall.model.ProductData;
import com.metamall.noscrollview.NoScrollGridView;
import com.metamall.utils.LogType;
import com.metamall.utils.LogUtil;

import java.util.List;

/**
 * 首页ListView的适配器
 */
public class HomeListAdapter extends UniversalAdapter<HomeFloorData> {

    public HomeListAdapter(Context context, List<HomeFloorData> datas,
                           int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void updateItem(ViewHolder holder, HomeFloorData data) {
        ((TextView) holder.getView(R.id.item_home_tv_floor)).setText(data.getFloor());
        if (data.getProducts() != null) {
            NoScrollGridView gridView = (NoScrollGridView) holder
                    .getView(R.id.item_home_grid);
            HomeGridAdapter adapter = new HomeGridAdapter(context,
                    data.getProducts(), R.layout.item_home_grid);
            gridView.setAdapter(adapter);
            // 设置商品的点击事件
            gridView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    ProductData product = (ProductData) parent.getItemAtPosition(position);
                    LogUtil.log(LogType.DEBUG, getClass(), product.getId()+"");
                }
            });
        }
    }

}