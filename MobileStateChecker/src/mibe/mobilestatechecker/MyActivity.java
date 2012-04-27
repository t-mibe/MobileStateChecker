package mibe.mobilestatechecker;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

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
	// c  : 呼び出すオブジェクトのコンテキスト，thisでだいたいうまくいく
	// cls: 対象となるサービスのクラス(クラス名.class)
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
	
	// 機内モードがOnになっているかを確認する
	public boolean isAirplaneMode() {
		int value = 0;
		try {
			value = Settings.System.getInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON);
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
		
		if(value == 1) return true;
		else return false;
	}
	
	// 機内モードの切り替えを行う
	public void setAirplaneMode(boolean b) {
		
		// 設定の切り替えに使う値
		int value = b ? 1 : 0;
		
		// 変更する対象
		String target = "cell,wifi,bluetooth";
		target = "cell";
		
		// 変更する対象を設定する
		Settings.System.putString(getContentResolver(), Settings.System.AIRPLANE_MODE_RADIOS, target);
		
		Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, value);
		//Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 1);
		
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", value);
		sendBroadcast(intent);
	}
}
