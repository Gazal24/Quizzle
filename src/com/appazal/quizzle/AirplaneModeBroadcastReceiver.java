package com.appazal.quizzle;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		SmsMessage[] sms = Telephony.Sms.Intents.getMessagesFromIntent(intent);
		Log.i(Config.TAG, "Yes Airplane mode is changed. It's nonsense. Quota is " 
				+ intent.getIntExtra("quota", 0) + "sms are " + sms[0].getMessageBody());
	}
}
