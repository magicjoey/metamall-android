package com.metamall.Select_switch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.metamall.R;


public class ListViewFragment extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.listview, container, false);
		ListView listview = (ListView)view.findViewById(R.id.listview);
		String[] str = {"20","30","40","20"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), 
					android.R.layout.simple_list_item_1,str);
		listview.setAdapter(adapter);
		return view;
	}

}
