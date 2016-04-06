package com.metamall.adapter;

import android.content.Context;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.metamall.R;
import com.metamall.model.CategoryData;

import java.util.List;

public class CategoryLeftListAdapter extends UniversalAdapter<CategoryData> {

    public CategoryLeftListAdapter(Context context, List<CategoryData> datas,
                                   int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void updateItem(ViewHolder holder, CategoryData data) {
        holder.setTextViewText(R.id.item_category_left_tv, data.getName());
    }

}