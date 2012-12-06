package com.eas.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

public class ActivityService extends Service{

	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private SensorEventListener mSensorEventListener;
	Editor editor;
	SharedPreferences pref;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class ActListener implements SensorEventListener{

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			float x = Math.round(event.values[0]);
			float y = Math.round(event.values[1]);
			float z = Math.round(event.values[2]);
			if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			mInitialized = true;

			} else {
			if(Math.abs(mLastX-x)>=1)
				mLastX = x;
			if(Math.abs(mLastY-y)>=1)
				mLastY = y;
			if(Math.abs(mLastZ-z)>=1)
				mLastZ = z;
			
			if((Math.abs(mLastX-x)> 10)||(Math.abs(mLastY-y)> 10)||(Math.abs(mLastZ-z)> 10))
				notGood();
			pref = getSharedPreferences("EASPREF", 0);
	        editor = pref.edit();
	        x += Singleton.x;
	        y += Singleton.y;
	        z += Singleton.z;
	        Singleton.times++;
	        Singleton.x = x;
	        Singleton.y = y;
	        Singleton.z = z;
			}
		}

		public void notGood(){
			Intent go = new Intent("com.eas.demo.Home");
			startActivity(go);
		}
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
			mInitialized = false;
		 	mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        mSensorEventListener = new ActListener();
	        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mSensorManager.unregisterListener(mSensorEventListener,mAccelerometer);
	}
	
	

}
