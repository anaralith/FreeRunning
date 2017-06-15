package fr.anaralith.freerunning.metier;

import android.location.Location;
import android.location.LocationManager;

import java.util.List;

import fr.anaralith.freerunning.db.dao.DAO_Parcours;
import fr.anaralith.freerunning.db.dao.DAO_Performance;
import fr.anaralith.freerunning.db.dao.DAO_Position;
import fr.anaralith.freerunning.db.dao.DAO_User;
import fr.anaralith.freerunning.db.dao.DAO_UserPerformance;
import fr.anaralith.freerunning.db.models.Performance;
import fr.anaralith.freerunning.db.models.Position;

public class RunningDataProcess {
    private DAO_Parcours dbParcours = null;
    private DAO_Position dbPosition = null;
    private DAO_Performance dbPerformance = null;
    private DAO_User dbUser = null;
    private DAO_UserPerformance dbUserPerformance = null;

    private Performance performance = null;

    private double temps;
    private String date;

    public RunningDataProcess(double temps, String date) {
        this.performance = new Performance();
        this.temps = temps;
        this.date = date;
    }

    //Calcul Distance
    public float calcDistance(){
        float distance = 0f;
        List<Position> listPosition = null;
        Location startPoint = new Location(LocationManager.GPS_PROVIDER);
        Location endPoint = new Location(LocationManager.GPS_PROVIDER);

        try {
            dbPosition.open();
            listPosition = dbPosition.getPositionByIdParcours(0);

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

            performance.setDistance_perf(distance);
        }

        return distance;
    }
    //Calcul Rythme Moyen
    //Calcul Vitesse Moyenne
    //Calcul Dénivelé
}
