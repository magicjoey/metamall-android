package com.metamall.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.Search.SearchActivity;
import com.metamall.model.SearchAutoData;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>.</p>
 *
 * @author Bells
 * @version SearchAutoAdapter.java 1.0 Created@2016-01-21 18:09 $
 */
public class SearchAutoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SearchAutoData> mOriginalValues;// 所有的Item
    private List<SearchAutoData> mObjects;// 过滤后的item
    private final Object mLock = new Object();
    private int mMaxMatch = 10;// 最多显示多少个选项,负数表示全部
    private View.OnClickListener mOnClickListener;

    public SearchAutoAdapter(Context context, int maxMatch,
                             View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mMaxMatch = maxMatch;
        this.mOnClickListener = onClickListener;
        initSearchHistory();
        mObjects = mOriginalValues;
    }

    @Override
    public int getCount() {
        Log.i("cyl", "getCount");
        return null == mObjects ? 0 : mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return null == mObjects ? 0 : mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AutoHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.auto_search_list_item, parent, false);
            holder = new AutoHolder();
            holder.content = (TextView) convertView
                    .findViewById(R.id.auto_content);
            holder.addButton = (TextView) convertView
                    .findViewById(R.id.auto_add);
            holder.autoImage = (TextView) convertView
                    .findViewById(R.id.auto_image);
            convertView.setTag(holder);
        } else {
            holder = (AutoHolder) convertView.getTag();
        }

        SearchAutoData data = mObjects.get(position);
        holder.content.setText(data.getContent());
        holder.addButton.setTag(data);
        holder.addButton.setOnClickListener(mOnClickListener);
        return convertView;
    }

    /**
     * 读取历史搜索记录
     */
    public void initSearchHistory() {
        SharedPreferences sp = mContext.getSharedPreferences(
                SearchActivity.SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SearchActivity.SEARCH_HISTORY, "");
        String[] hisArrays = longhistory.split(",");
        mOriginalValues = new ArrayList<SearchAutoData>();
        if (hisArrays.length == 1) {
            return;
        }
        for (int i = 0; i < hisArrays.length; i++) {
            mOriginalValues.add(new SearchAutoData().setContent(hisArrays[i]));
        }
    }

    /**
     * 匹配过滤搜索内容
     *
     * @param prefix
     *            输入框中输入的内容
     */
    public void performFiltering(CharSequence prefix) {
        if (prefix == null || prefix.length() == 0) {//搜索框内容为空的时候显示所有历史记录
            synchronized (mLock) {
                mObjects = mOriginalValues;
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            int count = mOriginalValues.size();
            ArrayList<SearchAutoData> newValues = new ArrayList<SearchAutoData>(
                    count);
            for (int i = 0; i < count; i++) {
                final String value = mOriginalValues.get(i).getContent();
                final String valueText = value.toLowerCase();
                if (valueText.contains(prefixString)) {

                }
                if (valueText.startsWith(prefixString)) {
                    newValues.add(new SearchAutoData().setContent(valueText));
                } else {
                    final String[] words = valueText.split(" ");
                    final int wordCount = words.length;
                    for (int k = 0; k < wordCount; k++) {
                        if (words[k].startsWith(prefixString)) {
                            newValues.add(new SearchAutoData()
                                    .setContent(value));
                            break;
                        }
                    }
                }
                if (mMaxMatch > 0) {
                    if (newValues.size() > mMaxMatch - 1) {
                        break;
                    }
                }
            }
            mObjects = newValues;
        }
        notifyDataSetChanged();
    }

    private class AutoHolder {
        TextView content;
        TextView addButton;
        TextView autoImage;
    }
}
