package mibe.mobilestatechecker;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

public class MyActivity extends Activity{
	
	private final String TAG = "MyActivity";
	
	// ���O�ƃg�[�X�g�ɏo�͂���i�d�v�x��INFO)
	// context: �Ăяo���I�u�W�F�N�g�̃R���e�L�X�g�Cthis�ł����������܂�����
	// tag    : �Ăяo���I�u�W�F�N�g�̃^�O
	// msg    : ���b�Z�[�W�̓��e
	public void myInfo(Context context, String tag, String msg) {

		Log.i(tag, msg);
		Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
	
	// �f�o�b�O����p��myLogAndToast
	// context: �Ăяo���I�u�W�F�N�g�̃R���e�L�X�g�Cthis�ł����������܂�����
	// tag    : �Ăяo���I�u�W�F�N�g�̃^�O
	// msg    : ���b�Z�[�W�̓��e
	public void myDebug(Context context, String tag, String msg) {
		
		Log.d(tag, msg);
		//Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
	
	// �T�[�r�X�̎��s��Ԃ��m�F����
	// c  : �Ăяo���I�u�W�F�N�g�̃R���e�L�X�g�Cthis�ł����������܂�����
	// cls: �ΏۂƂȂ�T�[�r�X�̃N���X(�N���X��.class)
	public boolean isServiceRunning(Context c,Class<?> cls) {
		
		ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
		
		List<RunningServiceInfo> runningService = am.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo i : runningService) {
			//myDebug(c, TAG, "service: " + i.service.getClassName() + " : " + i.started);
			if (cls.getName().equals(i.service.getClassName())) {
				myDebug(c, TAG, "service: " + i.service.getClassName() + " : running");
				//myDebug(c, TAG, "running");
				return true;
			}
		}
		
		return false;
	}
	
	// �@�����[�h��On�ɂȂ��Ă��邩���m�F����
	public boolean isAirplaneMode() {
		int value = 0;
		try {
			value = Settings.System.getInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON);
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
		
		if(value == 1) return true;
		else return false;
	}
	
	// �@�����[�h�̐؂�ւ����s��
	public void setAirplaneMode(boolean b) {
		
		// �ݒ�̐؂�ւ��Ɏg���l
		int value = b ? 1 : 0;
		
		// �ύX����Ώ�
		String target = "cell,wifi,bluetooth";
		target = "cell";
		
		// �ύX����Ώۂ�ݒ肷��
		Settings.System.putString(getContentResolver(), Settings.System.AIRPLANE_MODE_RADIOS, target);
		
		Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, value);
		//Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 1);
		
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", value);
		sendBroadcast(intent);
	}
}
