package com.tgs.mitra;



import com.tgs.mitra.bean.User;
import com.tgs.mitra.util.ConnectionDetector;
import com.tgs.mitra.util.UtilMethod;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private ConnectionDetector mConneDetect=null;
	private Context _activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.loginpage);
		
		_activity=this;
		
		Button  login_btn=(Button)findViewById(R.id.login_button1);
		
		final EditText user=(EditText)findViewById(R.id.edit_user);
		final EditText password=(EditText)findViewById(R.id.edit_passwd);
		
		  mConneDetect =new ConnectionDetector(getApplicationContext());
		  
		  if(mConneDetect.isConnectingToInternet())
		  {
			  login_btn.setEnabled(true);
		  }else{
			  
			  Toast.makeText(getApplicationContext(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
			  login_btn.setEnabled(false);
		  }
		login_btn.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
            	
            	if((user.getText().toString().trim().length()>1) && (password.getText().toString().trim().length()>1))
            	{
            		 User userObject=User.getInstance();
            			  userObject.setUser(user.getText().toString().trim());
            			  userObject.setPassword(password.getText().toString().trim());
            			  
            			  DologinBackground doBackground=new DologinBackground();
            			  doBackground.execute("","");
            		
            	}
            	else{
            		Toast.makeText(getApplicationContext(), "Please enter valid credentials", Toast.LENGTH_LONG).show();
            	}
            }
        });

		
	}
	
	class DologinBackground extends AsyncTask<String, Boolean, Boolean>
	{
		ProgressDialog dialog=null;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog=new ProgressDialog(_activity);
			dialog.setTitle("Loading...");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			
			 boolean state=false;
			 try{
				 
				 UtilMethod method=new UtilMethod();
				  
					
				  state=method.getLoginState(User.getInstance());
			 }catch (Exception e) {
				e.printStackTrace();
			}
			
			return state;
		}

		 @Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			dialog.dismiss();
			if(result)
			{
				finish();
				Intent i3 = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(i3);
			}else{
				Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
			}
			
			
			
		}
		

		 
	}

}
