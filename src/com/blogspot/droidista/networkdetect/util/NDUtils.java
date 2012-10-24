package com.blogspot.droidista.networkdetect.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NDUtils {
	
	public boolean hasInternet(Context context){
		NetworkInfo networkInfo = (NetworkInfo) ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		
		if(networkInfo!=null &&
				(networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
				&& networkInfo.isConnected()) {
			return true;
		}		

		return false;
	}

}
