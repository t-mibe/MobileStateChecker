package mibe.mobilestatechecker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

public class MobileStateCheckService extends MyService {
	
	// ���O���ʗp�^�O
	final private String TAG = "MobileStateCheckService";
	
	// �ʐM��Ԃ��Ď����郌�V�[�o
	PhoneStateListener psl;
	
	// �[���̏��ɃA�N�Z�X����N���X
	TelephonyManager tm;
	
	// �ʐM��Ԃ�ۑ�����ϐ�
	private int ss_old = -1;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// �T�[�r�X�\�����ɌĂ΂�郁�\�b�h
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	// �T�[�r�X�J�n���ɌĂ΂�郁�\�b�h
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		// �ʐM��Ԃ��Ď����郌�V�[�o����������
		setPhoneStateListener();
		
		// ���O�ƃg�[�X�g���o�͂���
		myInfo(this, TAG, "onStartCommand");
		
		return START_STICKY;
	}
	
	// �ʐM��Ԃ��Ď����郌�V�[�o����������
	private void setPhoneStateListener() {
		
		// ���O�ƃg�[�X�g���o�͂���
		myDebug(this, TAG, "setPhoneStateListener start");
		
		// �ʐM��ԊĎ��p�̃��V�[�o������������
		psl = new PhoneStateListener() {
			
			// �T�[�r�X��Ԃ̕ω����ɌĂ΂�郁�\�b�h
			@Override
			public void onServiceStateChanged(ServiceState serviceState) {
				
				// �T�[�r�X��Ԃ̕ω����ɍs������
				onServiceStateChangedAction(serviceState.getState());
			}
		};
		
		// �d�b���̎�M���J�n����
		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(psl, PhoneStateListener.LISTEN_SERVICE_STATE);

		// ���O�ƃg�[�X�g���o�͂���
		myDebug(this, TAG, "setPhoneStateListener end");
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
		myInfo(this, TAG, str);
	}
	
	// �T�[�r�X�I�����ɌĂ΂�郁�\�b�h
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// �ʐM��Ԃ̊Ď����I������
		endPhoneStateListener();
		
		// ���O�ƃg�[�X�g���o�͂���
		myInfo(this, TAG, "onDestroy");
	}
	
	// �ʐM��Ԃ̊Ď����I������
	private void endPhoneStateListener() {
		
		// �d�b���̎�M���I������
		tm.listen(psl, PhoneStateListener.LISTEN_NONE);
		
	}
}
