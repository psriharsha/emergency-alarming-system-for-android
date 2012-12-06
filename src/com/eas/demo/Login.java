package com.eas.demo;

import com.eas.demo.RestClient.RequestMethod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{

	TextView username, password;
	Button login, register;
	Context context;
	Editor editor;
	SharedPreferences pref;
	public static final String PREF_TEXT = "session";
	public static final String PREF_ID = "idUser";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		pref = getSharedPreferences("EASPREF", 0);
        editor = pref.edit();
        editor.putString(PREF_TEXT, null);
        if(pref.getString(PREF_TEXT, null)!=null){
        	Intent direct = new Intent("com.eas.demo.Home");
        	startActivity(direct);
        }
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Logging In", Toast.LENGTH_SHORT).show();
				RestClient client = new RestClient("http://"+Singleton.ip+"/eas/index.php/Service/checkUser");
				client.AddParam("emailId", username.getText().toString());
				client.AddParam("password", password.getText().toString());
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);
	    		try {
	    		    client.Execute(RequestMethod.POST);
	    		} catch (Exception e) {
	    		    e.printStackTrace();
	    		}

	    		String response = client.getResponse();
	    		if(!response.contains("F"))
	    		{
	    			String para = "started";
			        editor.putString(PREF_TEXT, para);
			        editor.putString(PREF_ID, response);
			        editor.commit();
	    			Intent intHome = new Intent("com.eas.demo.Home");
	    			startActivity(intHome);
	    		}
	    		else
	    			Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
			}
		});
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intReg = new Intent("com.eas.demo.Registration");
				startActivity(intReg);
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}	
	
}
