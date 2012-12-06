package com.eas.demo;

import com.eas.demo.RestClient.RequestMethod;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubscription extends Activity{

	SharedPreferences pref;
	EditText ettoMail;
	String toMail,permission,status,idUser;
	CheckBox loc,act;
	public static final String PREF_ID = "idUser";
	Button sendReq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_subscription);
		pref = getSharedPreferences("EASPREF", MODE_WORLD_WRITEABLE);
		idUser = pref.getString(PREF_ID, null);
		ettoMail = (EditText)findViewById(R.id.carer_mail);
		loc = (CheckBox) findViewById(R.id.check_loc);
		sendReq = (Button)findViewById(R.id.send_req);
		sendReq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toMail = ettoMail.getText().toString();
					permission = "activity";
				if(loc.isChecked())
					permission = "both";
				status = "false";
				RestClient client = new RestClient("http://"+Singleton.ip+"/eas/index.php/Service/addSubscription");
				client.AddParam("idUser", idUser);
				client.AddParam("toMail",toMail);
				client.AddParam("permission",permission);
				client.AddParam("status", status);
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);
	    		try {
	    		    client.Execute(RequestMethod.POST);
	    		} catch (Exception e) {
	    		    e.printStackTrace();
	    		}
	    		String response = client.getResponse();
	    		if(response.contains("S"))
	    		{
	    			Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_LONG);
	    			Intent intHome = new Intent("com.eas.demo.Home");
	    			startActivity(intHome);
	    		}
	    		else if(response.contains("T"))
	    			Toast.makeText(getApplicationContext(), "Subscription already exists", Toast.LENGTH_SHORT).show();
	    		else
	    			Toast.makeText(getApplicationContext(), "Try ding chaka", Toast.LENGTH_SHORT).show();
	    			
			}
		});
	}

}
