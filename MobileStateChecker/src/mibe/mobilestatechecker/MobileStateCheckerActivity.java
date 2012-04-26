package mibe.mobilestatechecker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MobileStateCheckerActivity extends MyActivity {
	
	// ログ用タグ
	private final String TAG = "MobileStateCheckerActivity";
	
	// ログ用コンテキスト
	private final Context context = this;
	
	// サーバ用トグルボタン
	ToggleButton stb;
	
	// 機内モード用トグルボタン
	ToggleButton atb;
	
	// アクティビティ作成時に呼ばれるメソッド
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myDebug(context, TAG, "onCreate start");

		// サーバ用トグルボタンを押したときの挙動を設定
		stb = (ToggleButton)findViewById(R.id.serverToggleButton);
		setToggleButton(stb, isServiceRunning(this, MobileStateCheckService.class), serverToggleButtonListener);
		
		// 機内モード用トグルボタンを押したときの挙動を設定
		atb = (ToggleButton)findViewById(R.id.airplaneToggleButton);
		setToggleButton(atb, false, airplaneToggleButtonListener);
		
		myDebug(context, TAG, "onCreate end");
	}
	
	/* トグルボタンを押したときの挙動を設定
	 * tb  : トグルボタン
	 * mode: トグルボタンの状態
	 * occl: ボタンが押された時の動作 */
	private void setToggleButton(ToggleButton tb, boolean mode, OnCheckedChangeListener occl) {
		
		// トグルボタンの状態を設定
		tb.setChecked(mode);
		
		// トグルボタンに変更があった時の挙動を設定
		tb.setOnCheckedChangeListener(occl);
	}
	
	// サーバ用トグルボタンが押された時の動作
	private OnCheckedChangeListener serverToggleButtonListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			// ボタンの状態に合わせて分岐する
			if (isChecked) {
				
				// サービス開始
				startService(new Intent(getBaseContext(),MobileStateCheckService.class));
			} else {

				// サービス終了
				stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
			}
		}
	};
	
	// 機内モード用トグルボタンが押された時の動作
	private OnCheckedChangeListener airplaneToggleButtonListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			// ボタンの状態に合わせて分岐する
			if (isChecked) {
				
				myDebug(context, TAG, "ap: true");
			} else {

				myDebug(context, TAG, "ap: false");
			}
		}
	};
	
	// レジューム時に呼ばれるメソッド
	@Override
	public void onResume(){
		super.onResume();
		
		myDebug(context, TAG, "onResume");
		
		// サーバの状態をトグルボタンに反映する
		stb.setChecked(isServiceRunning(context, MobileStateCheckService.class));
		
		// 機内モード用トグルボタンの状態を判定する
	}
	
	// アクティビティ終了時に呼ばれるメソッド
	@Override
	public void onDestroy() {
		super.onDestroy();
		myDebug(context, TAG, "onDestroy");
		
		// サービス終了
		stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
	}
}
