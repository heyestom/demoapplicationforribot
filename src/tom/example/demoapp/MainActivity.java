package tom.example.demoapp;

import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.widget.GridView;
import android.app.Activity;


public class MainActivity extends Activity {

	
	
	public RibotTeamMember[] ribotTeam;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		URL team;
		try {
			team = new URL("http://theribots.nodejitsu.com/api/team");

			GridView gridview = (GridView) findViewById(R.id.gridview);    
		    
			new GetRibotData(gridview,this,ribotTeam).execute(team);

			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
}
