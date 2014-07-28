package com.tgs.mitra;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
/**
 * 
 * @author Rajesh
 *
 */
public class Splash  extends Activity{
	private final int DELAY = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		startLoginActivty(DELAY);
	}

	private void startLoginActivty(int delay) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Intent loginIntent = new Intent(Splash.this,
						LoginActivity.class);
				startActivity(loginIntent);
			}
		};

		Handler handler = new Handler();
		handler.postDelayed(runnable, delay);
	}
}
