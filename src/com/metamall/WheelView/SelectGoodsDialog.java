package com.metamall.WheelView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.WheelView.adapters.AbstractWheelTextAdapter;
import com.metamall.WheelView.views.OnWheelChangedListener;
import com.metamall.WheelView.views.OnWheelScrollListener;
import com.metamall.WheelView.views.WheelView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/7.
 */
public class SelectGoodsDialog extends Dialog implements View.OnClickListener {
    private WheelView wvCondition;
    private WheelView wvOutcome;
    private View lySelectGoods;
    private View lySelectGoodsChild;
    private TextView btnSure;
    private TextView btnCancel;

    private Context context;
    private JSONObject mJsonObj;
    private String[] mConditionDatas;
    private Map<String, String[]> mOutcomeDatasMap = new HashMap<String, String[]>();

    private ArrayList<String> arrCondition = new ArrayList<String>();
    private ArrayList<String> arrOutcome = new ArrayList<String>();
    private AddressTextAdapter conditionAdapter;
    private AddressTextAdapter outcomeAdapter;

    private String strCondition = "条件";
    private String strOutcome = "结果";
    private OnSelectCListener onSelectCListener;

    private int maxsize = 24;
    private int minsize = 14;

    public SelectGoodsDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_select_goods);

        wvCondition = (WheelView) findViewById(R.id.wv_select_goods_conditions);
        wvOutcome = (WheelView) findViewById(R.id.wv_select_goods_outcome);
        lySelectGoods = findViewById(R.id.ly_myinfo_select_goods);
        lySelectGoodsChild = findViewById(R.id.ly_myinfo_select_goods_child);
        btnSure = (TextView) findViewById(R.id.btn_select_goods_sure);
        btnCancel = (TextView) findViewById(R.id.btn_select_goods_cancel);

        lySelectGoods.setOnClickListener(this);
        lySelectGoodsChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        initCondition();
        conditionAdapter = new AddressTextAdapter(context, arrCondition, getConditionItem(strCondition), maxsize, minsize);
        wvCondition.setVisibleItems(5);
        wvCondition.setViewAdapter(conditionAdapter);
        wvCondition.setCurrentItem(getConditionItem(strCondition));

        initOutcome(mOutcomeDatasMap.get(strCondition));
        outcomeAdapter = new AddressTextAdapter(context, arrOutcome, getOutcomeItem(strOutcome), maxsize, minsize);
        wvOutcome.setVisibleItems(5);
        wvOutcome.setViewAdapter(outcomeAdapter);
        wvOutcome.setCurrentItem(getOutcomeItem(strOutcome));

        wvCondition.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) conditionAdapter.getItemText(wheel.getCurrentItem());
                strCondition = currentText;
                setTextviewSize(currentText,conditionAdapter);
                String[] outcome = mOutcomeDatasMap.get(currentText);
                initOutcome(outcome);
                outcomeAdapter = new AddressTextAdapter(context, arrOutcome, 0, maxsize, minsize);
                wvOutcome.setVisibleItems(5);
                wvOutcome.setViewAdapter(outcomeAdapter);
                wvOutcome.setCurrentItem(0);
            }
        });

        wvCondition.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) conditionAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, conditionAdapter);
            }
        });

        wvOutcome.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) outcomeAdapter.getItemText(wheel.getCurrentItem());
                strOutcome = currentText;
                setTextviewSize(currentText, outcomeAdapter);
            }
        });

        wvOutcome.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) outcomeAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, outcomeAdapter);
            }
        });
    }

    private class AddressTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(24);
            } else {
                textvew.setTextSize(14);
            }
        }
    }

    public void setSelectCListener(OnSelectCListener onSelectCListener) {
        this.onSelectCListener = onSelectCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onSelectCListener != null) {
                onSelectCListener.onClick(strCondition, strOutcome);
            }
        } else if (v == btnCancel) {

        } else if (v == lySelectGoodsChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     *
     */
    public interface OnSelectCListener {
        public void onClick(String condition, String outcome);
    }

    /**
     * 从文件中读取地址数据
     */
    private void initJsonData(String m) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open(m);            ///////////换数据库//////////////////////////////////
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    private void initDatas(String m) {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray(m);        //////////////换数据库///////////////////////////////////
            mConditionDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);
                String condition = jsonP.getString("p");                                 ////////////////////////////////////

                mConditionDatas[i] = condition;

                JSONArray jsonCs = null;
                try {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                } catch (Exception e1) {
                    continue;
                }
                String[] mOutcomeDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");
                    mOutcomeDatas[j] = city;
                    JSONArray jsonAreas = null;
                    try {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    } catch (Exception e) {
                        continue;
                    }

                    String[] mAreasDatas = new String[jsonAreas.length()];
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("s");
                        mAreasDatas[k] = area;
                    }
                }
                mOutcomeDatasMap.put(condition, mOutcomeDatas);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    /**
     * 初始化商品
     */
    public void initCondition() {
        int length = mConditionDatas.length;
        for (int i = 0; i < length; i++) {
            arrCondition.add(mConditionDatas[i]);
        }
    }

    /**
     * 根据数据库，生成商品分类
     *
     * @param outcome
     */
    public void initOutcome(String[] outcome) {
        if (outcome != null) {
            arrOutcome.clear();
            int length = outcome.length;
            for (int i = 0; i < length; i++) {
                arrOutcome.add(outcome[i]);
            }
        } else {
            String[] outcomes = mOutcomeDatasMap.get("结果");
            arrOutcome.clear();
            int length = outcomes.length;
            for (int i = 0; i < length; i++) {
                arrOutcome.add(outcomes[i]);
            }
        }
        if (arrOutcome != null && arrOutcome.size() > 0
                && !arrOutcome.contains(strOutcome)) {
            strOutcome = arrOutcome.get(0);
        }
    }

    /**
     * 初始化地点
     *
     * @param condition
     * @param outcome
     */
    public void setSelect(String condition, String outcome) {
        if (condition != null && condition.length() > 0) {
            this.strCondition = condition;
        }
        if (outcome != null && outcome.length() > 0) {
            this.strOutcome = outcome;
        }
    }

    /**
     *
     *
     * @param condition
     * @return
     */
    public int getConditionItem(String condition) {
        int size = arrCondition.size();
        int conditionIndex = 0;
        boolean nocondition = true;
        for (int i = 0; i < size; i++) {
            if (condition.equals(arrCondition.get(i))) {
                nocondition = false;
                return conditionIndex;
            } else {
                conditionIndex++;
            }
        }
        if (nocondition) {
            strCondition = "条件";
            return 22;
        }
        return conditionIndex;
    }

    /**
     *
     *
     * @param outcome
     * @return
     */
    public int getOutcomeItem(String outcome) {
        int size = arrOutcome.size();
        int outcomeIndex = 0;
        boolean nooutcome = true;
        for (int i = 0; i < size; i++) {
            System.out.println(arrOutcome.get(i));
            if (outcome.equals(arrOutcome.get(i))) {
                nooutcome = false;
                return outcomeIndex;
            } else {
                outcomeIndex++;
            }
        }
        if (nooutcome) {
            strOutcome = "结果";
            return 0;
        }
        return outcomeIndex;
    }



}
