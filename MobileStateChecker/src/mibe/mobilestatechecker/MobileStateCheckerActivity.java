package mibe.mobilestatechecker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MobileStateCheckerActivity extends MyActivity {
	
	// ���O�p�^�O
	private final String TAG = "MobileStateCheckerActivity";
	
	// ���O�p�R���e�L�X�g
	private final Context context = this;
	
	// �T�[�o�p�g�O���{�^��
	private ToggleButton stb;
	
	// �@�����[�h�p�g�O���{�^��
	private ToggleButton atb;
	
	// �A�N�e�B�r�e�B�쐬���ɌĂ΂�郁�\�b�h
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myDebug(context, TAG, "onCreate");

		// �T�[�o�p�g�O���{�^�����������Ƃ��̋�����ݒ�
		setToggleButton(stb, false, R.id.serverToggleButton, serverToggleButtonListener);
		
		// �@�����[�h�p�g�O���{�^�����������Ƃ��̋�����ݒ�
		setToggleButton(atb, getAirplaneMode(), R.id.airplaneToggleButton, airplaneToggleButtonListener);
	}
	
	/* �g�O���{�^�����������Ƃ��̋�����ݒ�
	 * tb  : �g�O���{�^��
	 * mode: �g�O���{�^���̏��
	 * id  : �g�O���{�^���̃��\�[�XID
	 * occl: �{�^���������ꂽ���̓��� */
	private void setToggleButton(ToggleButton tb, boolean mode, int id, OnCheckedChangeListener occl) {
		
		// ID����g�O���{�^�����擾
		tb = (ToggleButton)findViewById(id);
		
		// �g�O���{�^���̏�Ԃ�ݒ�
		tb.setChecked(mode);
		
		// �g�O���{�^���ɕύX�����������̋�����ݒ�
		tb.setOnCheckedChangeListener(occl);
	}
	
	// �T�[�o�p�g�O���{�^���������ꂽ���̓���
	private OnCheckedChangeListener serverToggleButtonListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			// �{�^���̏�Ԃɍ��킹�ĕ��򂷂�
			if (isChecked) {
				
				// �T�[�r�X�J�n
				startService(new Intent(getBaseContext(),MobileStateCheckService.class));
			} else {

				// �T�[�r�X�I��
				stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
			}
		}
	};
	
	// �@�����[�h�p�g�O���{�^���������ꂽ���̓���
	private OnCheckedChangeListener airplaneToggleButtonListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			// �{�^���̏�Ԃɍ��킹�ĕ��򂷂�
			if (isChecked) {
				
				myDebug(context, TAG, "ap: true");
			} else {

				myDebug(context, TAG, "ap: false");
			}
		}
	};
	
	// ���W���[�����ɌĂ΂�郁�\�b�h
	@Override
	public void onResume(){
		super.onResume();
		
		myDebug(context, TAG, "onResume");
		
		// �T�[�o�p�g�O���{�^���̏�Ԃ𔻒肷��
		
		// �@�����[�h�p�g�O���{�^���̏�Ԃ𔻒肷��
	}
	
	// �@�����[�h��OnOff���擾
	private boolean getAirplaneMode() {
		return false;
	}
	
	// �A�N�e�B�r�e�B�I�����ɌĂ΂�郁�\�b�h
	@Override
	public void onDestroy() {
		super.onDestroy();
		myDebug(context, TAG, "onDestroy");
		
		// �T�[�r�X�I��
		stopService(new Intent(getBaseContext(),MobileStateCheckService.class));
	}
}
