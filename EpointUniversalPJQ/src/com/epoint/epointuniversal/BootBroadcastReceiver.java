/**
 * @author lilin
 * @date 2014-1-10 上午10:26:49
 * @annotation 自启动广播
 */
package com.epoint.epointuniversal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String action_boot = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("andli", "接受系统广播");
		if (intent.getAction().equals(action_boot)) {
			Intent ootStartIntent = new Intent(context, MainActivity.class);
			ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(ootStartIntent);
		}

	}

}
