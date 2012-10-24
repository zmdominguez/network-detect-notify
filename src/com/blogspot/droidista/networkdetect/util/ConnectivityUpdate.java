package com.blogspot.droidista.networkdetect.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.blogspot.droidista.networkdetect.R;
import com.blogspot.droidista.networkdetect.activity.NetworkDetector;

public class ConnectivityUpdate extends BroadcastReceiver {
	
	private static final int MY_NOTIFICATION_ID = 100;
	
	private static final String LOG_TAG = "ZBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		// Wifi = ConnectivityManager.TYPE_WIFI
		// Mobile =  ConnectivityManager.TYPE_MOBILE
		// For mobile: netSubtype == TelephonyManager.NETWORK_TYPE_UMTS --> 3G
		// For mobile: !mTelephony.isNetworkRoaming())

		boolean hasInternet = false;

		Log.d(LOG_TAG, "Received broadcast!");
		
		if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			hasInternet = (new NDUtils()).hasInternet(context);
		}

		SharedPrefsManager prefs = new SharedPrefsManager();
		boolean prevValue = prefs.hasNetwork(context);
		
		if(prevValue != hasInternet){
			(new SharedPrefsManager()).saveNetworkState(context, hasInternet);
			sendNotification(hasInternet, context);
		}
	}

	private void sendNotification(boolean hasInternet, Context context) {
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		
		String notificationTitle = "Change in data connection!";
		String notificationText = "We have internet?: " + (hasInternet ? "YES!" : "NO :(");
		Notification myNotification = new Notification(R.drawable.ic_launcher, "Broadcast received!", System.currentTimeMillis());
		
		
		Intent myIntent = new Intent(context.getApplicationContext(), NetworkDetector.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		myNotification.setLatestEventInfo(context,
				notificationTitle,
				notificationText,
				pendingIntent);
		notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

	}

// enabling and disabling receivers programmatically = http://developer.android.com/training/monitoring-device-state/manifest-receivers.html
}
