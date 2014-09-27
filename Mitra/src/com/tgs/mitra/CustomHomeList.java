package com.tgs.mitra;

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
import com.tgs.qsr.support.HomeScreenInfo;

public class CustomHomeList extends ArrayAdapter<HomeScreenInfo> {
	private final Activity context;
 
	ArrayList<HomeScreenInfo> list=null;

	DisplayImageOptions options=null;
	public CustomHomeList(Activity context, ArrayList<HomeScreenInfo> list) {
		super(context, R.layout.department,list);
		this.context = context;
		 this.list=list;
		 
		 
		 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
			
			
			
		 ImageLoader.getInstance().init(config);
		 
		   options = new DisplayImageOptions.Builder()
	        .showImageOnLoading(R.drawable.loading) // resource or drawable
	        .showImageForEmptyUri(R.drawable.hr) // resource or drawable
	        .showImageOnFail(R.drawable.hr) // resource or drawable
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
		View rowView = inflater.inflate(R.layout.homelist, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		
		TextView txt_count = (TextView) rowView.findViewById(R.id.txt_count);
		txt_count.setText(list.get(position).getCount());
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(list.get(position).getDescription());
		
		if(list.get(position).getDescription().contains("Assigned"))
		{
			rowView.setTag("Assigned Tickets");
		}
		else if(list.get(position).getDescription().contains("Open All"))
		{
			rowView.setTag("Open");
			
		}else if(list.get(position).getDescription().contains("My Tickets"))
		{
			rowView.setTag("My Tickets");
		}
		else if(list.get(position).getDescription().contains("Closed Tickets")){
			rowView.setTag("Close");
		}
		if(list.get(position).getImage()!=null )
		{
			if(!list.get(position).getImage().equals(""))
			{
				 
				
				ImageLoader.getInstance().displayImage(list.get(position).getImage(), imageView,options);
			}
			else{
				imageView.setImageResource(R.drawable.hr);
			}
		}
		else
		imageView.setImageResource(R.drawable.hr);
		
		return rowView;
	}
	
	 
}