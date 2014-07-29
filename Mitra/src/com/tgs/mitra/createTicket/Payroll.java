package com.tgs.mitra.createTicket;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.tgs.mitra.R;

public class Payroll extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	String id;
 public static String creat_message;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createticket);
		TextView heder= (TextView)findViewById(R.id.main_img);
		Button back= (Button)findViewById(R.id.back_btnn);
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent ir = new Intent(getApplicationContext(), CreateTicket.class);
				
			startActivity(ir);
			}
		});
		 
		Bundle b = getIntent().getExtras();
		if (b != null) {
			id = b.getString("msg");
			System.out.println("hello"+id);

		}
		heder.setText(id);
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);

		String[] planets = new String[] { "Paycheck missing", "Direct Depost missing", "Paid Incorrect Amount",
				"Request for time off", "Other" };
		ArrayList<String> planetList = new ArrayList<String>();
		planetList.addAll(Arrays.asList(planets));

		listAdapter = new ArrayAdapter<String>(this, R.layout.listtext,
				planetList);

		mainListView.setAdapter(listAdapter);
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name = parent.getItemAtPosition(position).toString();
				Intent intent=new Intent(Payroll.this,CreateDialogActivity.class);
				 intent.putExtra("msg", name);
						startActivity(intent);
				
			}
		});
	}
	public void showToast(String msg) {
		Toast.makeText(Payroll.this, msg, Toast.LENGTH_LONG).show();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent ir = new Intent(getApplicationContext(), CreateTicket.class);

		startActivity(ir);
		
	}
}