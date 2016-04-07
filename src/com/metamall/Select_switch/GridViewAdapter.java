package com.metamall.Select_switch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.metamall.R;

import java.util.HashMap;
import java.util.List;

/**
 *
 */

public class GridViewAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private int[] imagesID;
    private List<HashMap<String,Object>> grid_list;

    public GridViewAdapter(Context context,int[] imagesID,List<HashMap<String,Object>> grid_list){
        this.context = context;
        this.imagesID = imagesID;
        this.grid_list = grid_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imagesID.length;
    }

    @Override
    public Object getItem(int i) {
        return imagesID[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemView listItemView = null;
        if(view == null){
            listItemView = new ListItemView();
            view = inflater.inflate(R.layout.layout_gridview,null);
            listItemView.grid_text1 = (TextView)view.findViewById(R.id.grid_text1);
            listItemView.grid_text2 = (TextView)view.findViewById(R.id.grid_text2);
            listItemView.grid_text3 = (TextView)view.findViewById(R.id.grid_text3);
            view.setTag(listItemView);
        }else{
            listItemView = (ListItemView)view.getTag();
        }
        listItemView.grid_text1.setText(grid_list.get(i).get("text1").toString());
        Drawable drawable = context.getResources().getDrawable(imagesID[i]);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        listItemView.grid_text1.setCompoundDrawables(null,drawable,null,null);
        listItemView.grid_text2.setText(grid_list.get(i).get("text2").toString());
        listItemView.grid_text3.setText(grid_list.get(i).get("text3").toString());

        return view;
    }

    public class ListItemView{
        public TextView grid_text1;
        public TextView grid_text2;
        public TextView grid_text3;
    }
}
