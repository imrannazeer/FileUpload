package com.example.killer;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	ListView theAppsList;
	ArrayList<ApplicationsController> list ;
	Button killButton ;
    ActivityManager activityManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		killButton = (Button)findViewById(R.id.btnKillAll);
		killButton.setOnClickListener(this);
		list= new ArrayList<ApplicationsController>();
		theAppsList =(ListView)findViewById(R.id.appsList);
		 activityManager = (ActivityManager) getSystemService(getApplicationContext().ACTIVITY_SERVICE);
		final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
		for (RunningAppProcessInfo pid : activityManager.getRunningAppProcesses()) {
			activityManager.killBackgroundProcesses(pid.processName);
		}
		for (int i = 0; i < recentTasks.size(); i++) 
		{
			Log.d("Executed app", "Application executed : " +recentTasks.get(i).baseActivity.toShortString()+ "\t\t ID: "+recentTasks.get(i).id+"");  
			String thePackageName=   recentTasks.get(i).baseActivity.getPackageName();
		
			final PackageManager pm = getApplicationContext().getPackageManager();
			ApplicationInfo ai = null;
			try {
				ai = pm.getApplicationInfo( thePackageName, 0);
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String applicationName = null;
			if( pm.getLaunchIntentForPackage(ai.packageName) != null ){
			
				applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : thePackageName);
			}
			list.add(new ApplicationsController(thePackageName,applicationName));
		}
		showListOfRunningApps();
	}

	private void showListOfRunningApps() {
		 CustomListAdapter contactListAdapter = new CustomListAdapter(getApplicationContext(), R.layout.single_list_view,list);
		 theAppsList.setAdapter(contactListAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnKillAll:
			for (RunningTaskInfo pid : activityManager.getRunningTasks(Integer.MAX_VALUE)) {
				System.out.println(pid.baseActivity.toShortString());
				android.os.Process.killProcess(pid.id);
			}
			showListOfRunningApps();
			
			break;

		default:
			break;
		}
		
	}
	
}
