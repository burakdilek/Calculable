package com.calculable;

import java.util.Random;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NormalCarpma extends Activity {
	public TextView text;
	public TextView sayac;
	public Button dogru, yanlis, yeni, menu;
	public TextView chro, toplam, gotext;
	public boolean xa;
	public int ilk;
	public int x =15000, y = 100 ;
	public int son;
	public int sonuc,ilksonuc,sonsonuc;
	public int[] array, array1, array2,array3;
	public Random r;
	public int gercek;
	public int sonuc1, sonuc2, sonuc3, sonuc4,sonuc5;
	public int i = 1;
	public String go;
	public static CountDownTimer sure;
	public Animation blink;
	Dialog sonuc_dialog;
	MediaPlayer mp;

	SharedPreferences prefs;
	Editor editor;



	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(getResources().getString(R.string.quit))
		.setMessage(getResources().getString(R.string.gobackmenu))
		.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();       
			}

		})
		.setNegativeButton(getResources().getString(R.string.no), null)
		.show();
	}

	protected void yeniSoru(){
		/*if (i ==1){}
		else{
			chro.setBase(SystemClock.elapsedRealtime()+time);
			chro.start();
		}*/

		if (x == 5000){}
		else if(i%3==0){
			x = x-100;
		}
		else{}

		sayac.setText(String.valueOf(i));
		r =new Random();
		
		ilk =r.nextInt(100);
		son =r.nextInt(10);
		
		gercek = ilk*son;
		sonuc1 =gercek+100;
		sonuc2=gercek-100;
		sonuc3=gercek+10;
		sonuc3=gercek-10;
		
		array1 = new int[]{sonuc1,sonuc2};
		ilksonuc = array1[r.nextInt(2)];
		
		array2 = new int[]{sonuc3,sonuc4};
		sonsonuc = array2[r.nextInt(2)];
		
		array3 = new int[]{ilksonuc,sonsonuc};
		sonuc5 = array3[r.nextInt(2)];
		
		array = new int[]{sonuc5,gercek};
		sonuc = array[r.nextInt(2)];
		
		
		text.setText(String.valueOf(ilk)+" x "+String.valueOf(son)+"\n = "+String.valueOf(sonuc));
		//ilk deðer saniyeyi deðiþtiriyor
		sure = new CountDownTimer(x, y) {

			public void onTick(long millisUntilFinished) {
				chro.setText(String.valueOf(millisUntilFinished / y));
			}

			public void onFinish() {
				go =getResources().getString(R.string.time_alert);
				puanlama();
				gotext.setText(go);
				sonuc_dialog.show();
				chro.setText("!"); 
				chro.startAnimation(blink);
			}
		};
		if (i != 1){
			chro.setText("...");
			sure.start();
		}
		/* if (xa==true){
			  	go ="Süre Bitti :(";
		    	Intent intent = new Intent(this, Sonuc.class);
		    	intent.putExtra("p", i);
		 	    intent.putExtra("go", go);
		 	    intent.putExtra("i", 0);
		 	    startActivity(intent);
		  }*/;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide statusbar of Android
		// could also be done later
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hizli_toplama);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.game_layout);
		layout.setBackgroundResource(R.color.yellow);

		yanlis =(Button) findViewById(R.id.yanlis);
		dogru =(Button) findViewById(R.id.dogru);
		text =(TextView) findViewById(R.id.textView1);
		sayac =(TextView) findViewById(R.id.sayac);
		chro = (TextView) findViewById(R.id.chronometer1);
		blink = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.blink);

		prefs = this.getSharedPreferences("normalCbest", Context.MODE_PRIVATE);
		editor = prefs.edit();

		mp = MediaPlayer.create(NormalCarpma.this,R.raw.button16);

		sonuc_dialog = new Dialog(NormalCarpma.this);
		sonuc_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		sonuc_dialog.setContentView(R.layout.dialog_sonuc);
		sonuc_dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

		toplam =(TextView)sonuc_dialog.findViewById(R.id.toplam1);
		gotext =(TextView)sonuc_dialog.findViewById(R.id.go1);
		yeni =(Button)sonuc_dialog.findViewById(R.id.button2);
		yeni.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				sure.cancel();
				chro.setText("...");
				chro.clearAnimation();
				i = 1;
				x =15000; 
				y = 100 ;
				yeniSoru();
				sonuc_dialog.dismiss();
			}
		});
		menu =(Button)sonuc_dialog.findViewById(R.id.button1);
		menu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		sonuc_dialog.setCancelable(false);
		yeniSoru();

	}

	public void Dogru(View view){
		yanlis.setClickable(false);
		mp.start();
		if(ilk*son==sonuc){
			i++;
			sure.cancel();
			yeniSoru();	
		}
		else{
			sure.cancel();
			go =getResources().getString(R.string.wrong_alert);
			gotext.setText(go);
			chro.setText("...");
			puanlama();
			sonuc_dialog.show();
			/*Intent intent = new Intent(this, Sonuc.class);
			intent.putExtra("p", i);
			intent.putExtra("i", 1);
			intent.putExtra("s", 0);
			intent.putExtra("go", go);
			startActivity(intent);*/

		};
		yanlis.setClickable(true);
	}
	public void Yanlis(View view){
		mp.start();
		dogru.setClickable(false);
		if(ilk*son !=sonuc){

			i++;
			sure.cancel();
			yeniSoru();			

		}
		else{
			sure.cancel();
			go =getResources().getString(R.string.wrong_alert);
			gotext.setText(go);
			puanlama();

			sonuc_dialog.show();
			/*Intent intent = new Intent(this, Sonuc.class);
			intent.putExtra("p", i);
			intent.putExtra("go", go);
			intent.putExtra("i", 1);
			startActivity(intent);*/


		};
		dogru.setClickable(true);
	}	
	public void puanlama(){
		int score = prefs.getInt("key", 0);
		if(i - 1 > score ){
			editor.putInt("key", i - 1);
			editor.commit();
			toplam.setText(getResources().getString(R.string.nevv)+" : "+ String.valueOf(i-1)+"\n" + getResources().getString(R.string.best) +" : "+ String.valueOf(i-1));
		}
		else{
			toplam.setText(getResources().getString(R.string.nevv)+" : "+ String.valueOf(i-1)+"\n" + getResources().getString(R.string.best) +" : "+ score );
		}
	}
}
