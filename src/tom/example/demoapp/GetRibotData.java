package tom.example.demoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class GetRibotData extends AsyncTask<URL, Integer, JSONArray> {

	
	public GridView faceButtons;
	public Context context;
	public RibotTeamMember[] team;
	
	public GetRibotData(GridView gridview, Context c, RibotTeamMember[] r)
	{
		faceButtons = gridview;
		context =c ;
		team = r;
	}
	
	
	@Override
	protected JSONArray doInBackground(URL... url) {
		// get data from ribot url
		
		URL ribotTeam = url[0];
		String ribotData;
		try {
			
						
			BufferedReader reader = new BufferedReader(new InputStreamReader(ribotTeam.openStream()));
			
			ribotData = reader.readLine();
			
			Log.e("DATA: ", ribotData);
			
			
			JSONArray data = new JSONArray(ribotData);

			return data;

		} catch (MalformedURLException e) {
			e.printStackTrace();
			Log.e("URL:", ribotTeam.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(JSONArray JSONData) {
	// set ui
		
		
		team = new RibotTeamMember[JSONData.length()];
		
		
		//for ever json object in the array
		for(int x =0; x < JSONData.length(); x ++)
		{
			
			try {
				JSONObject currentTeamMember =  JSONData.getJSONObject(x);
			
				team[x]  = new RibotTeamMember(currentTeamMember);
				
				Log.d("", team[x].toString());
			}
			catch (JSONException e1) {
				e1.printStackTrace();
				Log.e("JSON ERROR", "JSON array conteatind non-vaild JSONObject");
			}
			//by default make it the hex color whilst pulling their image :)
                                                                                                                            		}
		
		
		ImageAdapter adapterOfImages = new ImageAdapter(context, team);
		
	    faceButtons.setAdapter(adapterOfImages);

	    
	    
	    faceButtons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// set up an activity for the right persons web request to go through on click

	        	Intent viewPersonIntent = new Intent(context, PersonDetailsActivity.class);
	        	     		        	
	        	viewPersonIntent.putExtra("teamMember", team[position]);
	        		        	
	        	context.startActivity(viewPersonIntent);
	        	
	        }
	    });
	    
		
		//PAINC AND EAT SOME CAKE 
		
	}

	
}
