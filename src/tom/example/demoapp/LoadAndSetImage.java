package tom.example.demoapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import android.widget.ImageView;
import android.widget.TextView;

public class LoadAndSetImage extends AsyncTask<RibotTeamMember, Integer, Drawable>{

	
	public ImageView icon;
	public TextView name;
	public RibotTeamMember teamMember;
	public Context context;
	
	public LoadAndSetImage(ImageView i, TextView t, Context c) {
		
		icon = i;
		name = t;
		context = c;
		
	}
	@Override
	protected Drawable doInBackground(RibotTeamMember... team) {
		
		teamMember = team[0];
		
		Drawable personImage =null;

		
		// if the team member doesn't have a drawable object already
		// try and load one from local storage 
		// if that fails try and pull one from API call
		// finally assign unknown image 
		
		if(teamMember.drawable==null)
		{
			//load from file
			try{		
				String filePath =  context.getFilesDir().getPath().toString() +  teamMember.id+".png";
				FileInputStream inStream = new FileInputStream(filePath);
				Bitmap bitmapFromFile = BitmapFactory.decodeStream(inStream);
				personImage = new BitmapDrawable(bitmapFromFile);
				return personImage;
				
			}catch(IOException e) {
				Log.e("File not found", "No local copy of image :S");
					
					
				// pull from server	
				try {
					InputStream in = (InputStream) teamMember.image.getContent();
					personImage = Drawable.createFromStream(in,null);
					
					if(personImage!=null) // if a file was successfully pulled attempt to store it
					{
						try{ // attempt to store the image 
							
							Bitmap bitMapForFile =  (Bitmap)((BitmapDrawable) personImage).getBitmap();
							//write to file for future use
							String filePath =  context.getFilesDir().getPath().toString() +  teamMember.id+".png";
							
							FileOutputStream out = new FileOutputStream(filePath);
							bitMapForFile.compress(Bitmap.CompressFormat.PNG, 100, out);
							
						} catch (IOException en) {
							en.printStackTrace();
							Log.e("Writing file failed", "Probably not set permissions right :S");
						}
					}
					
					
					return personImage;
					
				} catch (IOException en) {
					Log.e("File not found", "No ribotar image! :S");
				}
				
			}	
		}
		else
		{
			//return already set drawable
			return teamMember.drawable;
		}
		return personImage;
	}
	
	@Override 
	protected void onPostExecute(Drawable picture) 
	{
		
		//set the image view as the ribotar
		//set the team member objects drawable as the ribotar
		
		if(picture==null)
		{
			icon.setImageResource(R.drawable.unknown);
			teamMember.drawable = icon.getDrawable();
		}
		else{
			teamMember.drawable = picture;
			icon.setImageDrawable(picture);
		}
		name.setText(teamMember.firstName + " " + teamMember.lastName );
		Log.d("DATA: ", "Image loaded and set");

		
	}

}
