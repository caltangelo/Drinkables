package com.calebsantangelo.drink;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.calebsantangelo.drink.R;
import com.google.gson.Gson;

//AsyncTask<Params, Progress, Result>
public class DBhandler extends AsyncTask<Object, Void, Void> {
	
	String mOut;
	String mColumn;
	String mTable;
	String mWhere;
	DBadapter callerActivity;
	ListTool mLists;
	String mCabinet;
	//This must be set to the URL of the query.php script on the web server
	String URL = "http://www.calebsantangelo.com/query.php";
	
	protected void onPreExecute(){
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Object... params) {
		// TODO Auto-generated method stub		
		callerActivity = (DBadapter) params[0];
		mColumn = (String) params[1];
		mTable = (String) params[2];
		mWhere = (String) params[3];
		String q = formatQuery();
		postData(q);
		return null;
	}
	
	
	protected void onPostExecute(Void result) {
		mLists = new ListTool(getArray(mOut));
		mLists.convertToArray();
		ListView lv = callerActivity.getListView();
		callerActivity.setListAdapter(new ArrayAdapter<String>(callerActivity, R.layout.list_item, mLists.display));
		callerActivity.setIndices(mLists.IDs);
		callerActivity.dismissDialog();
		lv.setTextFilterEnabled(true);
		super.onPostExecute(result);
	}
	
	public void postData(String query) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(URL);

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("Query", query));
	        nameValuePairs.add(new BasicNameValuePair("cab", mCabinet));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        mOut = inputStreamToString(response.getEntity().getContent()).toString();
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    	mOut = e.getMessage();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    	mOut = e.getMessage();
	    }
	} 
	
	protected String[] getArray(String jsonString){
		String[] result;
		Gson gson = new Gson();
		result = gson.fromJson(jsonString, String[].class);
		return result;
	}
	
	protected String formatQuery(){
		Query query = new Query(mColumn, mTable);
		if (mWhere != null){
			query.addClause(mWhere);
		}
		Gson gson = new Gson();
		String jsonQuery = gson.toJson(query);
		return jsonQuery;
	}
	
	private StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total;
	}
}
