package com.metamall.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.adapter.SearchAutoAdapter;
import com.metamall.model.SearchAutoData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>.</p>
 *
 * @author Bells
 * @version SearchActivity.java 1.0 Created@2016-01-21 18:16 $
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    public static final String SEARCH_HISTORY = "search_history";
    private ListView mAutoListView;
    private TextView mSearchButtoon;
    private TextView mAutoEdit;
    private SearchAutoAdapter mSearchAutoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mSearchAutoAdapter = new SearchAutoAdapter(this, -1, this);
        //mAutoListView = (ListView) findViewById(R.id.auto_listview);
        mAutoListView.setAdapter(mSearchAutoAdapter);
        mAutoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                SearchAutoData data = (SearchAutoData) mSearchAutoAdapter.getItem(position);
                mAutoEdit.setText(data.getContent());
                mSearchButtoon.performClick();
            }
        });


        mSearchButtoon = (TextView) findViewById(R.id.home_top_ib_search);
        mSearchButtoon.setOnClickListener(this);
        mAutoEdit = (TextView) findViewById(R.id.home_top_et_search);
        mAutoEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mSearchAutoAdapter.performFiltering(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.home_top_ib_search) {//搜索按钮
            saveSearchHistory();
            mSearchAutoAdapter.initSearchHistory();
        } else {//"+"号按钮
            SearchAutoData data = (SearchAutoData) v.getTag();
            mAutoEdit.setText(data.getContent());
        }
    }

    /*
     * 保存搜索记录
     */
    private void saveSearchHistory() {
        String text = mAutoEdit.getText().toString().trim();
        if (text.length() < 1) {
            return;
        }
        SharedPreferences sp = getSharedPreferences(SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longhistory.split(",");
        ArrayList<String> history = new ArrayList<String>(
                Arrays.asList(tmpHistory));
        if (history.size() > 0) {
            int i;
            for (i = 0; i < history.size(); i++) {
                if (text.equals(history.get(i))) {
                    history.remove(i);
                    break;
                }
            }
            history.add(0, text);
        }
        if (history.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < history.size(); i++) {
                sb.append(history.get(i) + ",");
            }
            sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
        } else {
            sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
        }
    }

}
