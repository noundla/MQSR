package com.tgs.mitra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BottomLayout extends RelativeLayout{
	LayoutInflater mInflater;
	public BottomLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		  mInflater = LayoutInflater.from(context);
		  init();
	}
    public BottomLayout(Context context, AttributeSet attrs, int defStyle)
    {
    super(context, attrs, defStyle);
    mInflater = LayoutInflater.from(context);
    init(); 
    }
    public BottomLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    mInflater = LayoutInflater.from(context);
    init(); 
    }

    private void init(){
    	  mInflater.inflate(R.layout.common_layout, this, true);
        //  Button home_tab = (Button) findViewById(R.id.btn_create);
          Button create_tabe = (Button) findViewById(R.id.btn_create);
          Button reply_tab = (Button) findViewById(R.id.btn_reply);
    
    }
    
    
}
