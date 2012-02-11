package com.calebsantangelo.drink;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class IngredientBrowse extends DBadapter {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        column ="ingredient_id as id, ingredient_name as name";
        table = "ingredients";
        where = "ingredient_id in (SELECT DISTINCT base_id from drinks)";
        
        initializeDialog();
		  DBhandler load = new DBhandler();
	      load.execute(this, column, table, where);
    }

	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, resultList.class);
		i.putExtra("drink_id", idlist[position]);
		i.putExtra("columns", "drink_id as id, drink_name as name");
		i.putExtra("table", "drinks");
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