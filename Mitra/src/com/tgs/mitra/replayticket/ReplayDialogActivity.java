package com.tgs.mitra.replayticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.MQTicketing;
import com.tgs.mitra.bean.User;
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
	private LinearLayout replayLayout;

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

		replayLayout = (LinearLayout) findViewById(R.id.replasys_layout);

		title.setText("Department : " + replayTecket.getDepartmentName());
		deptId.setText("Ticket Id : " + replayTecket.getTicketId());
		deptDes.setText(replayTecket.getTicketDescription());
		deptName.setText(replayTecket.getTicketTitle());

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

			TextView replayText = null;
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

			System.out.println("TEST REplay:"+replayList.size());
			if (replayList != null) {
				for (int i = 0; i < replayList.size(); i++) {
					replayText = new TextView(_activity);
					replayText.setText(replayList.get(i).getReplayMessage());
					replayText.setTextColor(color.black);
					replayText.setTextSize(16);
					System.out.println("TEST REP :"+replayList.get(i).getReplayMessage());
					//replayText.setBackgroundResource(R.drawable.table_sharep);

					replayLayout.addView(replayText, lp);
				}
			}
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
