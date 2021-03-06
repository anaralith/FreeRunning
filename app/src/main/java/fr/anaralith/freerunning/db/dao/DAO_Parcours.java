package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.anaralith.freerunning.db.models.Parcours;

public class DAO_Parcours extends DAO {

    private final static String TABLE_NAME = "parcours";
    private final static String ID = "id_parcours";
    private final static String NAME = "name_parcours";
    private final static String ID_PERFORMANCE = "id_performance_parcours";

    public DAO_Parcours(Context context) {
        super(context);
    }

    /**
     * @param p the Performance to add in db
     */
    public int addParcours(Parcours p){
        ContentValues value = new ContentValues();
        value.put(NAME, p.getName());
        value.put(ID_PERFORMANCE, p.getId_performance());
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
    public void deleteParcours(long id){
        db.delete(TABLE_NAME, ID + "= ?",new String[]{String.valueOf(id)}); //retour in = nombre de ligne affectées
    }

    /**
     * @param p the modified Performance to update
     */
    public void updateParcours(Parcours p){
        ContentValues value = new ContentValues();
        value.put(NAME, p.getName());
        value.put(ID_PERFORMANCE, p.getId_performance());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId())});
    }

    /**
     * @param id the id of the Performance to search
     * @return the Performance find
     */
    public Parcours getParcours(long id){
        Parcours dbParcours = null;
        Cursor c = db.rawQuery("SELECT " + NAME + ","
                + ID_PERFORMANCE + " FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            String name = c.getString(0);
            int id_performance = c.getInt(1);

            dbParcours = new Parcours(name);
            dbParcours.setId(id);
            dbParcours.setId_performance(id_performance);
        }
        c.close();

        return dbParcours;
    }
}
