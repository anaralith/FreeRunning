package fr.anaralith.freerunning.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String POSITION_TABLE_NAME = "position";
    private static final String POSITION_KEY = "id_position";
    private static final String POSITION_LATITUDE = "latitude_position";
    private static final String POSITION_LONGITUDE = "longitude_position";

    private static final String PARCOURS_TABLE_NAME = "parcours";
    private static final String PARCOURS_KEY = "id_parcours";
    private static final String PARCOURS_ID_PERFORMANCE = "id_performance_parcours";
    private static final String PARCOURS_NAME = "name_parcours";

    private static final String POSITION_TABLE_CREATE =
            "CREATE TABLE " + POSITION_TABLE_NAME + " (" +
                    POSITION_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    POSITION_LATITUDE + " REAL NOT NULL, " +
                    POSITION_LONGITUDE + " REAL NOT NULL);";
    private static final String PARCOURS_TABLE_CREATE =
            "CREATE TABLE " + PARCOURS_TABLE_NAME + " (" +
                    PARCOURS_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    PARCOURS_ID_PERFORMANCE + " INTEGER, " +
                    PARCOURS_NAME + " TEXT NOT NULL);";

    private static final String POSITION_TABLE_DROP = "DROP TABLE IF EXISTS " + POSITION_TABLE_NAME + ";";
    private static final String PARCOURS_TABLE_DROP = "DROP TABLE IF EXISTS " + PARCOURS_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POSITION_TABLE_CREATE);
        db.execSQL(PARCOURS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(POSITION_TABLE_DROP);
        db.execSQL(PARCOURS_TABLE_DROP);
        onCreate(db);
    }
}
