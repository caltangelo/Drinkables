package com.calebsantangelo.drink;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CocktailBrowse extends DBadapter {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  initializeDialog();
		  DBhandler load = new DBhandler();
	      load.execute(this,"drink_id as id, drink_name as name","drinks",null);
		}

	@Override
	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, CocktailView.class);
		i.putExtra("drink_id", idlist[position]);
		startActivity(i);		
	}
	
}
