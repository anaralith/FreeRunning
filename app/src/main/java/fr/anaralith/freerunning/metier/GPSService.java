package fr.anaralith.freerunning.metier;

import android.app.IntentService;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;

import fr.anaralith.freerunning.db.dao.DAO_Position;
import fr.anaralith.freerunning.db.models.Position;

import static fr.anaralith.freerunning.metier.DataLocationGPS.ACTION_GPS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ACTION_STOPGPS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ID_PARCOURS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.TIME_RUNNING;

public class GPSService extends IntentService {
    private DAO_Position dbPosition = null;

    public GPSService(){
        super("Default");
        this.dbPosition = new DAO_Position(this);
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
        long id_parcours = intent.getLongExtra(ID_PARCOURS, 0);

        if(intent.getAction() == ACTION_GPS){
            Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);

            //Enregistre dans SQLite les coordonnées
            if(location != null){
                //Date des coordonnées en milisecond (long)
                Calendar c = Calendar.getInstance();
                long date = c.getTimeInMillis();

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
        } else if(intent.getAction() == ACTION_STOPGPS) {
            //TODO Génération Rapport performance
            long time = intent.getLongExtra(TIME_RUNNING, 0)/1000; //Seconde
            Log.i("DevApp", "GPSService - temps (s): " + time);

            RunningDataProcess performance = new RunningDataProcess(time, "", this);

            //Calcul all data performances
            double distance = performance.calcDistance(id_parcours);
            Log.i("DevApp", "GPSService - distance (Km) : " + distance);

            double vitesseMoy = performance.calcVitesseMoy(distance, time);
            Log.i("DevApp", "GPSService - Vitesse Moyenne (Km/h) : " + vitesseMoy);

            String rythmeMoy = performance.calcRythmeMoy(vitesseMoy);
            Log.i("DevApp", "GPSService - Rythme Moyen (min/Km) : " + rythmeMoy);

            //Stop le service
            stopSelf();
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

    private String getDate(long milliSeconds, String dateFormat){
        //getDate(c.getTimeInMillis(),"dd/MM/yyyy hh:mm:ss");
        return DateFormat.format(dateFormat, milliSeconds).toString();
    }
}
