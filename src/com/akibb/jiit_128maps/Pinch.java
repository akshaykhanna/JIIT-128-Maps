package com.akibb.jiit_128maps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Pinch extends Activity
{
    Bitmap f;
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//For fullscreen view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.cc);
		
		Bundle suitcase=getIntent().getExtras();
		  int s=suitcase.getInt("key_data");
	CustomImageVIew mImage = (CustomImageVIew)findViewById(R.id.customImageVIew1);
	   imageSetter(s);
		mImage.setBitmap(f);
	}
	private void imageSetter(int s) 
	{
		// TODO Auto-generated method stub
		switch(s)
		{
		case 101:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt1);
			break;
		case 102:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt2);
			break;
		case 103:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt3);
			break;
		case 104:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt4);
			break;
		case 105:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt5);
			break;
		case 106:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt6);
			break;
		case 107:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt7);
			break;
		case 108:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt8);
			break;
		case 109:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt9);
			break;
		case 201:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.tr1);
			break;
		case 202:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.tr1);
			break;
		case 203:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.tr1);
			break;
		case 204:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.tr1);
			break;
		case 205:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.tr5);
			break;
		case 301:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr1);
			break;
		case 302:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr2);
			break;
		case 303:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr3);
			break;
		case 304:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr4);
			break;
		case 305:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr5);
			break;
		case 306:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr6);
			break;
		case 307:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr7);
			break;
		case 308:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr8);
			break;
		case 309:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr9);
			break;
		case 310:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr10);
			break;
		case 311:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr11);
			break;
		case 312:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cr12);
			break;
		case 401:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.analog);
			break;	
		case 402:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.bedc);
			break;	
		case 403:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cic);
			break;	
		case 404:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cl1);
			break;	
		case 405:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cl2);
			break;
		case 406:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt7);
			break;
		case 407:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.comm);
			break;	
		case 408:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cse);
			break;	
		case 409:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.dsp);
			break;	
		case 410:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.eca);
			break;	
		case 411:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.eceproj);
			break;	
		case 412:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.emilab);
			break;	
		case 413:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.emt);
			break;	
		case 414:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.es);
			break;
		case 415:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.mpc);
			break;
		case 416:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.physicslab);
			break;
		case 501:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.anna);
			break;	
		case 502:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cafe);
			break;
		case 503:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.cselab);
			break;
		case 504:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.ece);
			break;
		case 505:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.mp);
			break;
		case 506:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.phd);
			break;
		case 507:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.registry);
			break;
		case 508:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.server);
			break;
		case 509:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.saras);
			break;
		case 510:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.audi);
			break;
		case 511:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.washg);
			break;
		case 512:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.washi);
			break;
		case 513:
			f=BitmapFactory.decodeResource(getResources(),R.drawable.lt5);
			break;
		}
		
	}
	
	/*Settings menu*/
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu)

	{
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater capactiveButtonMenu=getMenuInflater();
	    capactiveButtonMenu.inflate(R.menu.avant_grade_menu, menu);
	    return true;
	    
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		
		
		case R.id.help:
			Intent help=new Intent("com.akibb.jiit_128maps.HELP");
			startActivity(help);
			break;
		
		case R.id.credits:
			Intent aboutus=new Intent("com.akibb.jiit_128maps.ABOUTUS");
			startActivity(aboutus);
			break;
		
		case R.id.Exit:
			finish();
			break;
		}
		return false;
		
	}
}
