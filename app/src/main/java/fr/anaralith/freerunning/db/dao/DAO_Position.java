package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.anaralith.freerunning.db.models.Position;

public class DAO_Position extends DAO {

    private final static String TABLE_NAME = "position";
    private final static String ID = "id";
    private final static String LATITUDE = "latitude";
    private final static String LONGITUDE = "longitude";

    public DAO_Position(Context context) {
        super(context);
    }

    /**
     * @param p the position to add in db
     */
    public void addPosition(Position p){
        ContentValues value = new ContentValues();
        value.put(LATITUDE, p.getLattitude_position());
        value.put(LONGITUDE, p.getLongitude_position());
        db.insert(TABLE_NAME, null, value); //retour long = numéro de ligne
    }

    /**
     * @param id the id of the position to delete
     */
    public void deletePosition(long id){
        db.delete(TABLE_NAME, ID + "= ?",new String[]{String.valueOf(id)}); //retour in = nombre de ligne affectées
    }

    /**
     * @param p the modified position to update
     */
    public void updatePosition(Position p){
        ContentValues value = new ContentValues();
        value.put(LATITUDE, p.getLattitude_position());
        value.put(LONGITUDE, p.getLongitude_position());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId_position())});
    }

    /**
     * @param id the id of the position to search
     * @return the position find
     */
    public Position getPosition(long id){
        Position dbPosition = null;
        Cursor c = db.rawQuery("SELECT " + LATITUDE + "," + LONGITUDE + "FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            dbPosition = new Position(id, c.getInt(0), c.getInt(1));
        }
        c.close();

        return dbPosition;
    }
}
