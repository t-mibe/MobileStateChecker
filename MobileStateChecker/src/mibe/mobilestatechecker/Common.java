package mibe.mobilestatechecker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Common {
	
	
	
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
}
