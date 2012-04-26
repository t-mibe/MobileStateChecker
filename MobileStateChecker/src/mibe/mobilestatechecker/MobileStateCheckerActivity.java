package mibe.mobilestatechecker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MobileStateCheckerActivity extends Activity {
	
	// �T�[�o�p�g�O���{�^��
	private ToggleButton stb;
	
	// �@�����[�h�p�g�O���{�^��
	
	// �A�N�e�B�r�e�B�쐬���ɌĂ΂�郁�\�b�h
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// �g�O���{�^�����������Ƃ��̋�����ݒ�
		setToggleButton();
	}
	
	// �T�[�o�p�g�O���{�^�����������Ƃ��̋�����ݒ�
	private void setToggleButton() {
		
		// ID����g�O���{�^�����擾
		stb = (ToggleButton)findViewById(R.id.serverToggleButton);
		
		// �g�O���{�^���ɕύX�����������̋�����ݒ�
		stb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
			
			// �T�[�r�X�J�n
			startService(new Intent(getBaseContext(),MobileStateCheckService.class));
		} else {
			
			// �T�[�r�X�I��
			stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
		}
	}
	
	// �A�N�e�B�r�e�B�I�����ɌĂ΂�郁�\�b�h
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// �T�[�r�X�I��
		stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
	}
}
