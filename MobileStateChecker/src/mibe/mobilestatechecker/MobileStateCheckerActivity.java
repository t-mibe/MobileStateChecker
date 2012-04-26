package mibe.mobilestatechecker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MobileStateCheckerActivity extends Activity {
	
	// サーバ用トグルボタン
	private ToggleButton stb;
	
	// 機内モード用トグルボタン
	
	// アクティビティ作成時に呼ばれるメソッド
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// トグルボタンを押したときの挙動を設定
		setToggleButton();
	}
	
	// サーバ用トグルボタンを押したときの挙動を設定
	private void setToggleButton() {
		
		// IDからトグルボタンを取得
		stb = (ToggleButton)findViewById(R.id.serverToggleButton);
		
		// トグルボタンに変更があった時の挙動を設定
		stb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			// 変更があった時の挙動
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				// トグルボタンに変更があった時の処理
				actionToggleButton(isChecked);
			}
		});
	}
	
	// トグルボタンに変更があった時の処理
	// state: trueならON
	private void actionToggleButton(boolean state) {
		
		if(state) {
			
			// サービス開始
			startService(new Intent(getBaseContext(),MobileStateCheckService.class));
		} else {
			
			// サービス終了
			stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
		}
	}
	
	// アクティビティ終了時に呼ばれるメソッド
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// サービス終了
		stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
	}
}
