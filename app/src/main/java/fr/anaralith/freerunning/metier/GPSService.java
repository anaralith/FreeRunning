package fr.anaralith.freerunning.metier;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.util.Log;

import fr.anaralith.freerunning.db.dao.DAO_Position;
import fr.anaralith.freerunning.db.models.Position;

import static fr.anaralith.freerunning.metier.DataLocationGPS.DATE_COORDONNEES;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ID_PARCOURS;

public class GPSService extends IntentService {

    private DAO_Position dbPosition = null;

    public GPSService(){
        super("Default");
        this.dbPosition = new DAO_Position(this);
        Log.i("DevApp", "Service - Constructeur OK ");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GPSService(String name) {
        super(name);
        this.dbPosition = new DAO_Position(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
        Log.e("DevApp", "GPSService - location : " + location);
        long id_parcours = intent.getLongExtra(ID_PARCOURS, 0);
        String date = intent.getStringExtra(DATE_COORDONNEES);

        //Enregistre dans SQLite les coordonnées
        if(location != null){
            try {
                dbPosition.open();
                Position position = new Position(location.getLatitude(), location.getLongitude(), date, id_parcours);
                dbPosition.addPosition(position);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbPosition.close();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
