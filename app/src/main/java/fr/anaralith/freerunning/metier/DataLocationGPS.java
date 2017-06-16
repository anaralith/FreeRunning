package fr.anaralith.freerunning.metier;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import fr.anaralith.freerunning.db.dao.DAO_Parcours;
import fr.anaralith.freerunning.db.models.Parcours;

//Get location GPS of Device
public class DataLocationGPS {
    private Context context;
    private LocationManager locationManager;
    private LocationProvider locationProviderGPS;
    private PendingIntent pendingGPS;

    private DAO_Parcours dbParcours;
    private long id_parcours;

    private static final String GPS = "fr.anaralith.freerunning.permission.gps";
    public static final String ACTION_GPS = "fr.anaralith.freerunning.intent.action.gps";
    public static final String ACTION_STOPGPS = "fr.anaralith.freerunning.intent.action.stopGPS";
    public final static String ID_PARCOURS = "ID_PARCOURS";

    public DataLocationGPS(Context context) {
        this.context = context;
        this.dbParcours = new DAO_Parcours(context);

        initLocationGPS();
    }

    private void initLocationGPS() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationProviderGPS = locationManager.getProvider(LocationManager.GPS_PROVIDER);
    }

    //Start GPSReceiver and GPSService
    public void enableActivity(String nameParcours) {
        id_parcours = createParcours(nameParcours);
        Log.i("DevApp", "DataLocationGPS - Id parcours : " + id_parcours);

        //Receiver
        Intent intentReceiver = new Intent(context, GPSUpdateReceiver.class);
        intentReceiver.putExtra(ID_PARCOURS, id_parcours);
        intentReceiver.setAction(ACTION_GPS);

        pendingGPS = PendingIntent.getBroadcast(context, 0, intentReceiver, PendingIntent.FLAG_UPDATE_CURRENT);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, pendingGPS);

        //Service
        Intent intentService = new Intent(context, GPSService.class);
        intentService.putExtra(ID_PARCOURS, id_parcours);
        context.startService(intentService);
    }

    //Stop GPSReceiver and GPSService
    public void disableActivity(){
        if(pendingGPS != null)
            locationManager.removeUpdates(pendingGPS);

        Intent intentService = new Intent(context, GPSService.class);
        intentService.setAction(ACTION_STOPGPS);
        intentService.putExtra(ID_PARCOURS, id_parcours);
        context.startService(intentService);
    }

    //Ajoute le nouveau parcours à la base
    private long createParcours(String nameParcours){
        long id_parcours = 0;

        try {
            dbParcours.open();
            Parcours parcours = new Parcours(nameParcours);
            id_parcours = dbParcours.addParcours(parcours);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(dbParcours != null)
                dbParcours.close();
        }

        return id_parcours;
    }
}