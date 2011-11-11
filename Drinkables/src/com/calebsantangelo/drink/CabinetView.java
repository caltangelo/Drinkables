package com.calebsantangelo.drink;


import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.Bundle;


public class CabinetView extends DBadapter {
	ProgressDialog dialog;
	Integer[] idlist;
	ArrayList<Integer> cabinet;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  initializeDialog();
		  DBhandler load = new DBhandler();
	      load.execute(this,"ingredient_id as id, ingredient_name as name","ingredients",null);

	}
	
}
