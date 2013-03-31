package tom.example.demoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class GetExtraInfoFromAPI extends AsyncTask<RibotTeamMember , Integer, JSONObject> {

	
	public TextView[] aboutText;
	public GetExtraInfoFromAPI(TextView[] t)
	{
	  aboutText = t;
	}
	

	@Override
	protected JSONObject doInBackground(RibotTeamMember... team) {
		
		RibotTeamMember teamMember = team[0];

		if(teamMember.extraInfo==null)
		{
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(teamMember.url.openStream()));
				String ribotData = reader.readLine();
				Log.d("DATA: ", ribotData);
				JSONObject data = new JSONObject(ribotData);
				teamMember.extraInfo = data;
				return data;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			return teamMember.extraInfo;
		}
		return null;
	}
	
	
	@Override 
	protected void onPostExecute(JSONObject extraInfo)
	{
		
		int view =0;

		
		try {
			String text = extraInfo.getString("role");
			aboutText[view].setText("Role:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","role missing from JSON");
		}

		
		
		try {
			String text = extraInfo.getString("description");
			aboutText[view].setText("About:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","description missing from JSON");
		} 
		
		try {
			String text = extraInfo.getString("email");
			aboutText[view].setText("Email:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","email missing from JSON");
		} 
		
		
		try {
			String text = extraInfo.getString("twitter");
			aboutText[view].setText("Twitter:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","twitter missing from JSON");
		} 
		
		
		try {
			String text = extraInfo.getString("favSweet");
			aboutText[view].setText("Favourite Sweet:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","favSweet missing from JSON");
		} 

		
		try {
			String text = extraInfo.getString("favSeason");
			aboutText[view].setText("Favourite Season:");
			view++;
			aboutText[view].setText(text);
			view++;
		} catch (JSONException e) {
		Log.e("JSON error","favSeason missing from JSON");
		} 
		
		
		
		
				
	} 
	
}
