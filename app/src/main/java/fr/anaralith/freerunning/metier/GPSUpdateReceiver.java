package fr.anaralith.freerunning.metier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

public class GPSUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
        Log.e("DEBUG - RECEIVER", "" + location); //TODO : Debug

        if(location != null)
            Toast.makeText(context, "Latitude : " + location.getLatitude() + "Longitude : " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }
}
