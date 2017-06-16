package fr.anaralith.freerunning.metier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GPSStopReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent intentService = new Intent(intent);
        intentService.setClass(context, GPSService.class);
        context.startService(intentService);
    }
}
