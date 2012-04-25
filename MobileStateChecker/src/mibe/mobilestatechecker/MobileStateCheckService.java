package mibe.mobilestatechecker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MobileStateCheckService extends Service {
	
	// ���O���ʗp�^�O
	final private String TAG = "MobileStateCheckService";
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// �T�[�r�X�J�n���ɌĂ΂�郁�\�b�h
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		// �ʐM��Ԃ��Ď����郌�V�[�o����������
		setPhoneStateListener();
		
		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "onStartCommand");
		
		return START_STICKY;
	}
	
	// �ʐM��Ԃ��Ď����郌�V�[�o����������
	private void setPhoneStateListener() {
		
	}
	
	// �T�[�r�X�I�����ɌĂ΂�郁�\�b�h
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// ���O�ƃg�[�X�g���o�͂���
		new Common().myLogAndToast(this, TAG, "onDestroy");
	}
}
