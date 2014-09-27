package com.tgs.mitra.createTicket;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.HomePage;
import com.tgs.mitra.R;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.qsr.support.Department;
import com.tgs.qsr.support.MQDetQsn;
import com.tgs.qsr.support.MQTicketing;
import com.tgs.qsr.support.User;
import com.tgs.qsr.support.UtilMethod;

public class CreateDialogActivity extends Activity {

	String id;

	Context _activity=null;

	private ConnectionDetector mConneDetect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.create_ticket_dialog);

		final TextView titleText=(TextView)findViewById(R.id.main_img);
		
		_activity=this;
		mConneDetect =new ConnectionDetector(getApplicationContext());
		Button back = (Button) findViewById(R.id.back_btnn);
		
		((TextView)findViewById(R.id.store_name)).setText("Store         : "+User.getInstance().getStoreName());

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				 
			}

		});

		final EditText replyText=(EditText)findViewById(R.id.depart_desc_replay);
		Button cancel = (Button) findViewById(R.id.cancel_button);

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}

		});

		final MQDetQsn detQsn=(MQDetQsn)getIntent().getSerializableExtra("OBJ_QT");
		final Department department=(Department)getIntent().getSerializableExtra("OBJ_DPT");
		
		if(detQsn==null)
		{
			Toast.makeText(getApplicationContext(), "Faild to load!",Toast.LENGTH_LONG).show();
			finish();
		}
		final TextView deptId = (TextView) findViewById(R.id.tic_name);
		
		titleText.setText(department.getDepartment());

		deptId.setText("Catagery  : "+detQsn.getQuestionTitle());

		Button replay = (Button) findViewById(R.id.reply_button);
		replay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if(replyText.getText().toString().trim().length()>1)
				{
				Date javaUtilDate= new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				
				MQTicketing mqTicketing=new MQTicketing();
				
				mqTicketing.setAssignedOwner(""); 
				mqTicketing.setCopyToEmail("");
				mqTicketing.setCreatedDate(formatter.format(javaUtilDate)); 
				mqTicketing.setCreatedUser(User.getInstance().getUser());
				mqTicketing.setDepartment(department.getDepartment());
				mqTicketing.setDetails(replyText.getText().toString());//replyText.getText().toString()
				mqTicketing.setDueDate(formatter.format(javaUtilDate)); 
				mqTicketing.setGuidfield(department.getGuidfield());
				 
              mqTicketing.setLastChange(formatter.format(javaUtilDate));
               mqTicketing.setLastChangeUser(User.getInstance().getUser());
               mqTicketing.setPriority("low"); //Need to add dynamically.
               mqTicketing.setReplyId("0");//NO need
               mqTicketing.setStoreId(User.getInstance().getStoreName());
               mqTicketing.setTicketId("0");
               mqTicketing.setTicketStatus("Open"); 
               
               mqTicketing.setTitle(deptId.getText().toString());
				
				
               
				if(mConneDetect.isConnectingToInternet())
				{
				DoBackground background=new DoBackground();
				background.execute(mqTicketing);
				}
				else{
					  Toast.makeText(_activity, R.string.connection_error, Toast.LENGTH_LONG).show();
					  finish();
				}
				//finish();
			}
				else{
					Toast.makeText(_activity, "Invalid input!", Toast.LENGTH_LONG).show();
				}
			}
		});

	}
	
	class DoBackground extends AsyncTask<MQTicketing, Void, Void>
	{
		ProgressDialog dialog=null;
		private boolean status=false;
		int ticketId=0;


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
		protected Void doInBackground(MQTicketing... arg0) {

			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod(_activity);
				status=method.createTicket(User.getInstance(),arg0[0]);//method.replayTicket(User.getInstance(),arg0[0]);
				ticketId=method.createdTicketId;
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			if(status)
			{
				
				final Dialog dialog = new Dialog(CreateDialogActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
				dialog.setContentView(R.layout.custom_dialog);
				dialog.setCancelable(false);
			//	dialog.setTitle("Title...");
	 
				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.text);
				text.setText("Created new ticket:"+ticketId+" successfully");
				ImageView image = (ImageView) dialog.findViewById(R.id.image);
				image.setImageResource(R.drawable.logo);
	 
				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						 finish();
						 Intent i = new Intent(_activity,
									HomePage.class);
							i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(i);
						dialog.dismiss();
					}
				});
	 
				dialog.show();
			  }
				
	/*		 Toast.makeText(_activity, "Ticket created successfully", Toast.LENGTH_LONG).show();
			 AlertDialog alertDialog = new AlertDialog.Builder(
					 CreateDialogActivity.this).create();
					 alertDialog.setTitle(getString(R.string.app_name));
					 alertDialog.setMessage("Created new ticket:"+ticketId+" successfully");
					 alertDialog.setIcon(R.drawable.logo);
					 
					  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					 public void onClick(DialogInterface dialog, int which) {
						 finish();
						 Intent i = new Intent(_activity,
									HomePage.class);
							i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(i);
					 }
					 }
					  );
					  alertDialog.show();
			*/
			
			else{
				Toast.makeText(_activity, "Ticket created Fail!", Toast.LENGTH_LONG).show();
			}


			dialog.dismiss();
		}
	}
}
