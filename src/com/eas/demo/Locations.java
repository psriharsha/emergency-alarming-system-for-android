package com.eas.demo;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Locations extends Activity{

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    protected LocationManager locationManager;
    protected Button retrieveLocationButton;
    protected TextView display;
	Editor editor;
	SharedPreferences pref;
	
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loc);
        retrieveLocationButton = (Button) findViewById(R.id.locdisplay);
        display = (TextView) findViewById(R.id.text);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER ,MINIMUM_TIME_BETWEEN_UPDATES,MINIMUM_DISTANCE_CHANGE_FOR_UPDATES
,new LocListener());
        retrieveLocationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showCurrentLocation();
			}
		});
	}
	
	public class LocListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			String message="null";
	        if (location != null) {
	        	message = String.format(
	        			"Current Location \n Longitude: %1$s \n Latitude: %2$s",
	        			location.getLongitude(), location.getLatitude()
	        	);
	        }
	        else
	        	message = "There's no data";
	        display.setText(message);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			String message = "Provider status changed";
			display.setText(message);
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			String message = "Provider disabled by the user. GPS turned off";
			display.setText(message);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			String message = "Provider enabled by the user. GPS turned on";
			display.setText(message);
		}
		
		protected void showCurrentLocation() {
	        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	        String message="null";
	        if (location != null) {
	        	message = String.format(
	        			"Current Location \n Longitude: %1$s \n Latitude: %2$s",
	        			location.getLongitude(), location.getLatitude()
	        	);
	        }
	        else
	        	message = "There's no data";
	        display.setText(message);

	    }
		
	}
	
    protected void showCurrentLocation() {
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        String message="null";
        if (location != null) {
        	message = String.format(
        			"Current Location \n Longitude: %1$s \n Latitude: %2$s",
        			location.getLongitude(), location.getLatitude()
        	);
        }
        else
        	message = "There's no data";
        display.setText(message);

    }

}
