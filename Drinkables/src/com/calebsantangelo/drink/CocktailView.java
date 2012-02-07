package com.calebsantangelo.drink;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class CocktailView extends Activity {
	
	private TextView mNameText;
	private TextView mInstructionsText;
	private TextView mGlassText;
	private TextView mIngreds;
	private DBhandler mDBhandler;
	private String mDrink_id;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cocktail);
		
		mNameText = (TextView) findViewById(R.id.drinkname);
		mInstructionsText = (TextView) findViewById(R.id.instructions);
		mGlassText = (TextView) findViewById(R.id.glass_type);
		mIngreds = (TextView) findViewById(R.id.ingredients);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			int drink_id = extras.getInt("drink_id");
			mDrink_id = Integer.toString(drink_id);
		}
		
		mDBhandler = new fetchCocktail();
		mDBhandler.execute(this,"drink_name as name, instructions, glass, drink_ingredients","drinks"," drink_id = "+mDrink_id);
	}
	
	private class fetchCocktail extends DBhandler{
		
		//CocktailView callerActivity;
		
		@Override
		protected Void doInBackground(Object... params){
			//callerActivity = (CocktailView) params[0];
			mColumn = (String) params[1];
			mTable = (String) params[2];
			mWhere = (String) params[3];
			String q = formatQuery();
			postData(q);
			return null;
		}
		
		protected void onPostExecute(Void result){
			Cocktail drink = parseOutput(mOut);
			mNameText.setText(drink.getName());
			mInstructionsText.setText(drink.getInstructions());
			mGlassText.setText(drink.getGlass());
			mIngreds.setText(drink.getIngredients());
		}
		
		
		protected Cocktail parseOutput(String jsonString){
			Cocktail cocktailData;
			Gson gson = new Gson();
			cocktailData = gson.fromJson(jsonString, Cocktail.class);
			return cocktailData;
		}
	}
	
	private class Cocktail{
		
		String name;
		String instructions;
		String glass;
		String drink_ingredients;
		
		private Cocktail (String name, String instructions, String glass, String ingredients){
			this.name = name;
			this.instructions = instructions;
			this.glass = glass;
			drink_ingredients = ingredients;
		}
		
		private String getName(){
			return name;
		}
		
		private String getInstructions(){
			return instructions;
		}
		
		private String getGlass(){
			return glass;
		}
		
		private String getIngredients(){
			return formatIngredients(drink_ingredients);
		}
		
		private String formatIngredients(String ingredients){
			String temp = ingredients.replace("#", " ");
			return temp.replace(":", "\n");
		}
	}

}
