package com.example.helloandroid;

import java.util.ArrayList;

public class ListTool {
	
	static String[] display;
	static Integer[] IDs;
	
	public ListTool(String[] fromDB){
		ArrayList<String> displayList = new ArrayList<String>();
		ArrayList<Integer>idList = new ArrayList<Integer>();
		for (String s : fromDB){
			String id = s.split("##")[0];
			String name = s.split("##")[1];
			displayList.add(name);
			idList.add(Integer.parseInt(id));
		}
		convertToArray(displayList, idList);
	}
	
	private void convertToArray(ArrayList<String> names, ArrayList<Integer> ids){
		display = new String[10];
		IDs = new Integer[10];
		display = names.toArray(display);
		IDs = ids.toArray(IDs);	
	}

}
