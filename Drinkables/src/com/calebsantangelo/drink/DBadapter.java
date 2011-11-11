package com.calebsantangelo.drink;

import android.app.ListActivity;
import android.app.ProgressDialog;

public class DBadapter extends ListActivity {
	
	ProgressDialog dialog;
	Integer[] idlist;
	protected String column;
	protected String table;
	protected String where;
	
	
	protected void initializeDialog() {
		dialog = ProgressDialog.show(this, "", "Loading data, please wait...", true);
		dialog.show();
	}
	
	protected void dismissDialog(){
		dialog.dismiss();
	}
	
	protected void setIndices(Integer[] idlist){
		this.idlist = idlist;
	}

}
