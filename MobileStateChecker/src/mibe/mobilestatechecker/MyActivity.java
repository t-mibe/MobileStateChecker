package mibe.mobilestatechecker;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MyActivity extends Activity{
	
	private final String TAG = "MyActivity";
	
	// ログとトーストに出力する（重要度はINFO)
	// context: 呼び出すオブジェクトのコンテキスト，thisでだいたいうまくいく
	// tag    : 呼び出すオブジェクトのタグ
	// msg    : メッセージの内容
	public void myInfo(Context context, String tag, String msg) {

		Log.i(tag, msg);
		Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
	
	// デバッグ時専用のmyLogAndToast
	// context: 呼び出すオブジェクトのコンテキスト，thisでだいたいうまくいく
	// tag    : 呼び出すオブジェクトのタグ
	// msg    : メッセージの内容
	public void myDebug(Context context, String tag, String msg) {
		
		Log.d(tag, msg);
		//Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
	
	// サービスの実行状態を確認する
	public boolean isServiceRunning(Context c,Class<?> cls) {
		
		ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
		
		List<RunningServiceInfo> runningService = am.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo i : runningService) {
			//myDebug(c, TAG, "service: " + i.service.getClassName() + " : " + i.started);
			if (cls.getName().equals(i.service.getClassName())) {
				myDebug(c, TAG, "service: " + i.service.getClassName() + " : running");
				//myDebug(c, TAG, "running");
				return true;
			}
		}
		
		return false;
	}
}
