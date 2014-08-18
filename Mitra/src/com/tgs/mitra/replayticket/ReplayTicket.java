package com.tgs.mitra.replayticket;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.createTicket.CreateTicket;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQTickets;
import com.tgs.mitra.util.UtilMethod;

public class ReplayTicket extends Activity implements OnClickListener {
	public static final ListAdapter ReplyListviewAdapter = null;
	// ArrayList<ContentObject> arrayList=new ArrayList<ContentObject>();
	private Context _activity = null;
	private ConnectionDetector mConneDetect = null;
	private LinearLayout contentLayout = null;
	ListView reply_list_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_xml);
		_activity = this;
		mConneDetect = new ConnectionDetector(getApplicationContext());

		DoBackground background = new DoBackground();
		background.execute();

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
				/*
				 * Intent ir = new Intent(getApplicationContext(),
				 * HomePage.class);
				 * 
				 * startActivity(ir);
				 */
			}
		});
		// contentLayout=(LinearLayout)findViewById(R.id.content_layout);
		reply_list_view = (ListView) findViewById(R.id.reply_ListView);

		reply_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

		Button create_btn = (Button) findViewById(R.id.btn_create);
		Button reply_btn = (Button) findViewById(R.id.btn_reply);

		create_btn.setOnClickListener(listener);
		reply_btn.setOnClickListener(listener);
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

		/*		Intent intent = new Intent(getApplicationContext(),
						ReplayTicket.class);
				startActivity(intent);*/
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
				myTicketsList = method.getReplyTicketsList(User.getInstance()); // method.getMyTeckets(User.getInstance(),
																				// "Open");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			reply_list_view.setAdapter(new ReplyListviewAdapter(
					getApplicationContext()));

			dialog.dismiss();
		}
	}

	public class ReplyListviewAdapter extends BaseAdapter {

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
			System.out.println("helloooo...." + myTicketsList.size());
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
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			try {
				if (myTicketsList != null)

					for (int i = 0; i < myTicketsList.size(); i++) {
						holder.dep_title.setText(myTicketsList.get(i)
								.getTicketTitle());
						holder.dep_name.setText(myTicketsList.get(i)
								.getDepartmentName());

						holder.desc.setText(myTicketsList.get(i)
								.getTicketDescription());
						holder.createdby.setText(myTicketsList.get(i)
								.getLastModifiedBy());

						holder.date.setText(myTicketsList.get(i)
								.getLastModified());
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return view;
		}

	}

	class ViewHolder {
		TextView dep_name;
		TextView dep_title;
		TextView desc;
		TextView createdby;
		TextView date;

	}

	@Override
	public void onClick(View arg0) {

		// Toast.makeText(getApplicationContext(), "Selected"
		// +((ContentObject)arg0.getTag()).id, Toast.LENGTH_LONG).show();
		Intent intent = new Intent(ReplayTicket.this,
				ReplayDialogActivity.class);
		intent.putExtra("MQT_OBJ", (Serializable) (MQTickets) arg0.getTag());
		startActivity(intent);
	}

}
