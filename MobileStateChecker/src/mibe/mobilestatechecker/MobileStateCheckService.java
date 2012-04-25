package mibe.mobilestatechecker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MobileStateCheckService extends Service {
	
	// ログ識別用タグ
	final private String TAG = "MobileStateCheckService";
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// サービス開始時に呼ばれるメソッド
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		// 通信状態を監視するレシーバを準備する
		setPhoneStateListener();
		
		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "onStartCommand");
		
		return START_STICKY;
	}
	
	// 通信状態を監視するレシーバを準備する
	private void setPhoneStateListener() {
		
	}
	
	// サービス終了時に呼ばれるメソッド
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "onDestroy");
	}
}
