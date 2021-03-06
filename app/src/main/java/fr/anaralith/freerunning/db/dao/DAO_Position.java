package fr.anaralith.freerunning.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.anaralith.freerunning.db.models.Position;

public class DAO_Position extends DAO {

    private final static String TABLE_NAME = "position";
    private final static String ID = "id_position";
    private final static String ID_PARCOURS = "id_parcours";
    private final static String LATITUDE = "latitude_position";
    private final static String LONGITUDE = "longitude_position";
    private final static String DATE = "date_position";
    private final static String ALTTITUDE = "alttitude_position";

    private final static String SELECT = "SELECT " + LATITUDE + ", "
            + LONGITUDE + ", "
            + ALTTITUDE + ", "
            + DATE + " FROM "
            + TABLE_NAME;
    private final static String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

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
        value.put(ALTTITUDE, p.getAlttitude());
        value.put(DATE, p.getDate_position());
        value.put(ID_PARCOURS, p.getId_parcours());
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
        value.put(ALTTITUDE, p.getAlttitude());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(p.getId_position())});
    }

    public List<Position> getPositionByIdParcours(long id_parcours){
        List<Position> listPosition = new ArrayList<>();
        Cursor c = db.rawQuery(SELECT + " WHERE " + ID_PARCOURS + " = ?", new String[]{String.valueOf(id_parcours)});

        while(c.moveToNext()){
            double latitude = c.getDouble(0);
            double longitude = c.getDouble(1);
            double alttitude = c.getDouble(2);
            long date = c.getLong(3);

            listPosition.add(new Position(latitude, longitude, alttitude, date, id_parcours));
        }
        c.close();

        return listPosition;
    }

    public List<Position> getAllPosition(){
        List<Position> listPosition = new ArrayList<>();
        Cursor c = db.rawQuery(SELECT_ALL, new String[]{});

        while(c.moveToNext()){
            long key = c.getLong(0);
            double latitude = c.getDouble(1);
            double longitude = c.getDouble(2);
            double alttitude = c.getDouble(3);
            long id_parcours = c.getLong(4);
            long date = c.getLong(5);

            Position pos = new Position(latitude, longitude, alttitude, date, id_parcours);
            pos.setId_position(key);

            listPosition.add(pos);
        }
        c.close();

        return listPosition;
    }
}