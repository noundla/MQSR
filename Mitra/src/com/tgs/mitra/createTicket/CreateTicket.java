package com.tgs.mitra.createTicket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tgs.mitra.MainActivity;
import com.tgs.mitra.PlaceholderFragment;
import com.tgs.mitra.R;

public class CreateTicket extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	Spinner spinnerOsversions;
	private String[] state = { "K071002", "K071003", "K071004", "K071005",
			"K071006", "K071007", "K071008", "K071009", "K071010", "K071011",
			"K071012", "K071013", "K071014", "K071015", "K071016", "K071017",
			"K071018", "K071019", "K071020" };
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static CreateTicket newInstance(int sectionNumber) {
		CreateTicket fragment = new CreateTicket();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public CreateTicket() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.createticket, container,
				false);
		spinnerOsversions = (Spinner) rootView.findViewById(R.id.spinnerstate);

		/*ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
				R.layout.spintext, state);
		adapter_state.setDropDownViewResource(R.layout.spintext);
		spinnerOsversions.setAdapter(adapter_state);*/
		TextView heder = (TextView)rootView. findViewById(R.id.main_img);

		Button back = (Button)rootView. findViewById(R.id.back_btnn);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent ir = new Intent(getActivity(), MainActivity.class);

				startActivity(ir);
			}
		});
		heder.setText("Department");

		// Find the ListView resource.
		mainListView = (ListView)rootView. findViewById(R.id.mainListView);

		String[] planets = new String[] { "Payroll", "Human Resource", "IT",
				"Finance", " Loss prevension" };
		Integer[] imageId = { R.drawable.payroll, R.drawable.hr, R.drawable.it,
				R.drawable.fin, R.drawable.hr };

		CustomList listAdapter = new CustomList(getActivity(), planets,
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
					Intent ir = new Intent(getActivity(),
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
	
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}