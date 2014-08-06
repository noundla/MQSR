package com.tgs.mitra.createTicket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.MQTicketing;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQDetQsn;
import com.tgs.mitra.util.UtilMethod;

public class CreateDialogActivity extends Activity {

	String id;

	Context _activity=null;

	private ConnectionDetector mConneDetect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_ticket_dialog);

		TextView titleText=(TextView)findViewById(R.id.main_img);
		
		_activity=this;
		mConneDetect =new ConnectionDetector(getApplicationContext());
		Button back = (Button) findViewById(R.id.back_btnn);

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
		TextView deptId = (TextView) findViewById(R.id.tic_name);
		
		titleText.setText(department.getDepartment());

		deptId.setText(detQsn.getQuestionTitle());

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
				mqTicketing.setDetails("");//replyText.getText().toString()
				mqTicketing.setDueDate(formatter.format(javaUtilDate)); 
				mqTicketing.setGuidfield(department.getGuidfield());
				
				 
              mqTicketing.setLastChange(formatter.format(javaUtilDate));
               mqTicketing.setLastChangeUser(User.getInstance().getUser());
               mqTicketing.setPriority("low"); //Need to add dynamically.
               mqTicketing.setReplyId("0");//NO need
               mqTicketing.setStoreId(User.getInstance().getStoreName());
               mqTicketing.setTicketId("0");
               mqTicketing.setTicketStatus("Open"); 
               mqTicketing.setTitle(replyText.getText().toString());
				
				
				
				DoBackground background=new DoBackground();
				background.execute(mqTicketing);
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


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}
		@Override
		protected Void doInBackground(MQTicketing... arg0) {

			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				status=method.replayTicket(User.getInstance(),arg0[0]);
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			if(status)
			{
			 Toast.makeText(_activity, "Ticket created successfully", Toast.LENGTH_LONG).show();
			 finish();
			}
			else{
				Toast.makeText(_activity, "Ticket created Fail!", Toast.LENGTH_LONG).show();
			}


			dialog.dismiss();
		}
	}
}
