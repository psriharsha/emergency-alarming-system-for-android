package com.eas.demo;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Home extends Activity{


	Editor editor;
	SharedPreferences pref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		pref = getSharedPreferences("EASPREF", 0);
        editor = pref.edit();
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("Tab1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Emergency???");
		th.addTab(specs);
		specs = th.newTabSpec("Tab2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Dash Board");
		th.addTab(specs);
		Button details = (Button)findViewById(R.id.edit_details);
		details.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openDetails = new Intent("com.eas.demo.Details");
				startActivity(openDetails);
			}
		});
		Button add_subs = (Button) findViewById(R.id.add_subs);
		add_subs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openSubs = new Intent("com.eas.demo.AddSubscription");
				startActivity(openSubs);
			}
		});
		Button values = (Button)findViewById(R.id.values);
		values.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openAcc = new Intent("com.eas.demo.Values");
				startActivity(openAcc);
			}
		});
		Button notifications = (Button)findViewById(R.id.notifications);
		notifications.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openNotify = new Intent("com.eas.demo.Notifications");
				startActivity(openNotify);
			}
		});
		Button subs = (Button) findViewById(R.id.subs);
		subs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openSub = new Intent("com.eas.demo.Subscribers");
				startActivity(openSub);
			}
		});
		Button locs = (Button) findViewById(R.id.loc);
		locs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openLoc = new Intent("com.eas.demo.Locations");
				startActivity(openLoc);
			}
		});
		Button settings = (Button) findViewById(R.id.settings);
		settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openSet = new Intent("com.eas.demo.Manage");
				startActivity(openSet);
			}
		});
		Button notes = (Button) findViewById(R.id.notes);
		notes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.clear();
				editor.commit();
				finishActivity();
				Intent intLogin = new Intent("com.eas.demo.Registration");
				startActivity(intLogin);
			}
		});
	}
	
	public void finishActivity()
	{
		this.finish();
	}
	
}
