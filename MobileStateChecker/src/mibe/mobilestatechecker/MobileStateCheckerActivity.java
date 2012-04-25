package mibe.mobilestatechecker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MobileStateCheckerActivity extends Activity {
	
	private ToggleButton tb;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// トグルボタンを押したときの挙動を設定
		setToggleButton();
	}
	
	// トグルボタンを押したときの挙動を設定
	private void setToggleButton() {
		
		// IDからトグルボタンを取得
		tb = (ToggleButton)findViewById(R.id.toggleButton);
		
		// トグルボタンに変更があった時の挙動を設定
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
			Toast.makeText(this, "on", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "off", Toast.LENGTH_SHORT).show();
		}
	}
}
