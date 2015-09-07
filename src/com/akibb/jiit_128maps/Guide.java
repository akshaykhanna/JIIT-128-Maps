package com.akibb.jiit_128maps;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Guide extends Activity implements OnClickListener {

	
	Button bB,bN,bS; TextView tvH,tvP;
	String para[]={"The navigation is written down keeping in mind that the user will first reach to destination floor using any stair and entrance.\n Then from that particular stair or entrance step  by step navgation is given to his destination.\n The navigation is assisted by voice which can intiated by pressing the speak button.",
			"Navigation Buttons"
			+"Stairs A: Stairs near Saraswati statue or on extreme left from entrance."
			+"\nStairs B: To the left of Stairs A and near lift for second floor."
			+"\nStairs C: To right of parking entrance for first floor and the right ECE faculty for second floor."
			+"\nStairs D: Extreme right near washroom and new cafe ."
			+"\nPrimary Entrance: Main entrance for students opposite to playground entrance, near LRC and Annapurna."
			+"\nFaculty Entrance: Secondary entrance restricted to faculty only, near lift and stairs to first floor."
			+"\nEntrance Via Parking: Entrance from where one can enter college via parking. Its near stairs to first floor and LRC Entrance",
			"\nECA lab and BEDC lab are both same.\n Washroom on any floor are on the extreme corners.\n Abbrevation:- LT(Lecture), TR(Tutorial), CR(Classroom), LRC(Learning Resource Center), Cl(Computer Lab) and MP hall(Multi Purpose hall) "};
	
	String title[]={"Navigation","Navigation Buttons","Consideration"};
	int c=0;
	SharedPreferences saveData;
	public static String fileName="Guide";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_guide);
		saveData=getSharedPreferences(fileName,0);
		SharedPreferences.Editor e=saveData.edit();
		e.putInt("gsos", 1);
		e.commit();
		intial();
		setThing();
		bB.setOnClickListener(this);
		bN.setOnClickListener(this);
		//bS.setOnClickListener(this);
	}
	private void setThing() {
		// TODO Auto-generated method stub
		tvH.setText(title[c]);
		tvP.setText(para[c]);
	}
	private void intial() {
		// TODO Auto-generated method stub
		bB=(Button)findViewById(R.id.bGB);
		bN=(Button)findViewById(R.id.bGN);
		//bS=(Button)findViewById(R.id.bGSkip);
		tvH=(TextView)findViewById(R.id.tvGuideHead);
		tvP=(TextView)findViewById(R.id.tvGuidePara);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bGB:
			c--;
			if(c<0)
			  c=2;
			setThing();
			break;
		case R.id.bGN:
			c++;
			if(c>2)
				c=0;
			setThing();
			break;
		/*case R.id.bGSkip:
			saveData=getSharedPreferences(fileName,0);
			SharedPreferences.Editor e=saveData.edit();
			e.putInt("gsos", 1);
			e.commit();*/
		}
	}

	
}
