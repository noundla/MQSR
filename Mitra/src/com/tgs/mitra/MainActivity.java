package com.tgs.mitra;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tgs.mitra.ui.BaseActionBarActivity;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.qsr.support.MQDetQsn;
import com.tgs.qsr.support.User;
import com.tgs.qsr.support.UtilMethod;

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

	private Activity _activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_activity=this;

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));


		  mConneDetect =new ConnectionDetector(getApplicationContext());
		  
		   
		  
		  
		  
		  DoBackground doBackground=new DoBackground();
		  doBackground.execute();
		  
		  new Thread(){
			  public void run() {
				  UtilMethod method=new UtilMethod(_activity);
				  User user=User.getInstance();
				 /* user.setUser("Balaji");
				  user.setPassword("9balaji@");
				*/	
				 method.getHomeTicketsInfo(user, "Open");
				System.out.println("TEST LOGIN State open ");
			  }
		  }.start();

	}


	class DoBackground extends AsyncTask<Void, Void, Void>
	{
		boolean state=false;
		ProgressDialog dialog=null;
		
		 ArrayList<MQDetQsn> mqlist=null;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			System.out.println("TEST LOGIN start..... ");
			
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Void... params) {
		 
			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod(_activity);
				/*MQTicketing replayTicket=new MQTicketing();
				replayTicket.setAssignedOwner("Appaji");
				replayTicket.setCopyToEmail("harini@pravastech.com");
				replayTicket.setCreatedDate("2014-07-23T18:43:00");
				replayTicket.setCreatedUser("Harini");
				replayTicket.setDepartment("IT");
				replayTicket.setDetails("Test");
				replayTicket.setDueDate("2014-07-23T18:43:00");
				replayTicket.setGuidfield("08dd2c5c-61ff-4291-834b-614d45bb84a3");
				replayTicket.setLastChange("2014-07-23T18:43:00");
				replayTicket.setLastChangeUser("Harini");
				replayTicket.setPriority("High");
				replayTicket.setReplyId("0");
				replayTicket.setStoreId("K007102");
				replayTicket.setTicketId("0");
				replayTicket.setTicketStatus("open");
				replayTicket.setTitle("test");*/
				
				 //state=method.replayTicket(User.getInstance(), replayTicket);
				//state=method.createTicket(User.getInstance(), replayTicket);
				//tickeinglist=	method.getTicketsList(User.getInstance(),"ticketStatus","lastchange","ticketStatus");
				
				//method.getDeptQuestions(User.getInstance(),"dfefd2b5-7863-47fe-910f-63cd92f407ca","");
				//method.getDepartmentList(user)
				method.getLoginState(User.getInstance());
				  mqlist=	method.getDeptQuestions(User.getInstance(),"dfefd2b5-7863-47fe-910f-63cd92f407ca","");
				
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
			dialog.dismiss();
//			if(tickeinglist!=null)
//			Toast.makeText(getApplicationContext(), "Total size :"+tickeinglist.size(), Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), "Result "+state, Toast.LENGTH_LONG).show();
			for (int i = 0; i < mqlist.size(); i++) {
				System.out.println("TEST :"+mqlist.get(i).getQuestionTitle());
			}
		}
	}
	@Override
	public void onNavigationDrawerItemSelected(int position) {

		onAttachFragment(position);

	}


	private void onAttachFragment(int position) {


		switch (position) {
		case 0:

		/*	// update the main content by replacing fragments
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					CreateTicket.newInstance(position + 1)).commit();*/
			break;
		case 1:
			/*FragmentManager fragmentManager1 = getSupportFragmentManager();
			fragmentManager1
			.beginTransaction()
			.replace(R.id.container,
					ReplayTicket.newInstance(position + 1)).commit();*/
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
