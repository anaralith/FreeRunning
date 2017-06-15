package fr.anaralith.freerunning.metier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import static fr.anaralith.freerunning.metier.DataLocationGPS.DATE_COORDONNEES;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ID_PARCOURS;

public class GPSUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);

        //--------------------------------------------------------------------------------------
        if(location != null)
            Toast.makeText(context, "Latitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        //--------------------------------------------------------------------------------------

        Intent intentService = new Intent(context, GPSService.class);
        intentService.setClass(context, GPSService.class);
        context.startService(intentService);
    }
}
