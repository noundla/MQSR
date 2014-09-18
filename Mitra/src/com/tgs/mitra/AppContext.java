package com.tgs.mitra;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Application;

public class AppContext extends Application{
	
	
	
	boolean valid=true;

	public boolean isValid() {
		return valid;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//Need to check validation
		fetchXML("https://dl.dropboxusercontent.com/u/91525511/test.html");
	}


	public void fetchXML(final String urlString){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					URL url = new URL(urlString);
					HttpURLConnection conn = (HttpURLConnection) 
							url.openConnection();
					conn.setReadTimeout(10000 /* milliseconds */);
					conn.setConnectTimeout(15000 /* milliseconds */);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					conn.connect();
					InputStream stream = conn.getInputStream();

					BufferedReader r = new BufferedReader(new InputStreamReader(stream));
					StringBuilder total = new StringBuilder();
					String line;
					while ((line = r.readLine()) != null) {
						total.append(line);
					}



					valid=total.toString().contains("true");

					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start(); 


	}

}
