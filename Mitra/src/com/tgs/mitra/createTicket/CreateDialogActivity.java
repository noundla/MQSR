package com.tgs.mitra.createTicket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgs.mitra.R;

public class CreateDialogActivity extends Activity {

	String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_ticket_dialog);

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent ir = new Intent(getApplicationContext(),
						Payroll.class);

				startActivity(ir);
			}

		});

		Button cancel = (Button) findViewById(R.id.cancel_button);

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent ir = new Intent(getApplicationContext(),
						Payroll.class);

				startActivity(ir);
			}

		});

		Bundle b = getIntent().getExtras();
		if (b != null) {
			id = b.getString("msg");
			System.out.println("hello" + id);

		}
		TextView deptId = (TextView) findViewById(R.id.tic_name);

		deptId.setText(id);

		Button replay = (Button) findViewById(R.id.reply_button);
		replay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
}
