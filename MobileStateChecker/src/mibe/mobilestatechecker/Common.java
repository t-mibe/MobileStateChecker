package mibe.mobilestatechecker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Common {
	
	public void myLogAndToast(Context context, String tag, String msg) {

		Log.d(tag, msg);
		Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
}
