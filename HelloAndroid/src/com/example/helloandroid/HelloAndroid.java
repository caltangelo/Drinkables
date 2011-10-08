package com.example.helloandroid;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

public class HelloAndroid extends Activity {
	
	ProgressDialog dialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDialog();
        TextView tv = new TextView(this);

       DBhandler load = new DBhandler();
       load.execute(this,"yikes!",tv);
       
    }

	private void initializeDialog() {
		dialog = ProgressDialog.show(HelloAndroid.this, "", "Loading data, please wait...", true);
		dialog.show();
	}
}
