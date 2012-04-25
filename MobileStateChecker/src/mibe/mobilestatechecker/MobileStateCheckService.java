package mibe.mobilestatechecker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

public class MobileStateCheckService extends Service {
	
	// ���O���ʗp�^�O
	final private String TAG = "MobileStateCheckService";
	
	// 
	private int ss_old = -1;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// �T�[�r�X�J�n���ɌĂ΂�郁�\�b�h
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "onStartCommand start");
		
		// �ʐM��Ԃ��Ď����郌�V�[�o����������
		setPhoneStateListener();
		
		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "onStartCommand end");
		
		return START_STICKY;
	}
	
	// �ʐM��Ԃ��Ď����郌�V�[�o����������
	private void setPhoneStateListener() {
		
		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "setPhoneStateListener start");
		
		// �ʐM��ԊĎ��p�̃��V�[�o������������
		PhoneStateListener psl = new PhoneStateListener() {
			
			// �T�[�r�X��Ԃ̕ω����ɌĂ΂�郁�\�b�h
			@Override
			public void onServiceStateChanged(ServiceState serviceState) {
				
				// �T�[�r�X��Ԃ̕ω����ɍs������
				onServiceStateChangedAction(serviceState.getState());
			}
		};
		
		// �d�b���̎�M���J�n����
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(psl, PhoneStateListener.LISTEN_SERVICE_STATE);

		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "setPhoneStateListener end");
	}
	
	// �T�[�r�X��Ԃ̕ω����ɍs������
	private void onServiceStateChangedAction(int ss) {
		
		// �o�͂��镶����
		String str = "State = ";
		
		// ��Ԃ��ω����Ă��Ȃ��Ƃ��C�����𒆒f����
		if (ss == ss_old) return;
		
		// ��Ԃ��X�V����
		ss_old = ss;
		
		// ������ɑ΂��āC��Ԃɍ��킹���ǋL���s��
		switch(ss){
		case ServiceState.STATE_EMERGENCY_ONLY:
			str += "STATE_EMERGENCY_ONLY";
			break;
		case ServiceState.STATE_IN_SERVICE:
			str += "STATE_IN_SERVICE��";
			break;
		case ServiceState.STATE_OUT_OF_SERVICE:
			str += "STATE_OUT_OF_SERVICE";
			break;
		case ServiceState.STATE_POWER_OFF:
			str += "STATE_POWER_OFF";
			break;
		default:
		}
		
		// ����������O�ƃg�[�X�g�ɏo�͂���
		new Common().myLogAndToast(this, TAG, str);
	}
	
	// �T�[�r�X�I�����ɌĂ΂�郁�\�b�h
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "onDestroy");
	}
}
