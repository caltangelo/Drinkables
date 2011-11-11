package com.calebsantangelo.drink;


import com.example.tabwidget.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class resultList extends DBadapter {


	String search_id;
	DBhandler listGetter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  Bundle extras = getIntent().getExtras();
			if (extras != null){
				int drink_id = extras.getInt("drink_id");
				search_id = Integer.toString(drink_id);
				table = extras.getString("table");
				column = extras.getString("columns");
			}
		  
		  initializeDialog();
		  listGetter = new fetchResults();
	      listGetter.execute(this, column , table," base_id = "+search_id);
		}
	
	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, CocktailView.class);
		i.putExtra("drink_id", idlist[position]);
		startActivity(i);		
	}
	
	private class fetchResults extends DBhandler{
		
		resultList callerActivity;
		
		@Override
		protected Void doInBackground(Object... params){
			callerActivity = (resultList) params[0];
			mColumn = (String) params[1];
			mTable = (String) params[2];
			mWhere = (String) params[3];
			String q = formatQuery();
			postData(q);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			mLists = new ListTool(getArray(mOut));
			mLists.convertToArray();
			ListView lv = getListView();
			setListAdapter(new ArrayAdapter<String>(callerActivity, R.layout.list_item, mLists.display));
			setIndices(mLists.IDs);
			dismissDialog();
			lv.setTextFilterEnabled(true);
		}
		
		
	}
	
}
