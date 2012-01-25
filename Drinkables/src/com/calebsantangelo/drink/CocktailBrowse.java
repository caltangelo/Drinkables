package com.calebsantangelo.drink;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class CocktailBrowse extends DBadapter {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  column = "drink_id as id, drink_name as name";
		  table = "drinks";

		  
		  initializeDialog();
		  DBhandler load = new DBhandler();
	      load.execute(this, column, table,null);
		}

	@Override
	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, CocktailView.class);
		i.putExtra("drink_id", idlist[position]);
		startActivity(i);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.standard_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.help:
			Toast.makeText(getApplicationContext(), R.string.help_text, Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
