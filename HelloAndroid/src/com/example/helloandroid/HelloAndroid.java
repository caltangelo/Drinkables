package com.example.helloandroid;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HelloAndroid extends ListActivity {
	
	ProgressDialog dialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDialog();

        DBhandler load = new DBhandler();
        load.execute(this,"drink_id as id, drink_name as name", "drinks");
    }

	private void initializeDialog() {
		dialog = ProgressDialog.show(this, "", "Loading data, please wait...", true);
		dialog.show();
	}

	protected void onListItemClick (ListView l , View v, int position, long id){
		Intent i = new Intent(this, CocktailView.class);
		i.putExtra("drink_id", ListTool.IDs[position]);
		startActivity(i);		
	}
}
