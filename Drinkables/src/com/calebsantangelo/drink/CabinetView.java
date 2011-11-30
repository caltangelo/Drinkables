package com.calebsantangelo.drink;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class CabinetView extends DBadapter {
	
	ArrayList<Integer> cabinet;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  column = "ingredient_id as id, ingredient_name as name";
		  table = "ingredients";
		  
		  cabinet = new ArrayList<Integer>();
		  initializeDialog();
		  CabinetHandler load = new CabinetHandler();
	      load.execute(this, column, table,null);

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		
		Integer item_id = idlist[position];
		
		if(cabinet.contains(item_id)){
			cabinet.remove(item_id);
		} else{
			cabinet.add(item_id);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cabinet_menu, menu);
		return true;
	}
	
	protected String formatCabinet(){
		ArrayList<String> strCab = new ArrayList<String>();
		for(Integer I: cabinet){
			strCab.add("("+Integer.toString(I)+")");
		}
		return trimBrackets(strCab.toString());
	}
	
	protected String trimBrackets(String strToTrim){
		int len = strToTrim.length();
		return strToTrim.substring(1, len-1);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.search:
			Intent i = new Intent(this, resultList.class);
			i.putExtra("cabinet",formatCabinet());
			startActivity(i);
			return true;
		case R.id.help:
			Toast.makeText(getApplicationContext(), "sux 2bU, n00b", Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class CabinetHandler extends DBhandler{
		
		@Override
		protected void onPostExecute(Void result) {
			mLists = new ListTool(getArray(mOut));
			mLists.convertToArray();
			ListView lv = callerActivity.getListView();
			callerActivity.setListAdapter(new ArrayAdapter<String>(callerActivity, R.layout.cabinet_item_alternate, mLists.display));
			callerActivity.setIndices(mLists.IDs);
			lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			lv.setTextFilterEnabled(true);
			callerActivity.dismissDialog();
		}
		
	}
	
}
