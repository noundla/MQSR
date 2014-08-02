package com.tgs.mitra.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.tgs.mitra.R;

public class BaseActionBarActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		getSupportActionBar().setCustomView(R.layout.action_bar_bg_layout);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8A0808")));
		/*actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);*/
	}
}
