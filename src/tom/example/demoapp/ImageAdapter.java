package tom.example.demoapp;



import tom.example.demoapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private RibotTeamMember[] mThumbIds;
    
    private ImageView[] imageViews;
    
    public ImageAdapter(Context c , RibotTeamMember[] i) {
    	mContext = c;
    	mThumbIds = i;
    	imageViews = new ImageView[i.length];
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return imageViews[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        
    	
    	View gridcell;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        
        	LayoutInflater li =	(LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        	gridcell = li.inflate(R.layout.gridcell  ,null);

            
        } else {
            gridcell = convertView;
    
        }

        ImageView currentImage = (ImageView) gridcell.findViewById(R.id.image);
        TextView currentText = (TextView) gridcell.findViewById(R.id.text);
        new LoadAndSetImage(currentImage,currentText,mContext).execute(mThumbIds[position]);
        
        
        
        return gridcell;
    }

    // references to images

}