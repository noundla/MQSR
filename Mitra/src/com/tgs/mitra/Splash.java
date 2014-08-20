package com.tgs.mitra;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

		
		
		ImageView view=(ImageView)findViewById(R.id.splashimg);
		
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
	            R.anim.hyperspace_jump);
		view.startAnimation(hyperspaceJumpAnimation);
	    
	    hyperspaceJumpAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				finish();
				
				Intent loginIntent = new Intent(Splash.this,
						LoginActivity.class);
				startActivity(loginIntent);
			}
			
		});
		
		
		
		
	}

	/*private void startLoginActivty(int delay) {

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
	}*/
}
