package com.akibb.jiit_128maps;







import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BB extends ListActivity
{
	 
	ListView lv1;
	 int p,s,code;
	 TextView tv;
	 String search;
		String lec[]={"LT1","LT2","LT3","LT4","LT5","LT6","LT7","LT8","LT9"};
		String tr[]={"TR1","TR2","TR3","TR4","TR5",};
		String cr[]={"CR1","CR2","CR3","CR4","CR5","CR6","CR7","CR8","CR9","CR10","CR11","CR12"};
		String labs[]={"Analog/Digital lab","BEDC lab","CIC lab","CL1","CL2","CL3","Comm lab","CSE projectlab",
				"DSP Lab","ECA Lab","ECE project lab","EMI Lab","EMT Lab","ES Lab","MPC Lab","Physics Lab"};
		String ot[]={"Annapurna","Cafe","CSE faculty","ECE Faculty","MP hall","PHD faculty","Registry",
				"Server Room","Sarwasti statue","Open audi","Washroom G","Washroom I","Washroom II","HSS Dept"};

	 String first[][]={{"","CR2","TR1","","LT2","LT1","LT1","","Balcony passage","","Comm lab","DSP Lab","CR11","CR12","CIC lab","","","",""}
     ,{"","CR1","wA","GS Sir Cabin","TR2","TR3","wB","PHD faculty","Balcony passage","EMT Lab","wC","CSE projectlab","BS Sir Cabin","wD","ECE project lab","","","","Washroom I"}
     ,{"Washroom I","Stairs A","MP hall I","MP hall I","Stairs B","Server Room","Store","Store","Balcony passage","ECE Faculty","","Stairs C","","","","LT7","LT8","CL3","Stairs D"}};

	 
String second[][]={{"","MPC Lab","Analog/Digital lab","Analog/Digital lab","CR4","CR5","CR6","","Balcony passage","CR7","CR10","CR10","LT9","LT9","","","","Washroom II","Washroom II",""},
		{"","CR3","wA","Pusphendar Sir Cabin","TR4","TR5","wB","ECA Lab","Balcony passage","ES Lab","wC","CR8","CR9","wD","HSS Dept","","","","",""},
		{"Washroom II","Stairs A","","Stairs B","Lift","CSE faculty","CSE faculty","CSE faculty","Balcony passage","Faculty Senior","Stairs C","","","","","LT3","LT4","LT5","LT6","Stairs D"}};
	 
	    ArrayAdapter<String> adapter;
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);

			Bundle suitcase=getIntent().getExtras();
			 s=suitcase.getInt("key_data");
			  createSpinner(s);
			
				
				
		}
		 void createSpinner(int s) {
			// TODO Auto-generated method stub
			switch(s)
			{
			case 1:
			setListAdapter(new ArrayAdapter<String>(BB.this,android.R.layout.simple_list_item_1,lec));
			break;
			case 2:
			setListAdapter(new ArrayAdapter<String>(BB.this,android.R.layout.simple_list_item_1,tr));
			break;
			case 3:
			setListAdapter(new ArrayAdapter<String>(BB.this,android.R.layout.simple_list_item_1,cr));
			break;
			case 4:
			setListAdapter(new ArrayAdapter<String>(BB.this,android.R.layout.simple_list_item_1,labs));
			break;
			case 5:
			String ot[]={"Annapurna","Cafe","CSE faculty","ECE Faculty","MP hall","PHD faculty","Registry",
					"Server Room","Sarwasti statue","Open audi","Washroom G","Washroom I"};
			setListAdapter(new ArrayAdapter<String>(BB.this,android.R.layout.simple_list_item_1,ot));
			break;
			}
		}
		@Override
		protected void onListItemClick(ListView l, View v, int p, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, p, id);
			 code=(100*s)+(p+1);
			Bundle suitcase=new Bundle();
			suitcase.putInt("key_data", code);
			selectLocation(code);
			
			Intent flight;
			if(ddaSearch(search,first)!=999 || ddaSearch(search,second)!=999)
			flight=new Intent(BB.this,Navigation.class);
			else
			flight=new Intent(BB.this,NavG.class);
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
		 void selectLocation(int s) {
				// TODO Auto-generated method stub
				s=s-1;
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
				if(search=="BEDC Lab" || s==401)
					search="ECA Lab";
				//tv.setText(" "+first[0].length+" "+first[1].length+" "+first[2].length);
			}
		
}
