package com.calculable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
 

public class Splash extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    Animation move;
    ImageView logo;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
	    // hide statusbar of Android
	    // could also be done later
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        move = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        logo =(ImageView) findViewById(R.id.imageView1);
        
		logo.startAnimation(move);

        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                
            }
        }, SPLASH_TIME_OUT);
    }
    protected void onPause() {
		 if (this.isFinishing()){ //basically BACK was pressed from this activity
		 //mp.stop();
		  }
		else{

		}
		 Context context = getApplicationContext();
		 ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
		 if (!taskInfo.isEmpty()) {
		 ComponentName topActivity = taskInfo.get(0).topActivity; 
		 if (!topActivity.getPackageName().equals(context.getPackageName())) {
		 //mp.stop();

		}
		}
		   super.onPause();

		 }
 
}
