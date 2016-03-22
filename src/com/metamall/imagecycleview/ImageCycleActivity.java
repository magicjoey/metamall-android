package com.metamall.imagecycleview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.metamall.R;

import java.util.ArrayList;



public class ImageCycleActivity extends Activity {
	private ImageCycleView mAdView;

	private ImageCycleView mAdView2;

	private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
	private ArrayList<ADInfo> infos2 = new ArrayList<ADInfo>();

	private String[] imageUrls = {"http://imgcache.chinayes.com/cnews/20120903/201209031233330705998.jpg",
			"http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg",
			"http://g.hiphotos.baidu.com/image/pic/item/738b4710b912c8fcf5454d13f9039245d78821bd.jpg",
			"http://i.ce.cn/ce/xwzx/gnsz/gdxw/201502/07/W020150207284918744995.jpg",
			"http://i8.hexunimg.cn/2015-06-16/176776640.jpg"};

	private String[] imageUrls2 = {
			"http://down1.sucaitianxia.com/psd02/psd158/psds27988.jpg",
			"http://pic2.ooopic.com/11/35/98/12bOOOPIC8f.jpg",
			"http://down1.sucaitianxia.com/psd02/psd158/psds28266.jpg",
			"http://pic02.sosucai.com/PSD/PSD_cd0267/b/PSD_cd0267_00016.jpg"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_cycle);
		/*mImageUrl = new ArrayList<String>();
		mImageUrl2 = new ArrayList<String>();*/
		for(int i=0;i < imageUrls.length; i ++){
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("top-->" + i);
			infos.add(info);
		}

		for(String str: imageUrls2){
			ADInfo info2 = new ADInfo();
			info2.setUrl(str);
			info2.setContent("bottom");
			infos2.add(info2);
		}

		mAdView = (ImageCycleView) findViewById(R.id.ad_view);
		mAdView2 = (ImageCycleView) findViewById(R.id.ad_view2);
		mAdView.setImageResources(infos, mAdCycleViewListener);
		mAdView2.setImageResources(infos2, mAdCycleViewListener);
	}

	private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			Toast.makeText(ImageCycleActivity.this, "content->"+info.getContent(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView) {
			com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageURL, imageView);// 使用ImageLoader对图片进行加装！
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		mAdView.startImageCycle();
	};

	@Override
	protected void onPause() {
		super.onPause();
		mAdView.pushImageCycle();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAdView.pushImageCycle();
	}
}
