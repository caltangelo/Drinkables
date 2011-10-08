package com.example.helloandroid;



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
import android.widget.TextView;

//AsyncTask<Params, Progress, Result>
public class DBhandler extends AsyncTask<Object, Void, Void> {
	
	String out;
	HelloAndroid callerActivity;
	TextView textv;
	
	protected void onPreExecute(){
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Object... params) {
		// TODO Auto-generated method stub
		callerActivity = (HelloAndroid) params[0];
		out = (String) params[1];
		textv = (TextView) params[2];
		postData();
		return null;
	}
	
	
	protected void onPostExecute(Void result) {
		callerActivity.dialog.dismiss();
		textv.setText(out);
		callerActivity.setContentView(textv);
		super.onPostExecute(result);
	}
	
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.calebsantangelo.com/query.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("col[]", "drink_name"));
	        nameValuePairs.add(new BasicNameValuePair("table", "drinks"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        out = inputStreamToString(response.getEntity().getContent()).toString();
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    	out = "I've made a huge mistake";
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    	out = "I've made an equally large mistake";
	    }
	} 
	
	// Fast Implementation
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
