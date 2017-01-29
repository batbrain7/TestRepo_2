package com.example.mohitkumar.calldetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by mohitkumar on 29/01/17.
 */

public class DetectCalls extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

            Toast.makeText(context,"The call is poutgoing",Toast.LENGTH_LONG).show();
            //outgoing call
        } else if (intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) != null) {
            //incoming call

            String n = TelephonyManager.EXTRA_INCOMING_NUMBER;
            Toast.makeText(context,n + " Incoming call",Toast.LENGTH_LONG).show();
        }

    }
}
