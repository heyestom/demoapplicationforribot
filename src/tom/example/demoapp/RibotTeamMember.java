package tom.example.demoapp;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class RibotTeamMember implements Parcelable {
	

	public String firstName;
	public String lastName;
	public String nickName;
	public String hex;
	public String id;
	public String role;
	public URL url;
	public URL image;
	public JSONObject extraInfo;
	
	public Drawable drawable  = null;
	
	RibotTeamMember(JSONObject j)
	{
		
		
		
		// some of these are optional and will be null 
		// this needs to be handled better cause currently its only being done by bodging the order. :3
		try {
		firstName = j.getString("firstName");
	    lastName = j.getString("lastName");
		id = j.getString("id");
		url = new URL(j.getString("url"));
		image = new URL("http://theribots.nodejitsu.com/api/team/" + id + "/ribotar");

		
		}//try 
		catch (JSONException e) {
			e.printStackTrace();
			Log.e("JSON ERROR", "JSON array conteatind non-vaild JSONObject");
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
			Log.e("JSON ERROR", "JSON array involved non-vaild JSONObject");
		}// catch
		
		// optional value
		try {
			hex = j.getString("hexColor");
		} catch (JSONException e1) {
			Log.e("JSON Error:", "Optional value hexColor missing");
			hex = "#c0c0c0";
		}
		
		// optional value
		try {
			nickName = j.getString("nickname");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Error:", "Optional value nuckname missing");
			nickName = "";
		}
		
		// optional value
		try {
			role = j.getString(role);
		} catch (JSONException e) {
			Log.e("JSON Error:", "Optional value role missing");
			role = "";
		}
				
	}//constructor 
	
	@Override
	public String toString() {

		return new String(firstName + " " + lastName + " " + image.toString());
		
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(hex);
		dest.writeString(id);
		dest.writeString(nickName);
		dest.writeString(role);
		dest.writeString(url.toString());
		dest.writeString(image.toString());


		//thanks stack overflow 
		Bitmap bitmap = (Bitmap)((BitmapDrawable) drawable).getBitmap();
		// Serialize bitmap as Parcelable:
		dest.writeParcelable(bitmap, flags);	

		if(extraInfo!=null)
		{
			dest.writeString(extraInfo.toString());
		}
		
	}
	
	public RibotTeamMember(Parcel in)
	{
		firstName = in.readString();
		lastName = in.readString();
		hex = in.readString();
		id = in.readString();
		nickName = in.readString();
		role = in.readString();
		try {
			url = new URL(in.readString());
			image = new URL (in.readString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//thanks stack overflow again :)
		
		// Deserialize Parcelable and cast to Bitmap first:
		  Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
		// Convert Bitmap to Drawable:
		drawable = new BitmapDrawable(bitmap);

		try{
			extraInfo = new JSONObject(in.readString());
			
		}
		catch(NullPointerException e)
		{
			Log.e("Error while building from parcel", "ExtraInfo not set");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static final Parcelable.Creator<RibotTeamMember> CREATOR = new Parcelable.Creator<RibotTeamMember>()
	{
		@Override	
		public RibotTeamMember createFromParcel(Parcel in){ return  new RibotTeamMember(in); }

		@Override
		public RibotTeamMember[] newArray(int size) {
			return null;
		}
			
	};
		
}
