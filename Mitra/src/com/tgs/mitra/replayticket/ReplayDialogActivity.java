package com.tgs.mitra.replayticket;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;

public class ReplayDialogActivity extends Activity{

	private Context _activity=null;
	private ConnectionDetector mConneDetect=null;
	private MQTickets replayTecket=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_dialog);
		_activity=this;
		mConneDetect =new ConnectionDetector(getApplicationContext());
		  replayTecket=(MQTickets) getIntent().getSerializableExtra("MQT_OBJ");
		
		TextView title=(TextView)findViewById(R.id.depart_title);
		
		TextView deptId=(TextView)findViewById(R.id.depart_id);
		
		TextView deptDes=(TextView)findViewById(R.id.depart_desc);
		TextView deptName=(TextView)findViewById(R.id.depart_name);
		
		title.setText(replayTecket.getDepartmentName());
		deptId.setText(""+replayTecket.getTicketId());
		deptDes.setText(replayTecket.getTicketDescription());
		deptName.setText(replayTecket.getTicketTitle());
		
		
		final EditText replyText=(EditText)findViewById(R.id.depart_desc_replay);
		
		ImageView replay=(ImageView)findViewById(R.id.reply_button);
		replay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
				if(replyText.getText().toString().trim().length()>1)
				{
				if(mConneDetect.isConnectingToInternet())
				{
					
					DoBackground mBackground=new DoBackground();
					mBackground.execute(replyText.getText().toString());
				}else
				{
					Toast.makeText(_activity, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
				}
				}
				else{
					Toast.makeText(_activity, "Invalid input.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
	}
	
	class DoBackground extends AsyncTask<String, Void, Void>
	{
		ProgressDialog dialog=null;
		private ArrayList<MQTickets> myTicketsList=null;
		


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Saving...");
			dialog.show();
		}



		@Override
		protected Void doInBackground(String... params) {
			
			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				//method.g
				//method.replayTicket(User.getInstance(), replayTecket);
			}
			return null;
		}


		 @Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
		}
	}
}
