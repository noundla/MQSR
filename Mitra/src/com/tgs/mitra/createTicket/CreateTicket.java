package com.tgs.mitra.createTicket;

import java.io.Serializable;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.replayticket.ReplayTicket;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.qsr.support.Department;
import com.tgs.qsr.support.User;
import com.tgs.qsr.support.UtilMethod;

public class CreateTicket extends Activity {

	private ListView mainListView;
	private Spinner depatment_spinner;
	private TextView store_name;
	private Context _activity=null;
	ConnectionDetector mConneDetect=null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createticket);
		
		_activity=this;
		  mConneDetect =new ConnectionDetector(getApplicationContext());
		
		
		  depatment_spinner = (Spinner) findViewById(R.id.department_spinner);
			
		   depatment_spinner.setVisibility(View.VISIBLE);
		   if(User.getInstance().getStoreList()==null)
		   {
			StoreListTaks storeListTaks=new StoreListTaks();
			storeListTaks.execute();
		   }
		   else{
				  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CreateTicket.this,
							R.layout.spintext, User.getInstance().getStoreList());
						dataAdapter.setDropDownViewResource(R.layout.spintext);
						depatment_spinner.setAdapter(dataAdapter);
						
						depatment_spinner.setSelection(dataAdapter.getPosition(User.getInstance().getStoreName()));
			  }
		  
		   if(mConneDetect.isConnectingToInternet())
			{
		DoBackground background=new DoBackground();
		background.execute();
			}
		   else{
			   Toast.makeText(_activity, R.string.connection_error, Toast.LENGTH_LONG).show();
			   finish();
		   }
		   
		   
		   
		
		   
		   depatment_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				User.getInstance().setStoreName(((TextView)arg1).getText().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		   
		 
			 store_name = (TextView) findViewById(R.id.store_name);
		   
			 store_name.setVisibility(View.GONE);
/*		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
				R.layout.spintext, state);
		adapter_state.setDropDownViewResource(R.layout.spintext);
		depatment_spinner.setAdapter(adapter_state);*/
		TextView heder = (TextView) findViewById(R.id.main_img);

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
                 finish();

				/*Intent ir = new Intent(getApplicationContext(), HomePage.class);

				startActivity(ir);*/
			}
		});
		heder.setText("Department");

		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);
		mainListView.setScrollingCacheEnabled(false);

		
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name = parent.getItemAtPosition(position).toString();
 
					/*Intent ir = new Intent(getApplicationContext(),
							Payroll.class);
					ir.putExtra("msg", name);
					startActivity(ir);*/
				 
			}
		});
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 
				System.out.println("TEST ITEM name "+((Department)arg1.getTag()).getDepartment());
				//Toast.makeText(_activity, "Depart: "+((Department)arg1.getTag()).getDepartment(), Toast.LENGTH_LONG).show();
				Intent ir = new Intent(getApplicationContext(),
						Payroll.class);
				ir.putExtra("DEPRT_OBJ", (Serializable)((Department)arg1.getTag()));
				startActivity(ir);
				
			}
		});
		
		 Button create_btn=(Button)findViewById(R.id.btn_create);
			Button reply_btn=(Button)findViewById(R.id.btn_reply);

			create_btn.setOnClickListener(listener);
			reply_btn.setOnClickListener(listener);
		
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
			
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CreateTicket.this,
					R.layout.spintext, User.getInstance().getStoreList());
				dataAdapter.setDropDownViewResource(R.layout.spintext);
				depatment_spinner.setAdapter(dataAdapter);
			
			dialog.dismiss();
		}
	}
	
	private OnClickListener listener = new OnClickListener() {



		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_create:

		/*		Intent i = new Intent(getApplicationContext(),
						CreateTicket.class);
				startActivity(i);*/


				break;
			case R.id.btn_reply:

				Intent intent = new Intent(getApplicationContext(),
						ReplayTicket.class);
				startActivity(intent);
				break;

			}

		}
	};
	class DoBackground extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog dialog=null;
		private ArrayList<Department> dpartmentList=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading Departments...");
			dialog.setCancelable(false);
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			 
			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				dpartmentList=method.getDepartmentList(User.getInstance());
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			
			if(dpartmentList==null)
			{
				Toast.makeText(_activity, "No Results Found!", Toast.LENGTH_LONG).show();
				finish();
			}
			
			
			if(dpartmentList.size()==0)
			{
				Toast.makeText(_activity, "No Results Found!", Toast.LENGTH_LONG).show();
				finish();
			}

		  
			
			CustomList listAdapter = new CustomList(CreateTicket.this,dpartmentList);

			 

			mainListView.setAdapter(listAdapter);
			
			
			dialog.dismiss();
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		/*Intent ir = new Intent(getApplicationContext(), HomePage.class);
		startActivity(ir);*/
	}
}
