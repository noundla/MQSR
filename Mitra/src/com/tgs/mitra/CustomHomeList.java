package com.tgs.mitra;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.mitra.R;

public class CustomHomeList extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;

	public CustomHomeList(Activity context, String[] web, Integer[] imageId) {
		super(context, R.layout.department, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.homelist, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		
		TextView txt_count = (TextView) rowView.findViewById(R.id.txt_count);
		txt_count.setText("12000");
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web[position]);
		imageView.setImageResource(imageId[position]);
		return rowView;
	}
}