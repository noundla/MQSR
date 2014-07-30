package com.tgs.mitra.createTicket;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.Department;

public class CustomList extends ArrayAdapter<Department> {
	private final Activity context;
	private ArrayList<Department> departlist=null;
 

 
	Integer[] imageId = { R.drawable.payroll, R.drawable.hr, R.drawable.it,
			R.drawable.fin, R.drawable.hr,R.drawable.payroll, R.drawable.hr, R.drawable.it,
			R.drawable.fin, R.drawable.hr};
	public CustomList(Activity context, ArrayList<Department> departlist) {
		super(context, R.layout.department, departlist);
		this.context = context;
		this.departlist = departlist;
	 
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.department, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(departlist.get(position).getDepartment());
	
		if(imageId.length>position)
		{
		imageView.setImageResource(imageId[position]);
		}
		else
			imageView.setImageResource(imageId[0]);
		
		rowView.setTag(departlist.get(position));
		return rowView;
	}
}