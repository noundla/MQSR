package com.tgs.mitra;

import com.tgs.mitra.createTicket.CreateTicket;
import com.tgs.mitra.replayticket.ReplayTicket;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainTabsActivity extends  TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTabs() ;
	}
	private void setTabs()
	{
		addTab("Replay", R.drawable.tab_home, ReplayTicket.class);
		addTab("Create", R.drawable.tab_search, CreateTicket.class);
		
		addTab("Replay", R.drawable.tab_home, ReplayTicket.class);
		addTab("Create", R.drawable.tab_search, CreateTicket.class);
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}
