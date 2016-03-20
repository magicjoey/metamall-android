package com.metamall.manage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * 
 * <br>类描�?精品用户手机信息获取工具�?
 * <br>功能详细描述:
 * 
 * @author  zhouxuewen
 * @date  [2012-9-12]
 */
public class GoStorePhoneStateUtil {



	/**
	 * �?��手机WIFI有没有打�?��方法
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnable(Context context) {
		boolean result = false;
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()
						&& networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
					result = true;
				}
			}
		}
		return result;
	}




	/**
	 * 判断SDCard是否可以读写的方�?如果没有SDCard则返回false
	 * 
	 * @return 如果可以读写，则返回true,否则返回false
	 */
	public static boolean isSDCardAccess() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	/**
	 * �?��手机网络是否可用的方�?
	 * @return 可用返回TRUE,否则返回FALSE
	 */
	public static boolean isNetWorkAvailable(Context context) {
		boolean result = false;
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					result = true;
				}
			}
		}
		return result;
	}


}
