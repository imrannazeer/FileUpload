package com.example.killer;

import android.graphics.Bitmap;

public class ApplicationsController {
	public String packageNames;
	public String appName;
	public Bitmap appIcon;
	
	public ApplicationsController(String packageNames, String appName){
		this.packageNames = packageNames;
		this.appName = appName;
	}
	public Bitmap getAppIcon() {
		return this.appIcon;
	}
	public void setAppIcon(Bitmap appIcon) {
		this.appIcon = appIcon;
	}
	public String getPackageNames() {
		return this.packageNames;
	}
	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}
	public String getAppName() {
		return this.appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
