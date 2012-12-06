package com.eas.demo;

import android.app.Application;

public class Singleton extends Application {
Singleton instance;
	static float x,y,z,lat,lon;
	static int times;
	static String ip = "192.168.0.5";

	public Singleton()
	{
		instance = new Singleton();
	}
	public float getX() {
		return x;
	}
	
	public Singleton getInstance()
	{
		return instance;
	}
	
	
}
