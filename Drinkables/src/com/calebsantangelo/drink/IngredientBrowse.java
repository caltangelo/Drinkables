package com.calebsantangelo.drink;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class IngredientBrowse extends DBadapter {

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initializeDialog();
		  DBhandler load = new DBhandler();
	      load.execute(this,"ingredient_id as id, ingredient_name as name","ingredients","ingredient_id in (SELECT DISTINCT base_id from drinks)");
    }

	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, resultList.class);
		i.putExtra("drink_id", idlist[position]);
		i.putExtra("columns", "drink_id as id, drink_name as name");
		i.putExtra("table", "drinks");
		startActivity(i);		
	}
}