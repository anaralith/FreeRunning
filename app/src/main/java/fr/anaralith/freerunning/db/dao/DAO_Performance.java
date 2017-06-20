package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.anaralith.freerunning.db.models.Performance;

public class DAO_Performance extends DAO {

    private final static String TABLE_NAME = "performance";
    private final static String ID = "id_perf";
    private final static String DISTANCE = "distance_perf";
    private final static String RYTHME = "rythmeMoyen_perf";
    private final static String VITESSE = "vitesseMoyenne_perf";
    private final static String TEMPS = "temps_perf";
    private final static String DENIVELE_POSITIF = "denivele_positif_perf";
    private final static String DENIVELE_NEGATIF = "denivele_negatif_perf";
    private final static String DATE = "date_perf";

    public DAO_Performance(Context context) {
        super(context);
    }

    /**
     * @param p the Performance to add in db
     */
    public int addPerformance(Performance p){
        ContentValues value = new ContentValues();
        value.put(DISTANCE, p.getDistance_perf());
        value.put(RYTHME, p.getRythmeMoyen_perf());
        value.put(VITESSE, p.getVitesseMoyenne_perf());
        value.put(TEMPS, p.getTemps_perf());
        value.put(DENIVELE_POSITIF, p.getDenivele_negatif_perf());
        value.put(DENIVELE_NEGATIF, p.getDenivele_positif_perf());
        value.put(DATE, p.getDate_perf());
        db.insert(TABLE_NAME, null, value); //retour long = numéro de ligne

        Cursor c = db.rawQuery(LAST_AI_ID, new String[]{});
        while(c.moveToNext()){
            return c.getInt(0);
        }

        return 0;
    }

    /**
     * @param id the id of the Performance to delete
     */
    public void deletePerformance(long id){
        db.delete(TABLE_NAME, ID + "= ?",new String[]{String.valueOf(id)}); //retour in = nombre de ligne affectées
    }

    /**
     * @param p the modified Performance to update
     */
    public void updatePerformance(Performance p){
        ContentValues value = new ContentValues();
        value.put(DISTANCE, p.getDistance_perf());
        value.put(RYTHME, p.getRythmeMoyen_perf());
        value.put(VITESSE, p.getVitesseMoyenne_perf());
        value.put(TEMPS, p.getTemps_perf());
        value.put(DENIVELE_POSITIF, p.getDenivele_negatif_perf());
        value.put(DENIVELE_NEGATIF, p.getDenivele_positif_perf());
        value.put(DATE, p.getDate_perf());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId_perf())});
    }

    /**
     * @param id the id of the Performance to search
     * @return the Performance find
     */
    public Performance getPerformance(long id){
        Performance dbPerformance = null;
        Cursor c = db.rawQuery("SELECT " + DISTANCE + ","
                + RYTHME + ","
                + VITESSE + ","
                + TEMPS + ","
                + DENIVELE_POSITIF + ","
                + DENIVELE_NEGATIF + ","
                + DATE + ","
                + "FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            int distance = c.getInt(0);
            int rythme = c.getInt(1);
            int vitesse = c.getInt(2);
            int temps = c.getInt(3);
            int denivele_positif = c.getInt(4);
            int denivele_negatif = c.getInt(5);
            String date = c.getString(6);

            dbPerformance = new Performance(id, distance, rythme, vitesse, temps, denivele_positif, denivele_negatif, date);
        }
        c.close();

        return dbPerformance;
    }
}
