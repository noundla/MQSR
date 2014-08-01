package com.tgs.mitra;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.mitra.util.HomeScreenInfo;

public class CustomHomeList extends ArrayAdapter<HomeScreenInfo> {
	private final Activity context;
 
	ArrayList<HomeScreenInfo> list=null;

	public CustomHomeList(Activity context, ArrayList<HomeScreenInfo> list) {
		super(context, R.layout.department,list);
		this.context = context;
		 this.list=list;
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
		if(list.get(position).getImage()!=null )
		{
			if(!list.get(position).getImage().equals(""))
			{
				/*String url_val = list.get(position).getImage();
				 URL url;
		          Bitmap  bmImg;
		        try {
		            url = new URL(url_val);
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setDoInput(true);
		            conn.connect();
		            InputStream is = conn.getInputStream();
		             bmImg = BitmapFactory.decodeStream(is);
		             imageView.setImageBitmap(bmImg); //Here u will set image in imageview
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }*/
				
				imageView.setImageResource(R.drawable.hr);
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