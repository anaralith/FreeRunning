package fr.anaralith.freerunning.metier;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.List;

import fr.anaralith.freerunning.db.dao.DAO_Parcours;
import fr.anaralith.freerunning.db.dao.DAO_Performance;
import fr.anaralith.freerunning.db.dao.DAO_Position;
import fr.anaralith.freerunning.db.dao.DAO_User;
import fr.anaralith.freerunning.db.dao.DAO_UserPerformance;
import fr.anaralith.freerunning.db.models.Performance;
import fr.anaralith.freerunning.db.models.Position;

public class RunningDataProcess {
    private DAO_Position dbPosition = null;
    private Performance performance = null;

    private long temps;
    private String date;
    private double distance = 0f;

    public RunningDataProcess(long temps, String date, Context context) {
        this.performance = new Performance();
        this.temps = temps;
        this.date = date;
        this.dbPosition = new DAO_Position(context);

        performance.setDate_perf(date);
        performance.setTemps_perf(temps);
    }

    public Performance getRapport(long id_parcours, List<Position> allPosition){
        double distance = calcDistance(id_parcours);
        double vitesseMoy = calcVitesseMoy(distance, temps);
        calcRythmeMoy(vitesseMoy);
        calcDenivele(allPosition);
        return performance;
    }

    //Calcul Distance : en metre
    public double calcDistance(long id_parcours){
        List<Position> listPosition = null;
        Location startPoint = new Location(LocationManager.GPS_PROVIDER);
        Location endPoint = new Location(LocationManager.GPS_PROVIDER);

        try {
            dbPosition.open();
            listPosition = dbPosition.getPositionByIdParcours(id_parcours);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(dbPosition != null)
                dbPosition.close();
        }

        if(listPosition != null){
            for(int i=0;i<listPosition.size();i++){
                if((i + 1) != listPosition.size()){
                    startPoint.setLatitude(listPosition.get(i).getLatitude_position());
                    startPoint.setLongitude(listPosition.get(i).getLongitude_position());
                    endPoint.setLatitude(listPosition.get(i+1).getLatitude_position());
                    endPoint.setLongitude(listPosition.get(i+1).getLongitude_position());

                    distance += startPoint.distanceTo(endPoint);
                }
            }

            distance /= 1000;
            performance.setDistance_perf(distance);
        }

        return distance;
    }

    //Calcul Rythme Moyen : retour en seconde
    public String calcRythmeMoy(double vitesseMoy){
        double rythmeMoy = 60.0/vitesseMoy;

        int minute = new Double(rythmeMoy).intValue();
        int seconde = (int)((rythmeMoy-minute)*60);

        if(vitesseMoy == 0.0){
            performance.setRythmeMoyen_perf(0);
            return "" + 0;
        } else {
            performance.setRythmeMoyen_perf(minute*60 + seconde);
            return "" + minute + "\'" + seconde + "\"";
        }
    }

    //Calcul Vitesse Moyenne
    public double calcVitesseMoy(double distance, long temps){
        double vitesseMoy = (double)Math.round(distance / ((double)temps/60.0/60.0));

        performance.setVitesseMoyenne_perf(vitesseMoy);
        return vitesseMoy;
    }

    //Calcul Dénivelé
    public long[] calcDenivele(List<Position> allPosition){
        //Denivele Positif | Negatif
        long denivele[] = {0, 0};
        long deniveleTemp = 0;
        long lastDenivele = 0;
        boolean firstCheck = true;

        if(allPosition != null){
            for (Position position : allPosition) {
                if(firstCheck){
                    firstCheck = false;
                    lastDenivele = (long)position.getAlttitude();
                } else {
                    deniveleTemp = (long)position.getAlttitude();
                    lastDenivele -= deniveleTemp;

                    if(lastDenivele > 0){
                        denivele[0] += lastDenivele;
                    } else {
                        denivele[1] += Math.abs(lastDenivele);
                    }

                    lastDenivele = (long)position.getAlttitude();
                }
            }
        } else {
            Log.e("DevApp", "RunningDataProcess - allPosition = null");
        }

        performance.setDenivele_positif_perf(denivele[0]);
        performance.setDenivele_negatif_perf(denivele[1]);
        return denivele;
    }
}
