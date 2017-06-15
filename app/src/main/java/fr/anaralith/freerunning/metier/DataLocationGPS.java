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

    public final static String ID_PARCOURS = "ID_PARCOURS";
    public final static String DATE_COORDONNEES = "DATE_COORDONNEES";

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
    public void enableActivity(String nameParcours, String date) {
        long id_parcours = createParcours(nameParcours);
        Log.i("DevApp", "DataLocationGPS - Id parcours : " + id_parcours);

        //Receiver
        Intent intentReceiver = new Intent(context, GPSUpdateReceiver.class);
        intentReceiver.putExtra(ID_PARCOURS, id_parcours);
        intentReceiver.putExtra(DATE_COORDONNEES, date);
        pendingGPS = PendingIntent.getBroadcast(context, 0, intentReceiver, PendingIntent.FLAG_UPDATE_CURRENT);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, pendingGPS);

        //Service
        Intent intentService = new Intent(context, GPSService.class);
        intentService.putExtra(ID_PARCOURS, id_parcours);
        intentService.putExtra(DATE_COORDONNEES, date);
        context.startService(intentService);
    }

    //Stop GPSReceiver and GPSService
    public void disableActivity(){
        Intent intentService = new Intent(context, GPSService.class);

        if(pendingGPS != null)
            locationManager.removeUpdates(pendingGPS);

        context.stopService(intentService);
    }

//    Ajoute le nouveau parcours à la base
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