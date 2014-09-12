package com.tgs.mitra.homeinfo;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.createTicket.CreateTicket;
import com.tgs.mitra.replayticket.ReplayTicket;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;

public class HomeTicketInfo extends Activity   {
	public static final ListAdapter ReplyListviewAdapter = null;
	// ArrayList<ContentObject> arrayList=new ArrayList<ContentObject>();
	private Context _activity = null;
	private ConnectionDetector mConneDetect = null;
	private LinearLayout contentLayout = null;
	ListView reply_list_view;
Spinner depatment_spinner;
String ticketType="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_xml);
		_activity = this;
		
		ticketType=getIntent().getStringExtra("TicketType");
		
		((TextView)findViewById(R.id.main_img)).setText(ticketType);
		
		mConneDetect = new ConnectionDetector(getApplicationContext());

		if(mConneDetect.isConnectingToInternet())
		{
		DoBackground background = new DoBackground();
		background.execute();


		StoreListTaks storeListTaks=new StoreListTaks();
		storeListTaks.execute();
		
		}
		else{
			  Toast.makeText(_activity, R.string.connection_error, Toast.LENGTH_LONG).show();
			  finish();
		}
		
		   depatment_spinner = (Spinner) findViewById(R.id.department_spinner);
		
		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
				 
			}
		});
		// contentLayout=(LinearLayout)findViewById(R.id.content_layout);
		reply_list_view = (ListView) findViewById(R.id.reply_ListView);

		 
		reply_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				
				//Toast.makeText(_activity, "Not implemented!", Toast.LENGTH_LONG).show();
				 

				Intent intent = new Intent(HomeTicketInfo.this,
						HomeTicketInfo.class);
				ViewHolder holder=(ViewHolder) view.getTag();
				intent.putExtra("MQT_OBJ", (Serializable) (MQTickets) holder.getMqTickets());
				startActivity(intent);
				
			}
		});
		 
		Button create_btn = (Button) findViewById(R.id.btn_create);
		Button reply_btn = (Button) findViewById(R.id.btn_reply);

		create_btn.setOnClickListener(listener);
		reply_btn.setOnClickListener(listener);
	}

	
	class StoreListTaks extends AsyncTask<Void, Void, Void>
	{
		private ArrayList<String> storeList=null;
		private ProgressDialog dialog=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			UtilMethod method=new UtilMethod();
			storeList= method.getUserallowedstoresList(User.getInstance());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(HomeTicketInfo.this,
					R.layout.spintext, storeList);
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

		}
	};
	private ArrayList<MQTickets> myTicketsList = null;

	class DoBackground extends AsyncTask<Void, Void, Void> {
		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			if (mConneDetect.isConnectingToInternet()) {
				UtilMethod method = new UtilMethod();
				myTicketsList = method.getHomeTicketsInfo(User.getInstance(), ticketType);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(myTicketsList==null)
			{
			Toast.makeText(_activity, "No Results Found!", Toast.LENGTH_LONG).show();
			finish();
			}
			
			if(myTicketsList.size()==0)
			{
				Toast.makeText(_activity, "No Results Found!", Toast.LENGTH_LONG).show();
				finish();
			}

			reply_list_view.setAdapter(new ReplyListviewAdapter(
					getApplicationContext()));

			dialog.dismiss();
		}
	}

	public class ReplyListviewAdapter extends BaseAdapter {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		LayoutInflater inflater;
		Context context;

		public ReplyListviewAdapter(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myTicketsList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub

			ViewHolder holder;

			if (view == null) {
				holder = new ViewHolder();

				view = inflater.inflate(R.layout.replyticket_list_item, null);
				holder.dep_name = (TextView) view
						.findViewById(R.id.department_name);
				holder.dep_title = (TextView) view
						.findViewById(R.id.depart_title);
				holder.desc = (TextView) view.findViewById(R.id.depart_desc);
				// holder.month = (TextView) view.findViewById(R.id.emp_bmonth);
				holder.createdby = (TextView) view
						.findViewById(R.id.created_user);
				holder.date = (TextView) view.findViewById(R.id.created_date);
				holder.count = (TextView) view.findViewById(R.id.count);
				holder.ticketID=(TextView) view.findViewById(R.id.ticket_id);
				
				holder.arrow = (ImageView) view
						.findViewById(R.id.arrow);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			try {
				//if (myTicketsList != null)

				//for (int i = 0; i < myTicketsList.size(); i++) {
				holder.dep_title.setText("-"+myTicketsList.get(position)
						.getTicketTitle());
				holder.dep_name.setText(myTicketsList.get(position)
						.getDepartmentName());

				holder.desc.setText(myTicketsList.get(position)
						.getTicketDescription());
				/*	holder.createdby.setText("Created by : "+ myTicketsList.get(position)
								.getLastModifiedBy()+" On "+ myTicketsList.get(position)
								.getLastModified());*/


				//2014-09-01T09:17:18.05

				String dt = myTicketsList.get(position)
						.getLastModified();
				String splitParts[] = dt.split("T");


				String dates  = splitParts[0];
				String times  = splitParts[1];

				String onlytime[]=times.split(":");
				String hour=onlytime[0];
				String minute=onlytime[1];
				String second=onlytime[2];


				String dateParts[] = dates.split("-");
				String year  = dateParts[0];
				String month  = dateParts[1];
				String day = dateParts[2];

				
				
			
				
				String current_date=dateFormat.format(cal.getTime()).trim();
				current_date=current_date.replace("/", "-");
				 
			
				if (current_date.toString().equalsIgnoreCase(dates.trim())) {
					System.out.println("hell.."+Integer.valueOf(hour)%12 + ":" + minute + " " + ((Integer.valueOf(hour)>=12) ? "PM" : "AM"));
					
					holder.count.setText(Integer.valueOf(hour)%12 + ":" + minute + " " + ((Integer.valueOf(hour)>=12) ? "PM" : "AM"));
					holder.count.setVisibility(View.VISIBLE);
	
					
				}else{
					holder.count.setText(formatMonth(month).substring(0, 3)+" "+day/*+","+year */);
					holder.count.setVisibility(View.VISIBLE);
					System.out.println("hell.1."+formatMonth(month).substring(0, 3)+" "+day/*+","+year*/);
				}
				
				
				//formatMonth("2");
			//	System.out.println("monyth is   "+formatMonth(month));
			/*	holder.count.setText(Integer.valueOf(hour)%12 + ":" + minute + " " + ((Integer.valueOf(hour)>=12) ? "PM" : "AM"));
				holder.count.setVisibility(View.VISIBLE);*/
				
				if(myTicketsList.get(position).getReplayCount()!=0)
				{
				//holder.date.setText("Replays("+myTicketsList.get(position).getReplayCount()+")");
				holder.dep_title.setText("-"+myTicketsList.get(position)
						.getTicketTitle()+" ");
				holder.date.setText("   "+myTicketsList.get(position).getReplayCount());
				holder.date.setVisibility(View.VISIBLE);
				holder.arrow .setVisibility(View.VISIBLE);
				view.setBackgroundColor(_activity.getResources().getColor(R.color.reply_color));
				holder.createdby.setBackgroundColor(_activity.getResources().getColor(R.color.reply_color));
				
				}else{
					holder.date.setVisibility(View.INVISIBLE);
					holder.arrow .setVisibility(View.GONE);
					view.setBackgroundColor(Color.WHITE);
					holder.createdby.setBackgroundColor(Color.WHITE);
				}				


				//holder.date.setText(hour+":"+minute);
				holder.createdby.setText("Created by : "+ myTicketsList.get(position)
						.getLastModifiedBy());
				holder.createdby.bringToFront();

			//	holder.date.setText(hour+":"+minute+"  "+dates/*+":"+hour+":"+minute*/);
				
				
				 holder.ticketID.setText(""+myTicketsList.get(position).getTicketId().toString());
			//	holder.ticketID.setText();
				holder.setMqTickets(myTicketsList.get(position));
				//}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return view;
		}

	}
	public String formatMonth(String month)  {
	    SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	    SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
	    try {
			return monthDisplay.format(monthParse.parse(month));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return month;
	}
	class ViewHolder {
		TextView dep_name;
		TextView dep_title;
		TextView desc;
		TextView createdby;
		TextView date;
		TextView count;
		TextView ticketID;
		ImageView arrow;
		MQTickets mqTickets=null;
		public MQTickets getMqTickets() {
			return mqTickets;
		}
		public void setMqTickets(MQTickets mqTickets) {
			this.mqTickets = mqTickets;
		}
		

	}

	 

}
