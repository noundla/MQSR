package com.tgs.mitra.createTicket;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tgs.mitra.R;
import com.tgs.mitra.bean.Department;

public class CustomList extends ArrayAdapter<Department> {
	private final Activity context;
	private ArrayList<Department> departlist=null;
	DisplayImageOptions options=null;

 
	Integer[] imageId = { R.drawable.payroll, R.drawable.hr, R.drawable.it,
			R.drawable.fin, R.drawable.hr,R.drawable.payroll, R.drawable.hr, R.drawable.it,
			R.drawable.fin, R.drawable.hr};
	 
	public CustomList(Activity context, ArrayList<Department> departlist) {
		super(context, R.layout.department, departlist);
		this.context = context;
		this.departlist = departlist;
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
		
		
		
		 ImageLoader.getInstance().init(config);
		 
		   options = new DisplayImageOptions.Builder()
	        .showImageOnLoading(R.drawable.loading) // resource or drawable
	        .showImageForEmptyUri(R.drawable.fin) // resource or drawable
	        .showImageOnFail(R.drawable.fin) // resource or drawable
	        .resetViewBeforeLoading(false)  // default
	        .delayBeforeLoading(1000)
	        .cacheInMemory(true) // default
	        .cacheOnDisk(true) // default
	         
	        
	        .bitmapConfig(Bitmap.Config.ARGB_8888) // default
	        
	         
	        
	        .build();
	 
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.department, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(departlist.get(position).getDepartment());
	
		 
			ImageLoader.getInstance().displayImage(departlist.get(position).getMobileImage(), imageView,options);
		//imageView.setImageResource(imageId[position]);
		 
		
		rowView.setTag(departlist.get(position));
		return rowView;
	}
}