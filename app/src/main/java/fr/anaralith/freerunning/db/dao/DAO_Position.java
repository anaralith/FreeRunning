package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.anaralith.freerunning.db.models.Position;

public class DAO_Position extends DAO {

    private final static String TABLE_NAME = "position";
    private final static String ID = "id_position";
    private final static String LATITUDE = "latitude_position";
    private final static String LONGITUDE = "longitude_position";
    private final static String SELECT = "SELECT " + LATITUDE + ","
            + LONGITUDE + " FROM "
            + TABLE_NAME;

    public DAO_Position(Context context) {
        super(context);
    }

    /**
     * @param p the position to add in db
     */
    public void addPosition(Position p){
        ContentValues value = new ContentValues();
        value.put(LATITUDE, p.getLatitude_position());
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
        value.put(LATITUDE, p.getLatitude_position());
        value.put(LONGITUDE, p.getLongitude_position());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId_position())});
    }

    /**
     * @param id the id of the position to search
     * @return the position find
     */
    public Position getPosition(long id){
        Position dbPosition = null;
        Cursor c = db.rawQuery("SELECT " + LATITUDE + "," + LONGITUDE + " FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            dbPosition = new Position(c.getDouble(0), c.getDouble(1));
            dbPosition.setId_position(id);
        }
        c.close();

        return dbPosition;
    }

    public List<Position> getAllPosition(){
        List<Position> listPosition = new ArrayList<>();
        Cursor c = db.rawQuery(SELECT, new String[]{});

        while(c.moveToNext()){
            listPosition.add(new Position(c.getDouble(0), c.getDouble(1)));
        }
        c.close();

        return listPosition;
    }
}
