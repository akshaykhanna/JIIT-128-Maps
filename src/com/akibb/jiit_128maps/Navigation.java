package com.akibb.jiit_128maps;


import java.util.Arrays;
import java.util.Locale;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

public class Navigation extends Activity implements OnClickListener 
{
	
	SharedPreferences saveData;
	public static String fileName="Guide";
	
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
	String labs[]={"Analog/Digital lab","BEDC Lab","CIC lab","CL1","CL2","CL3","Comm lab","CSE projectlab",
			"DSP Lab","ECA Lab","ECE project lab","EMI Lab","EMT Lab","ES Lab","MPC Lab","Physics Lab"};
	String ot[]={"Annapurna","Cafe","CSE faculty","ECE Faculty","MP hall","PHD faculty","Registry",
			"Server Room","Sarwasti statue","Open audi","Washroom G","Washroom I","Washroom II","HSS Dept"};
	
	String first[][]={{"","CR2","TR1","","LT2","LT1","LT1","","Balcony passage","","Comm lab","DSP Lab","CR11","CR12","CIC lab","","","",""}
	        ,{"","CR1","wA","GS Sir Cabin","TR2","TR3","wB","PHD faculty","Balcony passage","EMT Lab","wC","CSE projectlab","BS Sir Cabin","wD","ECE project lab","","","","Washroom I"}
	        ,{"Washroom I","Stairs A","MP hall","MP hall","Stairs B","Server Room","Store","Store","Balcony passage","ECE Faculty","","Stairs C","","","","LT7","LT8","CL3","Stairs D"}};
	
	String second[][]={{"","MPC Lab","Analog/Digital lab","Analog/Digital lab","CR4","CR5","CR6","","Balcony passage","CR7","CR10","CR10","LT9","LT9","","","","Washroom II","Washroom II",""},
			{"","CR3","wA","Pusphendar Sir Cabin","TR4","TR5","wB","ECA Lab","Balcony passage","ES Lab","wC","CR8","CR9","wD","HSS Dept","","","","",""},
			{"Washroom II","Stairs A","","Stairs B","Lift","CSE faculty","CSE faculty","CSE faculty","Balcony passage","Faculty Senior","Stairs C","","","","","LT3","LT4","LT5","LT6","Stairs D"}};
	String search,near="",r[]=new String[144];
	String rr[],floor[][],ws;
	TextView tv,tvNb;Button bMap,bSA,bSB,bSC,bSD,bVN;ListView lv;ArrayAdapter<String> adapter;
	int i,j,l,s,wp;
	TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle suitcase=getIntent().getExtras();
		  s=suitcase.getInt("key_data");
		  setContentView(R.layout.navfs);
		  
		  
		  saveData=getSharedPreferences(fileName,0);
			int gCheck=saveData.getInt("gsos", 0);
			if(gCheck!=1)
				{
				Intent guide=new Intent("com.akibb.jiit_128maps.GUIDE");
				startActivity(guide);
				}
		  
		  tts= new TextToSpeech(Navigation.this,new TextToSpeech.OnInitListener() {
				
				@Override
				public void onInit(int status) {
					// TODO Auto-generated method stub
					if(status != TextToSpeech.ERROR)
					{
						tts.setLanguage(Locale.UK);
						tts.setSpeechRate((float) 0.85);
					}
					
					
				}
			});
		  
		  intialise();
		  selectLocation(s);
		  if(ddaSearch(search,first)!=999)
			  {
			  floor=first;
			  tv.setText(search+" is on First floor. ");
			  }
		    else
		  {
		    	floor=second;//change here which floor to select
		    	tv.setText(search+" is on Second floor. ");
		  }
		  route("Stairs A",floor);
		  nearby();
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
			  if(floor[dr][dcc]!="" && floor[dr][dcc]!=floor[dr][dc] && floor[dr][dcc]!="wA" && floor[dr][dcc]!="wB" && floor[dr][dcc]!="wC" && floor[dr][dcc]!="wD" )
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
			  if(floor[dr][dcc]!="" && floor[dr][dcc]!=floor[dr][dc] && floor[dr][dcc]!="wA" && floor[dr][dcc]!="wB" && floor[dr][dcc]!="wC" && floor[dr][dcc]!="wD" )
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
		tv=(TextView)findViewById(R.id.tvCheck);
		tvNb=(TextView)findViewById(R.id.tvNB);
		bMap=(Button)findViewById(R.id.bGoToMap);
		bSA=(Button)findViewById(R.id.bStairsA);
		bSB=(Button)findViewById(R.id.bStairsB);
		bSC=(Button)findViewById(R.id.bStairsC);
		bSD=(Button)findViewById(R.id.bStairsD);
		bVN=(Button)findViewById(R.id.bVoiceNav);
		lv=(ListView)findViewById(R.id.lvRoute);
		bMap.setOnClickListener(this);
		bSA.setOnClickListener(this);
		bSB.setOnClickListener(this);
		bSC.setOnClickListener(this);
		bSD.setOnClickListener(this);
		bVN.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bStairsA:route("Stairs A",floor);
		Toast.makeText(getApplicationContext(), "Stairs to extreme left near Saraswati statue", Toast.LENGTH_SHORT).show();
		break;
		case R.id.bStairsB:route("Stairs B",floor);
		Toast.makeText(getApplicationContext(), "Stairs near lift", Toast.LENGTH_SHORT).show();
		break;
		case R.id.bStairsC:route("Stairs C",floor);
		Toast.makeText(getApplicationContext(), "Stairs near senior faculty block", Toast.LENGTH_SHORT).show();
		break;
		case R.id.bStairsD:route("Stairs D",floor);
		Toast.makeText(getApplicationContext(), "Stairs to extreme right  near Open Audi", Toast.LENGTH_SHORT).show();
		break;
		case R.id.bGoToMap:
			Bundle suitcase=getIntent().getExtras();
			  s=suitcase.getInt("key_data");
			Bundle suitcase2=new Bundle();
			suitcase2.putInt("key_data", s);
			Intent flight=new Intent(Navigation.this,Pinch.class);
			flight.putExtras(suitcase2);
			startActivity(flight);
			break;
		case R.id.bVoiceNav:
			voiceNavigation();
		  break;
		}
	}
	 void selectLocation(int ss) {
		// TODO Auto-generated method stub
		ss=ss-1;
		switch(s/100)
		{
		case 1:search=lec[ss%100];
		break;
		case 2:search=tr[ss%100];
		break;
		case 3:search=cr[ss%100];
		break;
		case 4:search=labs[ss%100];
		break;
		case 5:search=ot[ss%100];
		break;
		}
		if(search=="BEDC Lab" || ss==401)
			search="ECA Lab";
		//tv.setText(" "+first[0].length+" "+first[1].length+" "+first[2].length);
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
  public void route(String stairs,String floor[][])
  {
	  String st=stairs;
	  if(tts!= null)
		{
			tts.stop();
		}
	  int dp=ddaSearch(search,floor);
	  int dr=dp/100,dc=dp%100;
	  int sp=ddaSearch(stairs,floor);
	  int sr=sp/100,sc=sp%100;
	   
	  l=0;
	  
	  if(sr==dr)//destination is  in first same as start point row
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
		  
		  if(dc>8)
		  { if(stairs=="Stairs A" || stairs=="Stairs B")
				  ws="wC";
		    else 
			  ws="w"+st.substring(st.length()-1);
			  }
		     
			  else 
			  { if(stairs=="Stairs C" || stairs=="Stairs D")
				  ws="wB";
			  else
				  ws="w"+st.substring(st.length()-1);
			  }
			  
			  //String ws="w"+st.substring(st.length()-1);
			  
			  wp=ddaSearch(ws,floor);
		  
		  int wr=wp/100,wc=wp%100;
		  if(sc<=wc)
		  {
			  r[l]="From "+ stairs +" turn right  and cross:";
			  l++;
			 // tv.setText(" "+sc+" "+wc);
			  columnPlus(sc,(wc+1),sr);//reach way point
			  r[l]="Now Turn left and go straight "; 
				 l++;
				 if(dr==1)
				 {
					if(wc<dc)
						{
						r[l]="From here turn right and cross:"; l++;
						columnPlus(wc,dc,wr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
						}
					else 
					{
						r[l]="From here turn left and cross:"; l++;
						columnMinus(wc,dc,wr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
					}
						
				 }
				 else
				 {
					 r[l]="Go further straigth by crosing \""+floor[wr][wc-1]+"-"+floor[wr][wc+1]+"\""; l++;
					 if(wc<dc)
						{
						r[l]="From here turn right and cross:"; l++;
						columnPlus(wc-1,dc,dr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
						}
					else 
					{
						r[l]="From here turn left and cross:"; l++;
						columnMinus(wc+1,dc,dr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
					}
				 }
		  }
		  else
		  {
			  r[l]="From "+ stairs +" turn left and cross:";
				 l++;
				 columnMinus(sc,(wc-1),sr);
				 r[l]="Now Turn right go straight "; 
				 l++;
				 if(dr==1)
				 {
					 if(wc<dc)
						{
						r[l]="From here turn right and cross:"; l++;
						columnPlus(wc,dc,wr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
						}
					 else 
						{
							r[l]="From here turn left and cross:"; l++;
							columnPlus(wc,dc,wr);
							r[l]= "You will reach: "+ floor[dr][dc]; l++;
						}
				 }
				 else
				 {
					 r[l]="Go further straigth by crosing \""+floor[wr][wc-1]+"-"+floor[wr][wc+1]+"\""; l++;
					 if(wc<dc)
						{
						r[l]="From here turn right and cross:"; l++;
						columnPlus(wc-1,dc,dr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
						}
					else 
					{
						r[l]="From here turn left and cross:"; l++;
						columnMinus(wc+1,dc,dr);
						r[l]= "You will reach: "+ floor[dr][dc]; l++;
					}
				 }
		  }	 
		  //tv.setText(""+wp);
	  }

	  //tv.setText(" Length of route "+l);
	  displayRoute();
  }//end of func route
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
	adapter=new ArrayAdapter<String>(Navigation.this,android.R.layout.simple_list_item_1,rr);
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
