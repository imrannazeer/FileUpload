package com.example.killer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter<ApplicationsController>{

	Context context;
	ArrayList<ApplicationsController> appCtlList;
	int resId;

	public CustomListAdapter(Context context, int resId,ArrayList<ApplicationsController> list) {
		super(context, resId, list);
		this.resId = resId;
		this.context = context;
		appCtlList = list;
	}

	@Override
	public int getCount() {

		return appCtlList.size();
	}


	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageHolder holder=null;
		ImageView thumb_image ;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.single_list_view, null);
			holder=new ImageHolder();
			holder.txtName = (TextView)convertView.findViewById(R.id.tvAppName);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ImageHolder)convertView.getTag();
		}

		ApplicationsController picture = appCtlList.get(position);
		holder.txtName.setText(picture.appName);
		final PackageManager pm = context.getPackageManager();
		Drawable icon = null ;
		try {
			icon = pm.getApplicationIcon(picture.packageNames);
			thumb_image=(ImageView)convertView.findViewById(R.id.icon_app); // thumb image
			thumb_image.setImageDrawable(icon);
		} catch (NameNotFoundException e) {
			thumb_image=(ImageView)convertView.findViewById(R.id.icon_app); // thumb image
			Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
			thumb_image.setImageBitmap(largeIcon);
			e.printStackTrace();
		}
		return convertView;
	}
	static class ImageHolder
	{
		ImageView imgIcon;
		TextView txtName,txtCost;
	}
}