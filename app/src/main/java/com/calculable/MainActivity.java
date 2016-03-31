package com.calculable;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;



public class MainActivity extends Activity {

	Button hc,ht,nc,nt,about;


	public void onBackPressed() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(getResources().getString(R.string.quit))
		.setMessage(getResources().getString(R.string.goquit))
		.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);       
			}

		})
		.setNegativeButton(getResources().getString(R.string.no), null)
		.show();



	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide statusbar of Android
		// could also be done later
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		ht = (Button)findViewById(R.id.button4);
		hc = (Button)findViewById(R.id.button2);
		nt = (Button)findViewById(R.id.button3);
		nc = (Button)findViewById(R.id.button1);
		about = (Button)findViewById(R.id.button5);


	}


	public void hizlitoplama(View view){
		clickblefalse();
		Intent intent = new Intent(this, HizliToplama.class);
		startActivity(intent);	
		clickbletrue();
	}
	public void hizlicarpma(View view){
		clickblefalse();
		Intent intent = new Intent(this, HizliCarpma.class);
		startActivity(intent);	
		clickbletrue();
	}
	public void normalcarpma(View view){
		clickblefalse();
		Intent intent = new Intent(this, NormalCarpma.class);
		startActivity(intent);	
		clickbletrue();
	}
	public void normaltoplama(View view){
		clickblefalse();
		Intent intent = new Intent(this, NormalToplama.class);
		startActivity(intent);	
		clickbletrue();
	}
	public void ayarlar(View view){
		clickblefalse();
		Intent intent = new Intent(this, Ayarlar.class);
		startActivity(intent);	
		clickbletrue();
	}
	public void clickblefalse(){
		hc.setClickable(false);
		ht.setClickable(false);
		nc.setClickable(false);
		nt.setClickable(false);
		about.setClickable(false);
		hc.setClickable(true);
		ht.setClickable(true);
		nc.setClickable(true);
		nt.setClickable(true);
		about.setClickable(true);
	}
	public void clickbletrue(){
	
		hc.setClickable(true);
		ht.setClickable(true);
		nc.setClickable(true);
		nt.setClickable(true);
		about.setClickable(true);
	}

}
