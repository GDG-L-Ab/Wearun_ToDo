package com.giux.wearun;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


import android.preview.support.wearable.notifications.*;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

public class MainActivity extends Activity {

protected static ArrayAdapter<String> adapter = null;
	
	static Chronometer chrono;
	static Button startButt;
	static Button stopButt;
	static Button lapButt;
	static ListView liVi_lap;
	
	static ArrayList<String> lapTimes = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		chrono = (Chronometer) findViewById(R.id.chronometer1);
		startButt = (Button) findViewById(R.id.startButton);
		stopButt = (Button) findViewById(R.id.stopButton);
		lapButt = (Button) findViewById(R.id.lapButton);
		liVi_lap = (ListView) findViewById(R.id.liVi_LapTime);
		
		
		startButt.setOnClickListener(startChrono);
		stopButt.setOnClickListener(stopChrono);
		lapButt.setOnClickListener(lapTime);
		
		adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, lapTimes);
		liVi_lap.setAdapter(adapter);
		
		notif();
	
	}
	
	
	@Override
	protected void onNewIntent(Intent intent) {
		//ToDo
	}
	

	public void notif(){
		//Todo
	}
	

	
	
	
	OnClickListener startChrono = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			start();
			
		}
	};
	

	OnClickListener stopChrono = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stop();
		}
	};
		
	
	OnClickListener lapTime = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			lap();
		}
	};
	
	
	
	
	public static void start(){
		chrono.setBase(SystemClock.elapsedRealtime());
		lapTimes.clear();
		adapter.notifyDataSetChanged();
		chrono.start();
		
		startButt.setEnabled(false);
		lapButt.setEnabled(true);
		stopButt.setEnabled(true);
		
	}
	
	
	public static void lap(){
		lapTimes.add(saveLapTime());
		adapter.notifyDataSetChanged();
	}
	
	
	public static void stop(){
		chrono.stop();
		lapTimes.add(saveLapTime());
		adapter.notifyDataSetChanged();
		
		startButt.setEnabled(true);
		lapButt.setEnabled(false);
		stopButt.setEnabled(false);
		
	}
	
	
	
	private static String saveLapTime() {
	     long elapsedMillis = SystemClock.elapsedRealtime() - chrono.getBase();   
	     String time = getDate(elapsedMillis, "mm:ss.SSS");
		return time;
	 }
	
	public static String getDate(long milliSeconds, String dateFormat){
	   
		DateFormat formatter = new SimpleDateFormat(dateFormat);

		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(milliSeconds);
	    return formatter.format(calendar.getTime());
	}

}
