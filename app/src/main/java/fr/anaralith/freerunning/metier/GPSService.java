package fr.anaralith.freerunning.metier;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import fr.anaralith.freerunning.db.dao.DAO_Parcours;
import fr.anaralith.freerunning.db.dao.DAO_Performance;
import fr.anaralith.freerunning.db.dao.DAO_Position;
import fr.anaralith.freerunning.db.models.Parcours;
import fr.anaralith.freerunning.db.models.Performance;
import fr.anaralith.freerunning.db.models.Position;
import fr.anaralith.freerunning.view.RunActivity;

import static fr.anaralith.freerunning.metier.DataLocationGPS.ACTION_GPS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ACTION_STOPGPS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.ID_PARCOURS;
import static fr.anaralith.freerunning.metier.DataLocationGPS.TIME_RUNNING;

public class GPSService extends IntentService {
    public static final String RAPPORT = "RAPPORT";

    private Performance rapport = null;
    private Parcours parcours= null;

    private DAO_Position dbPosition = null;
    private DAO_Performance dbPerformance = null;
    private DAO_Parcours dbParcours = null;

    private Activity activity = null;

    public GPSService(){
        super("Default");
        this.dbPosition = new DAO_Position(this);
        this.dbPerformance = new DAO_Performance(this);
        this.dbParcours = new DAO_Parcours(this);

    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GPSService(String name) {
        super(name);
        this.dbPosition = new DAO_Position(this);
        this.dbPerformance = new DAO_Performance(this);
        this.dbParcours = new DAO_Parcours(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        long id_parcours = intent.getLongExtra(ID_PARCOURS, 0);
        //Date des coordonnées en milisecond (long)
        Calendar c = Calendar.getInstance();
        long date = c.getTimeInMillis();

        if(intent.getAction() == ACTION_GPS){
            Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);

            //Enregistre dans SQLite les coordonnées
            if(location != null){
                try {
                    dbPosition.open();
                    Position position = new Position(location.getLatitude(),
                                                        location.getLongitude(),
                                                        getAltitudePoint(location.getLatitude(), location.getLongitude()),
                                                        date,
                                                        id_parcours);
                    dbPosition.addPosition(position);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(dbPosition != null)
                        dbPosition.close();
                }
            }
        } else if(intent.getAction() == ACTION_STOPGPS) {
            //TODO Génération Rapport performance
            long time = intent.getLongExtra(TIME_RUNNING, 0)/1000; //Seconde

            RunningDataProcess performance = new RunningDataProcess(time, String.valueOf(date), this);

            List<Position> listPosition = null;
            try {
                dbPosition.open();
                listPosition = dbPosition.getPositionByIdParcours(id_parcours);

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(dbPosition != null)
                    dbPosition.close();
            }

            rapport = performance.getRapport(id_parcours, listPosition);

            Log.i("DevApp", "GPSService - temps (s): " + rapport.getTemps_perf());
            Log.i("DevApp", "GPSService - distance (Km) : " + rapport.getDistance_perf());
            Log.i("DevApp", "GPSService - Vitesse Moyenne (Km/h) : " + rapport.getVitesseMoyenne_perf());
            Log.i("DevApp", "GPSService - Rythme Moyen (seconde/Km) : " + rapport.getRythmeMoyen_perf());
            Log.i("DevApp", "GPSService - Dénivelé : \nPositif : " + rapport.getDenivele_positif_perf()
                    + "\nNegatif : " + rapport.getDenivele_negatif_perf());
            Log.i("DevApp", "GPSService - Date: " + rapport.getDate_perf());

            //Sauvegarde en base du rapport ! update de l'id_perf dans parcours !
            saveRapport(id_parcours);
            Log.i("DevApp", "Terminée !");

            //Stop le service
            //TODO Appel l'activité du rapport !
            Intent newActivity = new Intent(GPSService.this, RunActivity.class);
            newActivity.putExtra(RAPPORT, rapport);
            newActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newActivity);

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

    //Recupère l'élevation
    private double getAltitudePoint(double latitude, double longitude) throws Exception {
        final String ELEVATION_API_URL =  "https://maps.googleapis.com/maps/api/elevation/";
        final String OUTPUT_FORMAT = "json";
        final String URL_KEY = "&key=AIzaSyAB_GDs7kEgzgTFGY3qZqcXzOdzlwIeCKk";
        final String URL_SIMPLE_LOCATION = "locations=";
        //https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&key=API_KEY

        URL url_API_Elevation = new URL(ELEVATION_API_URL + OUTPUT_FORMAT + "?" + URL_SIMPLE_LOCATION + latitude + "," + longitude + URL_KEY);

        HttpURLConnection con = (HttpURLConnection) url_API_Elevation.openConnection();
        con.connect();

        //Lecture du flux
        InputStream inputStream = con.getInputStream();
        String getHttp = InputStreamOperations.InputStreamToString(inputStream);

        //Traitement JSON
        JSONObject jsonObject = new JSONObject(getHttp);
        JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));

        JSONObject obj = new JSONObject(jsonArray.getString(0));
        double elevation = 0d;

        try {
            elevation = Double.valueOf(obj.getString("elevation"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return elevation;
    }

    private void saveRapport(long id_parcours){
        try {
            dbParcours.open();
            parcours = dbParcours.getParcours(id_parcours);
            parcours.setId_performance(savePerformance());
            dbParcours.updateParcours(parcours);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(dbParcours != null)
                dbParcours.close();
        }
    }

    private int savePerformance(){
        int id_performance = 0;

        try {
            dbPerformance.open();
            id_performance = dbPerformance.addPerformance(rapport);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(dbPerformance != null)
                dbPerformance.close();
        }

        return id_performance;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
