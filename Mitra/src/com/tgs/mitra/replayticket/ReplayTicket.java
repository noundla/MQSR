package com.tgs.mitra.replayticket;

import java.io.Serializable;
import java.util.ArrayList;

import com.tgs.mitra.HomePage;
import com.tgs.mitra.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReplayTicket extends Activity implements OnClickListener{
	ArrayList<ContentObject> arrayList=new ArrayList<ContentObject>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_xml);
Button back= (Button)findViewById(R.id.back_btnn);
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent ir = new Intent(getApplicationContext(), HomePage.class);
				
			startActivity(ir);
			}
		});
		LinearLayout contentLayout=(LinearLayout)findViewById(R.id.content_layout);
		
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
			
			View view=View.inflate(getApplicationContext(), R.layout.content_layout, null);
			
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
		
	}
	
	
	/*public class ContentObject implements Serializable
	{
		String name="";
		
		String id="";
		
		String department="";
		String description="";
		
	}*/


	@Override
	public void onClick(View arg0) {

// Toast.makeText(getApplicationContext(), "Selected" +((ContentObject)arg0.getTag()).id, Toast.LENGTH_LONG).show();
 Intent intent=new Intent(ReplayTicket.this,ReplayDialogActivity.class);
 ContentObject contenObj=((ContentObject)arg0.getTag());
 intent.putExtra("OBJ",(Serializable) contenObj);
		startActivity(intent);
	}
	
	
}
