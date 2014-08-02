package com.tgs.mitra.createTicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.mitra.R;
import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.MQDetQsn;
import com.tgs.mitra.util.UtilMethod;

public class Payroll extends Activity {

	private ListView mainListView;
	//private ArrayAdapter<String> listAdapter;
	String id;
	private Context _activity=null;
	ConnectionDetector mConneDetect=null;
	public static String creat_message;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createticket);

		_activity=this;
		mConneDetect =new ConnectionDetector(getApplicationContext());
		TextView heder= (TextView)findViewById(R.id.main_img);
		Button back= (Button)findViewById(R.id.back_btnn);


		final Department department=(Department) getIntent().getSerializableExtra("DEPRT_OBJ");

		DoBackground background=new DoBackground();
		background.execute(department);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();

				Intent ir = new Intent(getApplicationContext(), CreateTicket.class);

				startActivity(ir);
			}
		});

		heder.setText(department.getDepartment());
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);


		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("TEST RESUL"+view.getTag());
				//String name = parent.getItemAtPosition(position).toString();
				Intent intent=new Intent(Payroll.this,CreateDialogActivity.class);
				intent.putExtra("OBJ_QT", (Serializable)(MQDetQsn)view.getTag());
				intent.putExtra("OBJ_DPT", (Serializable)department);
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


	class DoBackground extends AsyncTask<Department, Void, Void>
	{
		ProgressDialog dialog=null;
		private ArrayList<MQDetQsn> departQsnsList=null;


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}
		@Override
		protected Void doInBackground(Department... arg0) {

			if(mConneDetect.isConnectingToInternet())
			{
				UtilMethod method=new UtilMethod();
				departQsnsList=method.getDeptQuestions(User.getInstance(), arg0[0].getGuidfield(), "");
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			/*ArrayList<String> questionList = new ArrayList<String>();

			if(departQsnsList!=null)
			{
				for (int i = 0; i < departQsnsList.size(); i++) {


					questionList.add(departQsnsList.get(i).getQuestionTitle());

				}
				listAdapter = new ArrayAdapter<String>(Payroll.this, R.layout.listtext,
						questionList);*/
			MyCustom myCustom=new MyCustom(Payroll.this, departQsnsList);
				mainListView.setAdapter(myCustom);



			dialog.dismiss();
		}
	}

	class MyCustom extends ArrayAdapter<MQDetQsn>
	{
		ArrayList<MQDetQsn> quationList=null;
		private Activity context;


		public MyCustom(Activity context,   
				ArrayList<MQDetQsn> quationList) {
			super(context,  R.layout.department, quationList);
			this.quationList=quationList;
			this.context = context;
		}

		
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = inflater.inflate(R.layout.department, null, true);
			TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
			txtTitle.setText(quationList.get(position).getQuestionTitle());
		
			 
				imageView.setImageResource(R.drawable.hr);
			
			rowView.setTag(quationList.get(position));
			return rowView;
		}
		




	}

}