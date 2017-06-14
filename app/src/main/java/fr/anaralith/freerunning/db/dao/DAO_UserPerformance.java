package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.anaralith.freerunning.db.models.User_performance;

public class DAO_UserPerformance extends DAO {
    public DAO_UserPerformance(Context context) {
        super(context);
    }

    private final static String TABLE_NAME = "user_performance";
    private final static String ID = "id_userPerf";
    private final static String ID_USER = "id_user_userPerf";
    private final static String ID_PERFORMANCE = "id_performance_userPerf";

    /**
     * @param p the Performance to add in db
     */
    public void addUserPerformance(User_performance p){
        ContentValues value = new ContentValues();
        value.put(ID_USER, p.getId_user_userPerf());
        value.put(ID_PERFORMANCE, p.getId_performance_userPerf());
        db.insert(TABLE_NAME, null, value); //retour long = numéro de ligne
    }

    /**
     * @param id the id of the Performance to delete
     */
    public void deleteUserPerformance(long id){
        db.delete(TABLE_NAME, ID + "= ?",new String[]{String.valueOf(id)}); //retour in = nombre de ligne affectées
    }

    /**
     * @param p the modified Performance to update
     */
    public void updateUserPerformance(User_performance p){
        ContentValues value = new ContentValues();
        value.put(ID_USER, p.getId_user_userPerf());
        value.put(ID_PERFORMANCE, p.getId_performance_userPerf());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId_userPerf())});
    }

    /**
     * @param id the id of the Performance to search
     * @return the Performance find
     */
    public User_performance getUserPerformance(long id){
        User_performance dbUser_performance = null;
        Cursor c = db.rawQuery("SELECT " + ID_USER + ","
                + ID_PERFORMANCE + "FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            int id_position = c.getInt(0);
            int id_performance = c.getInt(1);

            dbUser_performance = new User_performance(id, id_position, id_performance);
        }
        c.close();

        return dbUser_performance;
    }
}
