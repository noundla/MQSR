package com.tgs.mitra;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tgs.mitra.bean.User;
import com.tgs.mitra.createTicket.CreateTicket;
import com.tgs.mitra.homeinfo.HomeTicketInfo;
import com.tgs.mitra.replayticket.ReplayTicket;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.HomeScreenInfo;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;



public class HomePage  extends Activity {


	Button create_btn,reply_btn;
	private Button logout_btn;
	private Context _activity=null;
	private ListView homeListView;
	ProgressBar homeProgressBar=null;
	//private Spinner mSpinner=null;
	private ConnectionDetector mConneDetect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);

		mConneDetect = new ConnectionDetector(getApplicationContext());
		AppContext globalVariable = (AppContext) getApplicationContext();

		if(!globalVariable.isValid())
		{
			Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();

			finish();

		}

		homeProgressBar=(ProgressBar)findViewById(R.id.progressBar1);

		homeProgressBar.setVisibility(View.VISIBLE);

		_activity=this;

		homeListView = (ListView) findViewById(R.id.homelist);

		//mSpinner = (Spinner) findViewById(R.id.store_spinner);

		logout_btn=(Button)findViewById(R.id.logout);
		logout_btn.bringToFront();
		create_btn=(Button)findViewById(R.id.btn_create);
		reply_btn=(Button)findViewById(R.id.btn_reply);

		create_btn.setOnClickListener(listener);
		reply_btn.setOnClickListener(listener);


		logout_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				 
				finish();

				Intent i = new Intent(getApplicationContext(),
						LoginActivity.class);

				startActivity(i);
			}
		});

		 

		if(mConneDetect.isConnectingToInternet())
		{
			
			
			  if(User.getInstance().getStoreList()==null)
			   {
			StoreListTaks storeListTaks=new StoreListTaks();
			storeListTaks.execute();
			   }
			  else{
				  /*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(HomePage.this,
							R.layout.listtext, User.getInstance().getStoreList());
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					mSpinner.setAdapter(dataAdapter);*/
			  }
			HomeInfoTaks homeInfoTaks=new HomeInfoTaks();
			homeInfoTaks.execute();
		}
		else{
			Toast.makeText(_activity, R.string.connection_error, Toast.LENGTH_LONG).show();
		}


		/*//Test code
		 new Thread(){
			  public void run() {
				  UtilMethod method=new UtilMethod();
				  User user=User.getInstance();
				  

				    ArrayList<MQTickets> tickets = method.getSearchTicketsList(user, "MyTickets", "a");
				System.out.println("TEST LOGIN State open "+tickets.size());
			  }
		  }.start();*/
		
		homeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(mConneDetect.isConnectingToInternet())
				{
				//	Toast.makeText(_activity, "this"+arg1.getTag().toString(), Toast.LENGTH_LONG).show();
					/*Intent intent=new Intent(_activity,HomeTicketInfo.class);
					intent.putExtra("TicketType", arg1.getTag().toString());
					startActivity(intent);*/
					
					Intent intent=new Intent(_activity,ReplayTicket.class);
					intent.putExtra("TicketType", arg1.getTag().toString());
					intent.putExtra("isReplayScreen", false);
					startActivity(intent);
				}
				
			}
		});

	}


	class StoreListTaks extends AsyncTask<Void, Void, Void>
	{
		
		private ProgressDialog dialog=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			  ArrayList<String> storeList=null;
			UtilMethod method=new UtilMethod();
			storeList= method.getUserallowedstoresList(User.getInstance());
			User.getInstance().setStoreList(storeList);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);


			/*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(HomePage.this,
					R.layout.listtext, User.getInstance().getStoreList());
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpinner.setAdapter(dataAdapter);*/

			dialog.dismiss();
		}
	}


	class HomeInfoTaks extends AsyncTask<Void, Void, Void>
	{
		private ArrayList<HomeScreenInfo> homeInfoList=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			UtilMethod method=new UtilMethod();
			homeInfoList= method.getHomeScreenInfoList(User.getInstance());


			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			homeProgressBar.setVisibility(View.GONE);


			CustomHomeList homelistAdapter = new CustomHomeList(HomePage.this,homeInfoList);
			homeListView.setAdapter(homelistAdapter);
			homeListView.invalidateViews();

		}
	}


	private OnClickListener listener = new OnClickListener() {



		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_create:

				Intent i = new Intent(getApplicationContext(),
						CreateTicket.class);
				//	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);


				break;
			case R.id.btn_reply:

				Intent intent = new Intent(getApplicationContext(),
						ReplayTicket.class);
				//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				break;

			}

		}
	};


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);


		startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


		startActivity(startMain);

	}

	public void showToast(String msg) {
		Toast.makeText(HomePage.this, msg, Toast.LENGTH_LONG).show();
	}

}
