package tom.example.demoapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class PersonDetailsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_details);
		
		//unpack RibotTeamMember
		Bundle data = getIntent().getExtras();
		RibotTeamMember teamMember = data.getParcelable("teamMember");
		
		// make api call for more info! :D
		
		TextView[] textViews = new TextView[12];
		
		// this should probably be done dynamically 
		// but this works too 12 is the maximum 
		
		textViews[0] = (TextView) findViewById(R.id.extraInfo0);
		textViews[1] = (TextView) findViewById(R.id.extraInfo1);
		textViews[2] = (TextView) findViewById(R.id.extraInfo2);
		textViews[3] = (TextView) findViewById(R.id.extraInfo3);
		textViews[4] = (TextView) findViewById(R.id.extraInfo4);
		textViews[5] = (TextView) findViewById(R.id.extraInfo5);
		textViews[6] = (TextView) findViewById(R.id.extraInfo6);
		textViews[7] = (TextView) findViewById(R.id.extraInfo7);
		textViews[8] = (TextView) findViewById(R.id.extraInfo8);
		textViews[9] = (TextView) findViewById(R.id.extraInfo9);
		textViews[10] = (TextView) findViewById(R.id.extraInfo10);
		textViews[11] = (TextView) findViewById(R.id.extraInfo11);
		
		
		
		new GetExtraInfoFromAPI(textViews).execute(teamMember);
		

		ImageView personImage = (ImageView) findViewById(R.id.image);
		personImage.setImageDrawable(teamMember.drawable);
		
		
		Log.d(""+personImage.getWidth(), " x"+personImage.getHeight());
		
		RelativeLayout header = (RelativeLayout)  findViewById(R.id.header);
		
		Log.d("COLOUR",teamMember.hex);
		
		header.setBackgroundColor(Color.parseColor(teamMember.hex));
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(teamMember.firstName + " " + teamMember.lastName);
		
		Log.d("nickname",teamMember.nickName);
		TextView nickName = (TextView) findViewById(R.id.nickname);
		nickName.setText(teamMember.nickName);
	
	}
	
}
