package com.metamall.manage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.util.Locale;

/**
 * 应用推荐模块，工具类
 * 
 * @author zhoujun
 * 
 */
public class RecommAppsUtils {
	public static final float STANDARD_DENSITYDPI = 240f; //标准屏幕的densityDpi
	
	// 应用推荐默认显示系统图标大小
	private static final int APP_ICON_WIDTH = 80;
	private static final int APP_ICON_HEIGHT = 80;



	/**
	 * 获取设备分辨�?
	 * 
	 * @param context
	 * @return
	 */
	public static String getDisplay(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wMgr.getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		return width + "*" + height;
	}





	/**
	 * 根据手机的分辨率�?dp 的单�?转成�?px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�?px(像素) 的单�?转成�?dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将bitmap转成drawable
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Context context, Drawable drawable) {

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}
}
