package com.eas.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manage extends Activity{

	Button startApp,stopApp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage);
		final Intent intentService = new Intent("com.eas.demo.SendService");
		final Intent actService = new Intent("com.eas.demo.ActivityService");
		final Intent locService = new Intent("com.eas.demo.LocService");
		startApp = (Button)findViewById(R.id.startService);
		stopApp = (Button)findViewById(R.id.stopService);
		startApp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(actService);
				//startService(locService);
				startService(intentService);
			}
		});
		stopApp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(intentService);
				stopService(locService);
				stopService(actService);
			}
		});
	}

	
	
}
