package com.akibb.jiit_128maps;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class A extends Activity implements OnClickListener {
    Button le,tr,cr,ot,la;
    int s;
   
    
    
   
    
    public void onPause() { 
    	super.onPause();
    	
    
    }
    
    @Override
   	protected void onResume() {
   		// TODO Auto-generated method stub
   		super.onResume();
   		
   		
   	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	     
		setContentView(R.layout.a);
		
		
		intial();
		
		
		le.setOnClickListener(this);
		tr.setOnClickListener(this);
		cr.setOnClickListener(this);
		ot.setOnClickListener(this);
		la.setOnClickListener(this);
		
	}

	private void intial() {
		// TODO Auto-generated method stub
		le=(Button)findViewById(R.id.bLt);
		tr=(Button)findViewById(R.id.bTr);
		cr=(Button)findViewById(R.id.bCr);
		ot=(Button)findViewById(R.id.bOt);
		la=(Button)findViewById(R.id.bLabs);
		
		
	}


	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bLt:s=1;
			break;
		case R.id.bTr:s=2;
	
			break;
		case R.id.bCr:s=3;
			break;
		case R.id.bLabs:s=4;
		
			break;
		case R.id.bOt:s=5;
		
			break;
		}
		Bundle suitcase=new Bundle();
		suitcase.putInt("key_data", s);//.putString("key_data", s);
		Intent flight=new Intent(A.this,BB.class);
		flight.putExtras(suitcase);
		startActivity(flight);
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
