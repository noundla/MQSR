package com.tgs.mitra.replayticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tgs.mitra.R;

public class PopupActivity extends Activity{
	
	  String[] values = new String[] { "reply1", 
			  "reply2", "reply3", "reply4"
             };
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);

	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.popup_listv);
	
	ListView popup_listv= (ListView)findViewById(R.id.popuplistv);
	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
             android.R.layout.simple_list_item_1, android.R.id.text1, values);
   
   
           // Assign adapter to ListView
	 popup_listv.setAdapter(adapter); 
}
}
