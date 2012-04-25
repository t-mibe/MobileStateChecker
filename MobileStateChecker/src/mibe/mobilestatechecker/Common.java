package mibe.mobilestatechecker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Common {
	
	
	
	// ログとトーストに出力する（重要度はINFO)
	// context: 呼び出すオブジェクトのコンテキスト，thisでだいたいうまくいく
	// tag    : 呼び出すオブジェクトのタグ
	// msg    : メッセージの内容
	public void myInfo(Context context, String tag, String msg) {

		Log.i(tag, msg);
		Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
	
	// デバッグ時専用のmyLogAndToast
	// context: 呼び出すオブジェクトのコンテキスト，thisでだいたいうまくいく
	// tag    : 呼び出すオブジェクトのタグ
	// msg    : メッセージの内容
	public void myDebug(Context context, String tag, String msg) {
		
		Log.d(tag, msg);
		//Toast.makeText(context, tag + " : " + msg, Toast.LENGTH_SHORT).show();
	}
}
