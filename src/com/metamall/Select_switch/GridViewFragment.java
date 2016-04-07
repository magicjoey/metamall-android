package com.metamall.Select_switch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.metamall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewFragment extends Fragment{

	 //网格视图和列表视图的数据
    private int[] imagesID = new int[]{R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher};
    private String[] str1 = {"XIN","COLORWAYS","SNAP","PEOCOCK"};
    private String[] str2 = {"IPHONE4/4S","IPHONE4/4S","IPHONE4/4S","IPHONE4/4S"};
    private String[] str3 = {"30","35","25","40"};
    private List<HashMap<String,Object>> grid_list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.gridciew, container,false);
		GridView gridview = (GridView)view.findViewById(R.id.gridview);
		grid_list = getListItems();
		ListAdapter adapter = new GridViewAdapter(getActivity().getApplicationContext(), 
				imagesID,grid_list);
		gridview.setAdapter(adapter);
		return view;
	}

	public List<HashMap<String,Object>> getListItems(){
        List<HashMap<String,Object>> list1 = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<str1.length;i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("text1",str1[i]);
            map.put("text2",str2[i]);
            map.put("text3",str3[i]);
            list1.add(map);
        }
        return list1;
    }
	
}
