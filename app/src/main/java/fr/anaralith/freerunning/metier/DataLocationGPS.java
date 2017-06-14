package fr.anaralith.freerunning.metier;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;

//Get location GPS of Device
public class DataLocationGPS {

    private Context context;
    private LocationManager locationManager;
    private LocationProvider locationProviderGPS;
    private PendingIntent pendingGPS;

    public DataLocationGPS(Context context) {
        this.context = context;

        initLocationGPS();
    }

    private void initLocationGPS() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationProviderGPS = locationManager.getProvider(LocationManager.GPS_PROVIDER);
    }

    //Start GPSReceiver and GPSService
    public void enableActivity() {
        //Receiver
        Intent intentReceiver = new Intent(context, GPSUpdateReceiver.class);
        pendingGPS = PendingIntent.getBroadcast(context, 0, intentReceiver, PendingIntent.FLAG_UPDATE_CURRENT);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, pendingGPS);

        //Service
        Intent intentService = new Intent(context, GPSService.class);
        context.startService(intentService);
    }

    //Stop GPSReceiver and GPSService
    public void disableActivity(){
        Intent intentService = new Intent(context, GPSService.class);

        if(pendingGPS != null)
            locationManager.removeUpdates(pendingGPS);

        context.stopService(intentService);
    }
}