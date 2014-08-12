package com.tgs.mitra;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class TabsMainActivity extends  ActionBarActivity{
	private FragmentTabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_tabs);
		// mTabHost = new FragmentTabHost(this);
		// mTabHost.setup(this, getSupportFragmentManager(),
		// R.id.menu_settings);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		Bundle b = new Bundle();
		b.putString("key", "Home");
		mTabHost.addTab(mTabHost.newTabSpec("Home").setIndicator("Home"),
				Fragment1.class, b);
		//
		b = new Bundle();
		System.out.print("Tabs Main Activity");
		b.putString("key", "create");
		mTabHost.addTab(mTabHost.newTabSpec("Create")
				.setIndicator("Create"), Fragment2.class, b);
		b = new Bundle();
		b.putString("key", "Replay");
		mTabHost.addTab(mTabHost.newTabSpec("Replay").setIndicator("Replay"),
				Fragment3.class, b);
		// setContentView(mTabHost);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
