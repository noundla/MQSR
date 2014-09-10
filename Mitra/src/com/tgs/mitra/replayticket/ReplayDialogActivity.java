package com.tgs.mitra.replayticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.MQTicketing;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.replayticket.ReplayTicket.ReplyListviewAdapter;
import com.tgs.mitra.replayticket.ReplayTicket.ViewHolder;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQReply;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;

public class ReplayDialogActivity extends Activity {

	private Context _activity = null;
	private ConnectionDetector mConneDetect = null;
	private MQTickets replayTecket = null;
	private String ticket_prority = "low";
	MQTicketing mqTicketing = new MQTicketing();
	//private LinearLayout replayLayout;
	private ListView listView=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_dialog);
		_activity = this;
		mConneDetect = new ConnectionDetector(getApplicationContext());
		replayTecket = (MQTickets) getIntent().getSerializableExtra("MQT_OBJ");

		TextView title = (TextView) findViewById(R.id.depart_title);

		TextView deptId = (TextView) findViewById(R.id.depart_id);

		TextView deptDes = (TextView) findViewById(R.id.depart_desc);
		TextView deptName = (TextView) findViewById(R.id.depart_name);
		//TextView store_id = (TextView) findViewById(R.id.store_id);
		//replayLayout = (LinearLayout) findViewById(R.id.replasys_layout);
		listView=(ListView)findViewById(R.id.replyed_ListView);

		title.setText(replayTecket.getDepartmentName());
		deptId.setText( replayTecket.getTicketId());
		deptDes.setText(replayTecket.getTicketDescription());
		deptName.setText(replayTecket.getTicketTitle());
		
		((TextView)findViewById(R.id.store_id)).setText(User.getInstance().getStoreName());

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();

			}
		});

		Spinner priority = (Spinner) findViewById(R.id.ticket_prority);

		priority.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ticket_prority = ((TextView) arg1).getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		final EditText replyText = (EditText) findViewById(R.id.depart_desc_replay);

		Button replay = (Button) findViewById(R.id.reply_button);
		replay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (replyText.getText().toString().trim().length() > 1) {
					if (mConneDetect.isConnectingToInternet()) {
						// Creating MQTicketing objecct from MQTicket

						mqTicketing.setAssignedOwner("");
						mqTicketing.setCopyToEmail("");
						mqTicketing.setCreatedDate(replayTecket
								.getLastModified()); // Maybe we need to send
														// Defauld date
						mqTicketing
								.setCreatedUser(User.getInstance().getUser());
						mqTicketing.setDepartment(replayTecket
								.getDepartmentName());
						mqTicketing.setDetails(replyText.getText().toString());
						mqTicketing.setDueDate(replayTecket.getLastModified());// No
																				// need
																				// to
																				// pass
																				// //Maybe
																				// we
																				// need
																				// to
																				// send
																				// Defauld
																				// date
						mqTicketing
								.setGuidfield("99999999-9999-9999-9999-999999999999");// No
																						// need
																						// it's
																						// generate
																						// automatically.

						Date javaUtilDate = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss"); // Soap required
															// format
						mqTicketing.setLastChange(formatter
								.format(javaUtilDate));
						mqTicketing.setLastChangeUser(User.getInstance()
								.getUser());
						mqTicketing.setPriority(ticket_prority);
						mqTicketing.setReplyId(replayTecket.getTicketId());// NO
																			// need
						mqTicketing.setStoreId(User.getInstance()
								.getStoreName());
						mqTicketing.setTicketId("0");
						mqTicketing.setTicketStatus("Close");// We need to send
																// close
						mqTicketing.setTitle(replayTecket.getTicketTitle());

						DoBackground mBackground = new DoBackground();
						mBackground.execute(replyText.getText().toString());
					} else {
						Toast.makeText(_activity,
								getString(R.string.connection_error),
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(_activity, "Invalid input.",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		if (replayTecket.isHasReplay()) {
			// Get replays
			ReplayListTask listTask = new ReplayListTask();
			listTask.execute(replayTecket.getTicketId());
		}

	}

	class ReplayListTask extends AsyncTask<String, Void, Void> {

		private ArrayList<MQReply> replayList = null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... arg0) {

			if (mConneDetect.isConnectingToInternet()) {
				UtilMethod method = new UtilMethod();

				replayList = method.getTicketPopup(User.getInstance(), arg0[0]);
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);

			 
		 
			if (replayList != null) {/*
				for (int i = 0; i < replayList.size(); i++) {
					replayText = new TextView(_activity);
					replayText.setText(replayList.get(i).getReplayMessage().trim()+" "+" Add Created datae and time and created by also");
					replayText.setTextColor(getResources().getColor(android.R.color.black));
					replayText.setTextSize(16);
					System.out.println("TEST REP :"+replayList.get(i).getReplayMessage());
					//replayText.setBackgroundResource(R.drawable.table_sharep);

					view=new View(_activity);
					view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					view.setMinimumHeight(1);
					
					//replayText.setText("SAMPLE");
					replayLayout.addView(replayText, lp);
					replayLayout.addView(view, lp);
				}
			*/
				//Added new UI
				listView.setAdapter(new ReplyListviewAdapter(
						getApplicationContext()));
			}
		}

		
		//////////////////////////////////////////
		public class ReplyListviewAdapter extends BaseAdapter {

			LayoutInflater inflater;
			Context context;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();

			public ReplyListviewAdapter(Context context) {
				// TODO Auto-generated constructor stub
				this.context = context;
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return replayList.size();
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

					view = inflater.inflate(R.layout.popup_ticket_list_item, null);
					holder.dep_name = (TextView) view
							.findViewById(R.id.department_name);
					holder.dep_title = (TextView) view
							.findViewById(R.id.depart_title);
					holder.desc = (TextView) view.findViewById(R.id.depart_desc);
					// holder.month = (TextView) view.findViewById(R.id.emp_bmonth);
					holder.createdby = (TextView) view
							.findViewById(R.id.created_user);
					//holder.date = (TextView) view.findViewById(R.id.created_date);
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
					holder.dep_title.setText("-"+replayList.get(position)
							.getTicketTitle());
					holder.dep_name.setText(replayList.get(position)
							.getDepartmentName());

					holder.desc.setText(replayList.get(position)
							.getTicketDescription());
					/*	holder.createdby.setText("Created by : "+ myTicketsList.get(position)
									.getLastModifiedBy()+" On "+ myTicketsList.get(position)
									.getLastModified());*/


					//2014-09-01T09:17:18.05

					String dt = replayList.get(position)
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
						//System.out.println("hell.."+Integer.valueOf(hour)%12 + ":" + minute + " " + ((Integer.valueOf(hour)>=12) ? "PM" : "AM"));
						
						holder.count.setText(Integer.valueOf(hour)%12 + ":" + minute + " " + ((Integer.valueOf(hour)>=12) ? "PM" : "AM"));
						holder.count.setVisibility(View.VISIBLE);
		
						
					}else{
						holder.count.setText(formatMonth(month).substring(0, 3)+" "+day/*+","+year */);
						holder.count.setVisibility(View.VISIBLE);
						//System.out.println("hell.1."+formatMonth(month).substring(0, 3)+" "+day/*+","+year*/);
					}
					
				 		
					/*if(replayList.get(position).getReplayCount()!=0)
					{
					//holder.date.setText("Replays("+myTicketsList.get(position).getReplayCount()+")");
					holder.dep_title.setText("-"+replayList.get(position)
							.getTicketTitle()+" ");
					holder.date.setText("   "+replayList.get(position).getReplayCount());
					holder.date.setVisibility(View.VISIBLE);
					holder.arrow .setVisibility(View.VISIBLE);
					view.setBackgroundColor(_activity.getResources().getColor(R.color.reply_color));
					}else{
						holder.date.setVisibility(View.INVISIBLE);
						holder.arrow .setVisibility(View.GONE);
						view.setBackgroundColor(Color.WHITE);
					}	*/			


					//holder.date.setText(hour+":"+minute);
					holder.createdby.setText("Created by : "+ replayList.get(position)
							.getLastModifiedBy());

				//	holder.date.setText(hour+":"+minute+"  "+dates/*+":"+hour+":"+minute*/);
					
					
					 holder.ticketID.setText(""+replayList.get(position).getTicketId().toString());
				//	holder.ticketID.setText();
					holder.setMqTickets(replayList.get(position));
					//}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				return view;
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

		}
		/////////////////////////////////////////
		
	}

	
	
	

	class ViewHolder {
		TextView dep_name;
		TextView dep_title;
		TextView desc;
		TextView createdby;
		//TextView date;
		TextView count;
		TextView ticketID;
		ImageView arrow;
		MQReply mqReplay=null;
		public MQReply getMqTickets() {
			return mqReplay;
		}
		public void setMqTickets(MQReply mqReplay) {
			this.mqReplay = mqReplay;
		}


	}

	
	
	
	class DoBackground extends AsyncTask<String, Void, Void> {
		ProgressDialog dialog = null;
		private ArrayList<MQTickets> myTicketsList = null;

		boolean status = false;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(_activity);
			dialog.setTitle("Saving...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {

			if (mConneDetect.isConnectingToInternet()) {
				UtilMethod method = new UtilMethod();
				status = /* method.createTicket(User.getInstance(), mqTicketing); */method
						.replayTicket(User.getInstance(), mqTicketing);
				// method.g
				// method.replayTicket(User.getInstance(), replayTecket);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (status) {
				Toast.makeText(_activity, "Done successfully ",
						Toast.LENGTH_LONG).show();
				finish();
			} else {
				Toast.makeText(_activity, "Not successfully Done! ",
						Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
}
