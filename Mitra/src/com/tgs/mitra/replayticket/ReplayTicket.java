package com.tgs.mitra.replayticket;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tgs.mitra.HomePage;
import com.tgs.mitra.R;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;

public class ReplayTicket extends Activity implements OnClickListener{
	//ArrayList<ContentObject> arrayList=new ArrayList<ContentObject>();
	private Context _activity=null;
	private ConnectionDetector mConneDetect=null;
	private LinearLayout contentLayout=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_xml);
		_activity=this;
		mConneDetect =new ConnectionDetector(getApplicationContext());

		DoBackground background=new DoBackground();
		background.execute();

		Button back= (Button)findViewById(R.id.back_btnn);



		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
				Intent ir = new Intent(getApplicationContext(), HomePage.class);

				startActivity(ir); 
			}
		});
		  contentLayout=(LinearLayout)findViewById(R.id.content_layout);

		

	}

	class DoBackground extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog dialog=null;
		private ArrayList<MQTickets> myTicketsList=null;


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {

			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				myTicketsList=	method.getMyTeckets(User.getInstance(), "Open");
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			if(myTicketsList!=null)
			{
			try{
				
				/*//ContentObject contentObject =null;
				for (int i = 0; i < myTicketsList.size(); i++) {

					contentObject=new ContentObject();
					 
					 
						contentObject.department=myTicketsList.get(i).getDepartmentName();

					contentObject.description=myTicketsList.get(i).getTicketDescription();
					contentObject.name=myTicketsList.get(i).getDepartmentName();
					contentObject.id=myTicketsList.get(i).getTicketId();
					arrayList.add(contentObject);
				}*/

				for (int i = 0; i < myTicketsList.size(); i++) {

					View view=View.inflate(getApplicationContext(), R.layout.content_layout, null);

					TextView title=(TextView)view.findViewById(R.id.depart_title);

					TextView deptId=(TextView)view.findViewById(R.id.depart_id);

					TextView deptDes=(TextView)view.findViewById(R.id.depart_desc);
					TextView deptName=(TextView)view.findViewById(R.id.depart_name);

					title.setText(myTicketsList.get(i).getDepartmentName());
					deptId.setText(""+myTicketsList.get(i).getTicketId());
					deptDes.setText(myTicketsList.get(i).getTicketDescription());
					//	deptName.setText(arrayList.get(i).department+"dsfds");
					deptName.setText(myTicketsList.get(i).getLastModified());
					ImageView button=(ImageView)view.findViewById(R.id.reply_button);

					button.setTag(myTicketsList.get(i));
					button.setOnClickListener(ReplayTicket.this);

					contentLayout.addView(view);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}

			dialog.dismiss();
		}
	}
	@Override
	public void onClick(View arg0) {

		// Toast.makeText(getApplicationContext(), "Selected" +((ContentObject)arg0.getTag()).id, Toast.LENGTH_LONG).show();
		Intent intent=new Intent(ReplayTicket.this,ReplayDialogActivity.class);
		intent.putExtra("MQT_OBJ",(Serializable)(MQTickets) arg0.getTag());
		startActivity(intent);
	}


}
