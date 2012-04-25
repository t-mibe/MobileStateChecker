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
		
		// �g�O���{�^�����������Ƃ��̋�����ݒ�
		setToggleButton();
	}
	
	// �g�O���{�^�����������Ƃ��̋�����ݒ�
	private void setToggleButton() {
		
		// ID����g�O���{�^�����擾
		tb = (ToggleButton)findViewById(R.id.toggleButton);
		
		// �g�O���{�^���ɕύX�����������̋�����ݒ�
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			// �ύX�����������̋���
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				// �g�O���{�^���ɕύX�����������̏���
				actionToggleButton(isChecked);
			}
		});
	}
	
	// �g�O���{�^���ɕύX�����������̏���
	// state: true�Ȃ�ON
	private void actionToggleButton(boolean state) {
		
		if(state) {
			Toast.makeText(this, "on", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "off", Toast.LENGTH_SHORT).show();
		}
	}
}
