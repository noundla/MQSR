package com.tgs.mitra.createTicket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tgs.mitra.HomePage;
import com.tgs.mitra.R;

public class CreateTicket extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	Spinner spinnerOsversions;
	private String[] state = { "K071002", "K071003", "K071004", "K071005",
			"K071006", "K071007", "K071008", "K071009", "K071010", "K071011",
			"K071012", "K071013", "K071014", "K071015", "K071016", "K071017",
			"K071018", "K071019", "K071020" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createticket);
		spinnerOsversions = (Spinner) findViewById(R.id.spinnerstate);

		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
				R.layout.spintext, state);
		adapter_state.setDropDownViewResource(R.layout.spintext);
		spinnerOsversions.setAdapter(adapter_state);
		TextView heder = (TextView) findViewById(R.id.main_img);

		Button back = (Button) findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent ir = new Intent(getApplicationContext(), HomePage.class);

				startActivity(ir);
			}
		});
		heder.setText("Department");

		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);

		String[] planets = new String[] { "Payroll", "Human Resource", "IT",
				"Finance", " Loss prevension" };
		Integer[] imageId = { R.drawable.payroll, R.drawable.hr, R.drawable.it,
				R.drawable.fin, R.drawable.hr };

		CustomList listAdapter = new CustomList(CreateTicket.this, planets,
				imageId);

		/*
		 * ArrayList<String> planetList = new ArrayList<String>();
		 * planetList.addAll( Arrays.asList(planets) );
		 */

		// listAdapter = new ArrayAdapter<String>(this, R.layout.listtext,
		// planetList);

		mainListView.setAdapter(listAdapter);
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name = parent.getItemAtPosition(position).toString();

				switch (position) {
				case 0:
					Intent ir = new Intent(getApplicationContext(),
							Payroll.class);
					ir.putExtra("msg", name);
					startActivity(ir);
					break;
				case 1:
					/*
					 * Intent i = new Intent(getActivity(), ActioPage.class);
					 * startActivity(i);
					 */
					break;
				case 2:
					/*
					 * Intent i1 = new Intent(getApplicationContext(),
					 * Payroll.class); startActivity(i1);
					 */
					break;

				default:
					break;
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent ir = new Intent(getApplicationContext(), HomePage.class);

		startActivity(ir);
		
	}
}
