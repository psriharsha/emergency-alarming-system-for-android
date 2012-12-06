package com.eas.demo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Values extends Activity implements SensorEventListener {
	
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	Editor editor;
	SharedPreferences pref;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.values);
	        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

	        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    }

	    protected void onResume() {

	    	super.onResume();

	    	mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

	    	}

	    	protected void onPause() {

	    	super.onPause();

	    	mSensorManager.unregisterListener(this);

	    	}
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_demo_screens, menu);
	        return true;
	    }
	    
	    @Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			TextView tvX= (TextView)findViewById(R.id.tv1);

			TextView tvY= (TextView)findViewById(R.id.tv2);

			TextView tvZ= (TextView)findViewById(R.id.tv3);

			

			float x = Math.round(event.values[0]);

			float y = Math.round(event.values[1]);

			float z = Math.round(event.values[2]);

			if (!mInitialized) {

			mLastX = x;

			mLastY = y;

			mLastZ = z;

			tvX.setText("0.0");

			tvY.setText("0.0");

			tvZ.setText("0.0");

			mInitialized = true;

			} else {

			/*float deltaX = Math.abs(mLastX - x);

			float deltaY = Math.abs(mLastY - y);

			float deltaZ = Math.abs(mLastZ - z);

			if (deltaX < NOISE) deltaX = (float)0.0;

			if (deltaY < NOISE) deltaY = (float)0.0;

			if (deltaZ < NOISE) deltaZ = (float)0.0;

			mLastX = x;

			mLastY = y;

			mLastZ = z;*/
			Float X,Y,Z;
			if(Math.abs(mLastX-x)>=1)
				mLastX = x;
			if(Math.abs(mLastY-y)>=1)
				mLastY = y;
			if(Math.abs(mLastZ-z)>=1)
				mLastZ = z;
			
			if((Math.abs(mLastX-x)== 10)||(Math.abs(mLastY-y)== 10)||(Math.abs(mLastZ-z)== 10))
				notGood();
			X = mLastX;
			Y= mLastY;
			Z = mLastZ;
			

			/*tvX.setText("X: "+Float.toString(deltaX));

			tvY.setText("Y: "+Float.toString(deltaY));

			tvZ.setText("Z: "+Float.toString(deltaZ));*/
			tvX.setText("X: "+Float.toString(X));

			tvY.setText("Y: "+Float.toString(Y));

			tvZ.setText("Z: "+Float.toString(Z));

			
			}
		}
		
		public void notGood(){
			Intent go = new Intent("com.eas.demo.Home");
			startActivity(go);
		}
}
