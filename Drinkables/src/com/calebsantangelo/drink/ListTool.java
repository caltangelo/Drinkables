package com.calebsantangelo.drink;

import java.util.ArrayList;

public class ListTool {
	
	ArrayList<String> displayList;
	ArrayList<Integer> idList;
	String[] display;
	Integer[] IDs;
	
	public ListTool(String[] fromDB){
		displayList = new ArrayList<String>();
		idList = new ArrayList<Integer>();
		for (String s : fromDB){
			String name = s.split("##")[0];
			String id = s.split("##")[1];
			displayList.add(name);
			idList.add(Integer.parseInt(id));
		}
	}
	
	void convertToArray(){
		display = new String[1];
		IDs = new Integer[1];
		display = displayList.toArray(display);
		IDs = idList.toArray(IDs);	
	}

}