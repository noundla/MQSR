package com.tgs.mitra.replayticket;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tgs.mitra.MainActivity;
import com.tgs.mitra.PlaceholderFragment;
import com.tgs.mitra.R;

public class ReplayTicket extends Fragment implements OnClickListener {
	ArrayList<ContentObject> arrayList=new ArrayList<ContentObject>();

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ReplayTicket newInstance(int sectionNumber) {
		ReplayTicket fragment = new ReplayTicket();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ReplayTicket() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.replay_xml, container,
				false);
Button back= (Button)rootView.findViewById(R.id.back_btnn);
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent ir = new Intent(getActivity(), MainActivity.class);
				
			startActivity(ir);
			}
		});
		LinearLayout contentLayout=(LinearLayout)rootView.findViewById(R.id.content_layout);
		
		ContentObject contentObject=null;
		for (int i = 0; i < 15; i++) {
			
			contentObject=new ContentObject();
			if(i%2==0)
				
			contentObject.department="HR";
			else
				contentObject.department="PayRoll";
			
			contentObject.description="This is descripatio about "+contentObject.department+" Department , Requesting about.......\n Please solve.";
			contentObject.name=""+contentObject.department;
			contentObject.id="K07"+(1001+i);
			arrayList.add(contentObject);
		}
		
		for (int i = 0; i < arrayList.size(); i++) {
			
			View view=View.inflate(getActivity(), R.layout.content_layout, null);
			
			TextView title=(TextView)view.findViewById(R.id.depart_title);
			
			TextView deptId=(TextView)view.findViewById(R.id.depart_id);
			
			TextView deptDes=(TextView)view.findViewById(R.id.depart_desc);
			TextView deptName=(TextView)view.findViewById(R.id.depart_name);
			
			title.setText(arrayList.get(i).name);
			deptId.setText(""+arrayList.get(i).id);
			deptDes.setText(arrayList.get(i).description);
		//	deptName.setText(arrayList.get(i).department+"dsfds");
			deptName.setText("7/4/2014");
			ImageView button=(ImageView)view.findViewById(R.id.reply_button);
			
			button.setTag(arrayList.get(i));
			button.setOnClickListener(this);
			
			contentLayout.addView(view);
		}
		
	
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(),ReplayDialogActivity.class);
		 ContentObject contenObj=((ContentObject)v.getTag());
		 intent.putExtra("OBJ",(Serializable) contenObj);
				startActivity(intent);
	}
}