package mibe.mobilestatechecker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

public class MobileStateCheckService extends Service {
	
	// ログ識別用タグ
	final private String TAG = "MobileStateCheckService";
	
	// 
	private int ss_old = -1;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// サービス開始時に呼ばれるメソッド
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "onStartCommand start");
		
		// 通信状態を監視するレシーバを準備する
		setPhoneStateListener();
		
		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "onStartCommand end");
		
		return START_STICKY;
	}
	
	// 通信状態を監視するレシーバを準備する
	private void setPhoneStateListener() {
		
		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "setPhoneStateListener start");
		
		// 通信状態監視用のレシーバを初期化する
		PhoneStateListener psl = new PhoneStateListener() {
			
			// サービス状態の変化時に呼ばれるメソッド
			@Override
			public void onServiceStateChanged(ServiceState serviceState) {
				
				// サービス状態の変化時に行う処理
				onServiceStateChangedAction(serviceState.getState());
			}
		};
		
		// 電話情報の受信を開始する
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(psl, PhoneStateListener.LISTEN_SERVICE_STATE);

		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "setPhoneStateListener end");
	}
	
	// サービス状態の変化時に行う処理
	private void onServiceStateChangedAction(int ss) {
		
		// 出力する文字列
		String str = "State = ";
		
		// 状態が変化していないとき，処理を中断する
		if (ss == ss_old) return;
		
		// 状態を更新する
		ss_old = ss;
		
		// 文字列に対して，状態に合わせた追記を行う
		switch(ss){
		case ServiceState.STATE_EMERGENCY_ONLY:
			str += "STATE_EMERGENCY_ONLY";
			break;
		case ServiceState.STATE_IN_SERVICE:
			str += "STATE_IN_SERVICE内";
			break;
		case ServiceState.STATE_OUT_OF_SERVICE:
			str += "STATE_OUT_OF_SERVICE";
			break;
		case ServiceState.STATE_POWER_OFF:
			str += "STATE_POWER_OFF";
			break;
		default:
		}
		
		// 文字列をログとトーストに出力する
		new Common().myLogAndToast(this, TAG, str);
	}
	
	// サービス終了時に呼ばれるメソッド
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// ログとトーストを出力する
		new Common().myLogAndToast(this, TAG, "onDestroy");
	}
}
