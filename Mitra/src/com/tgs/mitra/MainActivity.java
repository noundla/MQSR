package com.tgs.mitra;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tgs.mitra.ui.BaseActionBarActivity;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.UtilMethod;

public class MainActivity extends BaseActionBarActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	ConnectionDetector mConneDetect=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));


		  mConneDetect =new ConnectionDetector(getApplicationContext());
		  
		 /* DoBackground doBackground=new DoBackground();
		  doBackground.execute();*/
		  
		  new Thread(){
			  public void run() {
				  UtilMethod method=new UtilMethod();
					
				 boolean state=method.getLoginState("Balaji", "9balaji@");
				System.out.println("TEST LOGIN State "+state);
			  }
		  }.start();

	}


	class DoBackground extends AsyncTask<Void, Void, Void>
	{
		boolean state=false;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			System.out.println("TEST LOGIN start..... ");
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Void... params) {
		 
			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				
				  state=method.getLoginState("Balaji", "9balaji@");
				System.out.println("TEST LOGIN State "+state);
			}
			else{
				System.out.println("TEST Connection Error ");
			}
			
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), "Login :"+state, Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public void onNavigationDrawerItemSelected(int position) {

		onAttachFragment(position);

	}


	private void onAttachFragment(int position) {


		switch (position) {
		case 0:

			// update the main content by replacing fragments
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					PlaceholderFragment.newInstance(position + 1)).commit();
			break;
		case 1:

			break;

		default:
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;

		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
