package com.metamall.utils;

/**
 * FragmentManager存储Fragment用的tag的枚举
 * tag表示Fragment的完整类名
 */
public enum FragmentTag {
	
	TAG_HOME("com.metamall.fragment.HomeFragment"),
	TAG_CATEGORY("com.metamall.fragment.CategoryFragment"),
	TAG_SCAN("com.metamall.fragment.ScanFragment"),
	TAG_CART("com.metamall.fragment.CartFragment"),
	TAG_MY("com.metamall.fragment.MyFragment");
	
	String tag;
	
	private FragmentTag(String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return tag;
	}
}
