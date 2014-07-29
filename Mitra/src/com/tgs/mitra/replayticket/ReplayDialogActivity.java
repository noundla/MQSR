package com.tgs.mitra.replayticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.mitra.R;

public class ReplayDialogActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.replay_dialog);
		ContentObject contentObject=(ContentObject) getIntent().getSerializableExtra("OBJ");
		
		TextView title=(TextView)findViewById(R.id.depart_title);
		
		TextView deptId=(TextView)findViewById(R.id.depart_id);
		
		TextView deptDes=(TextView)findViewById(R.id.depart_desc);
		TextView deptName=(TextView)findViewById(R.id.depart_name);
		
		title.setText(contentObject.name);
		deptId.setText(""+contentObject.id);
		deptDes.setText(contentObject.description);
		deptName.setText(contentObject.department);
		
		ImageView replay=(ImageView)findViewById(R.id.reply_button);
		replay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
	}
}
