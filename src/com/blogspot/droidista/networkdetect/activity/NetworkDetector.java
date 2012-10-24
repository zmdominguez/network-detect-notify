package com.blogspot.droidista.networkdetect.activity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.blogspot.droidista.networkdetect.R;
import com.blogspot.droidista.networkdetect.util.NDUtils;
import com.blogspot.droidista.networkdetect.util.SharedPrefsManager;

public class NetworkDetector extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		displayData();
	}

	private void displayData() {
		boolean hasInternet = (new NDUtils()).hasInternet(NetworkDetector.this);
		(new SharedPrefsManager()).saveNetworkState(NetworkDetector.this, hasInternet);
		
		TextView hasInternetText = (TextView)findViewById(R.id.textHasInternet);
		hasInternetText.setText(String.valueOf(hasInternet));
	}	

}
