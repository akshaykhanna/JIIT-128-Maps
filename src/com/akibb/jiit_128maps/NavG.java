package com.akibb.jiit_128maps;


import java.util.Locale;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NavG extends Activity implements OnClickListener
{

	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts!= null)
		{
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
		
	}
	String lec[]={"LT1","LT2","LT3","LT4","LT5","LT6","LT7","LT8","LT9"};
	String tr[]={"TR1","TR2","TR3","TR4","TR5",};
	String cr[]={"CR1","CR2","CR3","CR4","CR5","CR6","CR7","CR8","CR9","CR10","CR11","CR12"};
	String labs[]={"Analog/Digital lab","BEDC lab","CIC lab","CL1","CL2","CL3","Comm lab","CSE projectlab",
			"DSP Lab","ECA Lab","ECE project lab","EMI Lab","EMT Lab","ES Lab","MPC Lab","Physics Lab"};
	String ot[]={"Annapurna","Cafe","CSE faculty","ECE Faculty","MP hall","PHD faculty","Registry",
			"Server Room","Saraswati Statue","Open audi","Washroom G","Washroom I","Washroom II","HSS Dept"};
	
	String ground[][]={{"Saraswati Statue","EMI Lab","CL1","CL2","Physics Lab","Way to playground","Annapurna","Annapurna","Cafe","Washroom G","Stairs to first floor"},
			{"Washroom G","MP hall","Stairs to I0","Faculty Entrance","Registry","Primary Entrance","LRC","Entrance via parking","Stairs to I floor","Open audi","New Cafe"}};
	String search,floor[][],r[]=new String[121],rr[],near="";
	TextView tv,tvNb;Button bMap,bEP,bES,bEVP,bVN;ListView lv;ArrayAdapter<String> adapter;
	int i,j,l,s,wp;
	TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navg);
		
		Bundle suitcase=getIntent().getExtras();
		  s=suitcase.getInt("key_data");
		  tts= new TextToSpeech(NavG.this,new TextToSpeech.OnInitListener() {
				
				@Override
				public void onInit(int status) {
					// TODO Auto-generated method stub
					if(status != TextToSpeech.ERROR)
					{
						tts.setLanguage(Locale.UK);
						tts.setSpeechRate((float) 0.9);
					}
					
					
				}
			});
		  intialise();
		  selectLocation(s);
		  tv.setText(search+" is on  Ground floor.");
		  floor=ground;
		  nearby();
		  route("Primary Entrance",floor);
	}
	private void nearby() {
		// TODO Auto-generated method stub
		  near="";
		  int dp=ddaSearch(search,floor);
		  int dr=dp/100,dc=dp%100;
		  //left
		  int dcc=dc-1;
		  while(dcc>=0)
		  {
			  if(floor[dr][dcc]!="" && floor[dr][dcc]!=floor[dr][dc] )
			  {
				  if(near!="")//for adding comma and space
						 near+=", ";
				  near+=floor[dr][dcc];
				  break;
			  }
			  dcc--;
			  
		  }
		  //right
		  dcc=dc+1;
		  while(dcc<floor[dr].length)
		  {
			  if(floor[dr][dcc]!="" && floor[dr][dcc]!=floor[dr][dc])
			  {
				  if(near!="")//for adding comma and space
						 near+=", ";
				  near+=floor[dr][dcc];
				  break;
			  }
			  dcc++;
			  }
		  tvNb.setText("Near: "+near+".");
	}
	private void intialise() {
		// TODO Auto-generated method stub
		tv=(TextView)findViewById(R.id.textView1);
		tvNb=(TextView)findViewById(R.id.tvGFloor);
		bMap=(Button)findViewById(R.id.bGTGMap);
		bEP=(Button)findViewById(R.id.bIE);
		bES=(Button)findViewById(R.id.bIIE);
		bEVP=(Button)findViewById(R.id.bParkE);
		bVN=(Button)findViewById(R.id.bVNG);
		lv=(ListView)findViewById(R.id.listView1);
		bMap.setOnClickListener(this);
		bEP.setOnClickListener(this);
		bES.setOnClickListener(this);
		bEVP.setOnClickListener(this);
		bVN.setOnClickListener(this);
}
	void selectLocation(int s) {
		// TODO Auto-generated method stub
		s=s-1;//array index starts from 0
		switch(s/100)
		{
		case 1:search=lec[s%100];
		break;
		case 2:search=tr[s%100];
		break;
		case 3:search=cr[s%100];
		break;
		case 4:search=labs[s%100];
		break;
		case 5:search=ot[s%100];
		break;
		}
		//tv.setText(" "+first[0].length+" "+first[1].length+" "+first[2].length);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bGTGMap:
			Bundle suitcase=getIntent().getExtras();
			  s=suitcase.getInt("key_data");
			Bundle suitcase2=new Bundle();
			suitcase2.putInt("key_data", s);
			
			Intent flight=new Intent(NavG.this,Pinch.class);
			flight.putExtras(suitcase2);
			startActivity(flight);
			break;
		case R.id.bIE:route("Primary Entrance",floor);
			break;
		case R.id.bIIE:route("Faculty Entrance",floor);
			break;
		case R.id.bParkE:route("Entrance via parking",floor);
			break;
		case R.id.bVNG:
			voiceNavigation();
		     break;
		}
	}
	 public int ddaSearch(String search,String floor[][])
	  {
		  for (int rowIndex = 0; rowIndex < floor.length; rowIndex++ ) {
		       
		       
		          for (int columnIndex = 0; columnIndex < floor[rowIndex].length; columnIndex++) {
		             if (search.equals(floor[rowIndex][columnIndex])) {
		                 return rowIndex*100+columnIndex;
		             }
		          }
		       }
		    return 999; // value not found in array

	  }
	private void route(String stairs, String[][] floor) 
	{
		// TODO Auto-generated method stub
		int dp=ddaSearch(search,floor);
		  int dr=dp/100,dc=dp%100;
		  int sp=ddaSearch(stairs,floor);
		  int sr=sp/100,sc=sp%100;
		  if(tts!= null)
			{
				tts.stop();
			}
		  l=0;
		 
		  
		  if(dr==sr)
		  {
			  if(sc<dc)
		      {
			 
					r[l]="From "+ stairs +" turn right and cross:";
					l++;
					columnPlus(sc,dc,sr);
					r[l]= "You will reach: "+ floor[sr][dc];
					l++;
					
				 }
		 else
			 {
					 r[l]="From "+ stairs +" turn left and cross:";
					 l++;
					 columnMinus(sc,dc,sr);
					r[l]= "You will reach: "+ floor[sr][dc];
					l++;  
			 }
		  }   
		  else
		  {
			  r[l]="From "+ stairs +" go straight and reach "+floor[0][sc] +".";
			  l++;
			  if(sc<dc)
		      {
			 
					r[l]="From here turn right and cross:";
					l++;
					columnPlus(sc,dc,0);
					r[l]= "You will reach: "+ floor[sr-1][dc];
					l++;
					
				 }
		 else
			 {
					 r[l]="From here turn left and cross:";
					 l++;
					 columnMinus(sc,dc,0);
					r[l]= "You will reach: "+ floor[sr-1][dc];
					l++;
			 }
		  }
		  displayRoute();
	}
	 private void columnPlus(int sc,int ec,int sr)
	  {
		for(i=sc+1;i<=ec-1;i++)//loop ends  at a place before destination 
			//and starts at place after source
		 {
			//tv.setText("Column plus challla...");
			 if(floor[sr][i]==""|| floor[sr][i]=="wA" || floor[sr][i]=="wB" || floor[sr][i]=="wC" || floor[sr][i]=="wD"  )
				continue;
			 if(floor[sr][i]==floor[sr][i-1])
				 continue;
			 r[l]=floor[sr][i];
			 l++;
			 }  
	  }
	  private void columnMinus(int sc,int ec,int sr)
	  {
		  for(i=sc-1;i>=ec+1;i--)
			 {
				
				 if(floor[sr][i]==""|| floor[sr][i]=="wA" || floor[sr][i]=="wB" || floor[sr][i]=="wC" || floor[sr][i]=="wD" )
						continue;
				 if(floor[sr][i]==floor[sr][i+1])
					 continue;
				 r[l]=floor[sr][i];
				 l++;
			}
	  }
	  private void displayRoute() {
			// TODO Auto-generated method stub
			rr=new String[l];
			for(i=0;i<l;i++)
				rr[i]=r[i];
			adapter=new ArrayAdapter<String>(NavG.this,android.R.layout.simple_list_item_1,rr);
			lv.setAdapter(adapter);
			//Arrays.fill(r, null);
			//setListAdapter(new ArrayAdapter<String>(Navigation.this,android.R.layout.simple_list_item_1,r));
		}
	  private void voiceNavigation()
	  {
	 	 String voice="";
	 	 for(i=0;i<rr.length;i++)
	 	 {
	 		 voice+=r[i]+ ", ";
	 	 }
	 	 tts.speak(voice, TextToSpeech.QUEUE_FLUSH, null);
	  }
	  

		/*Settings menu*/
		@Override
		public boolean onCreateOptionsMenu(android.view.Menu menu)

		{
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu);
			MenuInflater capactiveButtonMenu=getMenuInflater();
		    capactiveButtonMenu.inflate(R.menu.menu_nav, menu);
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
			
			case R.id.guide:
				Intent guide=new Intent("com.akibb.jiit_128maps.GUIDE");
				startActivity(guide);
				break;
			
			case R.id.Exit:
				finish();
				break;
			}
			return false;
			
		}
}