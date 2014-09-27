package com.tgs.mitra;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tgs.qsr.support.HomeScreenInfo;
import com.tgs.qsr.support.User;
import com.tgs.qsr.support.UtilMethod;

public class Fragment1 extends Fragment{
	private TextView text;

	private Button logout_btn,create_btn,reply_btn;

	private ListView homeListView;
	ProgressBar homeProgressBar=null;
	private Spinner mSpinner=null;

	private ArrayList<HomeScreenInfo> homeInfoList=null;

	public Fragment1() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		StoreListTaks storeListTaks=new StoreListTaks();
		storeListTaks.execute();

		HomeInfoTaks homeInfoTaks=new HomeInfoTaks();
		homeInfoTaks.execute();
		super.onCreate(savedInstanceState);

	}
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout,
				null);
		text = (TextView) v.findViewById(R.id.text);
		if (getArguments() != null) {
			//
			try {
				String value = getArguments().getString("key");
				text.setText("Current Tab is: " + value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("TEST Fragment1 onCreateView");
		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		System.out.println("TEST Fragment1 onActivityCreated");
	}*/


	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,   Bundle savedInstanceState) {

		AppContext globalVariable = (AppContext) getActivity().getApplicationContext();

		if(!globalVariable.isValid())
		{
			Toast.makeText( getActivity(), getString(R.string.error), Toast.LENGTH_LONG).show();

			getActivity().finish();

		}


		View v = LayoutInflater.from(getActivity()).inflate(R.layout.home,
				null);

		homeProgressBar=(ProgressBar)v.findViewById(R.id.progressBar1);

		homeProgressBar.setVisibility(View.VISIBLE);



		homeListView = (ListView) v.findViewById(R.id.homelist);

		mSpinner = (Spinner) v.findViewById(R.id.store_spinner);

		logout_btn=(Button)v.findViewById(R.id.logout);
		logout_btn.bringToFront();
		create_btn=(Button)v.findViewById(R.id.btn_create);
		reply_btn=(Button)v.findViewById(R.id.btn_reply);

		//create_btn.setOnClickListener(listener);
		//reply_btn.setOnClickListener(listener);

		System.out.println("TEST FRSG1 :"+homeInfoList);

		if(homeInfoList!=null)
		{
			setHomeInfo();
		}


		reply_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				/*String mTag="create";
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

				Fragment mFragment =getActivity().getSupportFragmentManager().findFragmentByTag(mTag);
		        if (mFragment == null) {
		            mFragment = Fragment
		                    .instantiate(getActivity(),Fragment3.class.getName(), null);
		            ft.replace (R.id.main_layout, mFragment, mTag);
		            ft.commit();
		        } else {
		            ft.attach(mFragment);
		            ft.commit();
		        }*/
				
				Fragment2 fragment2 = new Fragment2();
			    FragmentManager fragmentManager = getFragmentManager();
			    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			    fragmentTransaction.replace(R.id.realtabcontent, fragment2);
			    fragmentTransaction.commit();
			}
		});



		return v;
	}


	public class StoreListTaks extends AsyncTask<Void, Void, Void>
	{
		private ArrayList<String> storeList=null;
		private ProgressDialog dialog=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(getActivity());
			dialog.setTitle("Loading...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			UtilMethod method=new UtilMethod(getActivity());
			storeList= method.getUserallowedstoresList(User.getInstance());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);


			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
					R.layout.listtext, storeList);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpinner.setAdapter(dataAdapter);

			dialog.dismiss();
		}
	}

	class HomeInfoTaks extends AsyncTask<Void, Void, Void>
	{


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			UtilMethod method=new UtilMethod(getActivity());
			homeInfoList= method.getHomeScreenInfoList(User.getInstance());


			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);


			homeProgressBar.setVisibility(View.GONE);


			setHomeInfo();

		}
	}

	private void setHomeInfo() {

		if(homeInfoList!=null)
		{
			CustomHomeList homelistAdapter = new CustomHomeList(getActivity(),homeInfoList);
			homeListView.setAdapter(homelistAdapter);
			homeListView.invalidateViews();
		}

	}

}
