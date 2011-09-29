package com.example.tabwidget;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArtistsActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.ingredients);

        String[] ingredients = getResources().getStringArray(R.array.Ingredients_array);
  	    setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, ingredients));

  	  ListView lv = getListView();
  	  lv.setTextFilterEnabled(true);

  	  lv.setOnItemClickListener(new OnItemClickListener() {
  	    @Override
		public void onItemClick(AdapterView<?> parent, View view,
  	        int position, long id) {
  	      // When clicked, show a toast with the TextView text
  	      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
  	          Toast.LENGTH_SHORT).show();
  	    }
  	  });
  	}
}