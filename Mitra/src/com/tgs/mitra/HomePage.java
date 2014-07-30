package com.tgs.mitra;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.createTicket.CreateTicket;
import com.tgs.mitra.replayticket.ReplayTicket;



public class HomePage  extends Activity {

	
	AlertDialog alertDialog;
	Button mApply_leave, mSummary, mTeam_summary, mMy_leave, holydays,
			bdays_btn;
	String myId;
Spinner spinnerOsversions;
private String[] state = { "K071002", "K071003", "K071004", "K071005",
		   "K071006", "K071007", "K071008", "K071009", "K071010", "K071011",
		   "K071012", "K071013", "K071014", "K071015", "K071016", "K071017",
		   "K071018", "K071019" , "K071020"};
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 
Button logout_btn,create_btn,reply_btn;
TextView toptext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		
	spinnerOsversions=(Spinner)findViewById(R.id.spinnerstate);
		
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
			    R.layout.spintext, state);
			  adapter_state
			    .setDropDownViewResource(R.layout.spintext);
			  spinnerOsversions.setAdapter(adapter_state);
			  
		
		
		
		logout_btn=(Button)findViewById(R.id.logout);
		create_btn=(Button)findViewById(R.id.btn_create);
		reply_btn=(Button)findViewById(R.id.btn_reply);
		
		create_btn.setOnClickListener(listener);
		reply_btn.setOnClickListener(listener);

		
		//birthdayService();

		logout_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				Intent i = new Intent(getApplicationContext(),
						LoginActivity.class);
			
				startActivity(i);
			}
		});
		


	}


	private OnClickListener listener = new OnClickListener() {

	

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btn_create:
	
					Intent i = new Intent(getApplicationContext(),
							CreateTicket.class);
					startActivity(i);
			

				break;
			case R.id.btn_reply:
				
				Intent intent = new Intent(getApplicationContext(),
						ReplayTicket.class);
				startActivity(intent);
				break;
			
			}
			
			//ss
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
