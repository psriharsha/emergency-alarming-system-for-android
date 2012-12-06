package com.eas.demo;

import com.eas.demo.RestClient.RequestMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity{

	EditText etemailId,etpassword,etfirstName,etlastName;
	String emailId,password,firstName,lastName,typeOfUser;
	Button registerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		etemailId = (EditText)findViewById(R.id.emailId);
		etpassword = (EditText)findViewById(R.id.registrationPassword);
		etfirstName = (EditText)findViewById(R.id.regFirstName);
		etlastName = (EditText)findViewById(R.id.regLastName);
		registerButton = (Button)findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				emailId = etemailId.getText().toString();
				password = etpassword.getText().toString();
				firstName = etfirstName.getText().toString();
				lastName = etlastName.getText().toString();
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);
				RestClient client = new RestClient("http://"+Singleton.ip+"/eas/index.php/Service/register");
				client.AddParam("emailId", emailId);
				client.AddParam("password", password);
				client.AddParam("firstName", firstName);
				client.AddParam("lastName", lastName);
				client.AddParam("typeOfUser", "both");
	    		try {
	    		    client.Execute(RequestMethod.POST);
	    		} catch (Exception e) {
	    		    e.printStackTrace();
	    		}

	    		String response = client.getResponse();
	    		if(response.contains("S"))
	    		{
	    			Intent intHome = new Intent(".Login");
	    			startActivity(intHome);
	    		}
	    		else if(response.contains("U"))
	    			Toast.makeText(getApplicationContext(), "Username already registered", Toast.LENGTH_SHORT).show();
	    		else
	    			Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
			}
		});
	}

	
	
}
