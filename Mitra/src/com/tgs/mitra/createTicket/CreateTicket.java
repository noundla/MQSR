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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.HomePage;
import com.tgs.mitra.R;
import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.UtilMethod;

public class CreateTicket extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	Spinner spinnerOsversions;
	private String[] state = { "K071002", "K071003", "K071004", "K071005",
			"K071006", "K071007", "K071008", "K071009", "K071010", "K071011",
			"K071012", "K071013", "K071014", "K071015", "K071016", "K071017",
			"K071018", "K071019", "K071020" };
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
		
		
		DoBackground background=new DoBackground();
		background.execute();
		
		
		spinnerOsversions = (Spinner) findViewById(R.id.spinnerstate);

		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
				R.layout.spintext, state);
		adapter_state.setDropDownViewResource(R.layout.spintext);
		spinnerOsversions.setAdapter(adapter_state);
		TextView heder = (TextView) findViewById(R.id.main_img);

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
                   finish();
/*
				Intent ir = new Intent(getApplicationContext(), HomePage.class);

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
	}
	
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
			
			
			

		 
			for (int i = 0; i < dpartmentList.size(); i++) {
				
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
		Intent ir = new Intent(getApplicationContext(), HomePage.class);
		startActivity(ir);
	}
}
