package com.eas.demo;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.widget.Toast;

import com.eas.demo.RestClient.RequestMethod;

public class SendService extends Service{
	SharedPreferences pref;
	String idUser;
    protected LocationManager locationManager;
	public static final String PREF_ID = "idUser";
	private final Handler handler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {

	}
	};
	protected Editor editor;
	public void onDestroy() {
	    super.onDestroy();
	//  Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
	    handler.removeCallbacks(sendUpdatesToWeb);   
	}
	
	private Runnable sendUpdatesToWeb = new Runnable() {
        public void run() {
            /// Any thing you want to do put the code here like web service procees it will run in ever 1 second

        	double actX,actY,actZ,latitude,longitude;
        	pref = getSharedPreferences("EASPREF", 0);
    		idUser = pref.getString(PREF_ID, null);
    		RestClient client = new RestClient("http://"+Singleton.ip+"/eas/index.php/Service/addDetails");
    		int times = Singleton.times;
	        actX = Singleton.x/times;
	        actY = Singleton.y/times;
	        actZ = Singleton.z/times;
	        latitude = Singleton.lat;
	        longitude = Singleton.lon;
    		Singleton.x = 0;
    		Singleton.y = 0;
    		Singleton.z = 0;
    		Singleton.times = 0;
	        client.AddParam("idUser", idUser);
	        client.AddParam("actX", ""+actX);
	        client.AddParam("actY", ""+actY);
	        client.AddParam("actZ", ""+actZ);
	        client.AddParam("latitude", ""+latitude);
	        client.AddParam("longitude", ""+longitude);
    		try {
    		    client.Execute(RequestMethod.POST);
    		} catch (Exception e) {
    		    e.printStackTrace();
    		}

    		String response = client.getResponse();
            handler.postDelayed(this, 1000); // 1 seconds
        }
 };
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        handler.removeCallbacks(sendUpdatesToWeb);
        handler.postDelayed(sendUpdatesToWeb, 1000); 
		return START_STICKY;
	}
	

}
