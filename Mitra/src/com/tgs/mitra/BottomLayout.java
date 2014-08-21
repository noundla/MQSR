package com.tgs.mitra;

import com.tgs.mitra.createTicket.CreateTicket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BottomLayout extends RelativeLayout {
	LayoutInflater mInflater;
	Context context;
	public BottomLayout(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
		  mInflater = LayoutInflater.from(context);
		  init();
	}
    public BottomLayout(Context context, AttributeSet attrs, int defStyle)
    {
    super(context, attrs, defStyle);
    this.context=context;
    mInflater = LayoutInflater.from(context);
    init(); 
    }
    public BottomLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context=context;
    mInflater = LayoutInflater.from(context);
    init(); 
    }

    private void init(){
    	  mInflater.inflate(R.layout.common_layout, this, true);
        //  Button home_tab = (Button) findViewById(R.id.btn_create);
          Button create_tabe = (Button) findViewById(R.id.btn_create);
          Button reply_tab = (Button) findViewById(R.id.btn_reply);
          final Button home_tab = (Button) findViewById(R.id.home_button);
         
          home_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//home_tab.setBackgroundColor(Color.BLUE);
				 
				Intent i = new Intent(context,
						HomePage.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
				
			}
		});
    
    }
    
    
}
