package fr.anaralith.freerunning.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String POSITION_TABLE_NAME = "position";
    private static final String POSITION_KEY = "id_position";
    private static final String POSITION_LATITUDE = "latitude_position";
    private static final String POSITION_LONGITUDE = "longitude_position";
    private static final String POSITION_DATE = "date_position";
    private static final String POSITION_PARCOURS = "id_parcours";
    private static final String POSITION_ALTTITUDE = "alttitude_position";

    private static final String PARCOURS_TABLE_NAME = "parcours";
    private static final String PARCOURS_KEY = "id_parcours";
    private static final String PARCOURS_ID_PERFORMANCE = "id_performance_parcours";
    private static final String PARCOURS_NAME = "name_parcours";

    private static final String PERFORMANCE_TABLE_NAME = "performance";
    private static final String PERFORMANCE_KEY = "id_perf";
    private static final String PERFORMANCE_DISTANCE = "distance_perf";
    private static final String PERFORMANCE_RYTHME = "rythmeMoyen_perf";
    private static final String PERFORMANCE_VITESSE = "vitesseMoyenne_perf";
    private static final String PERFORMANCE_TEMPS = "temps_perf";
    private static final String PERFORMANCE_DENIVELE_POSITIF = "denivele_positif_perf";
    private static final String PERFORMANCE_DENIVELE_NEGATIF = "denivele_negatif_perf";
    private static final String PERFORMANCE_DATE = "date_perf";

    private static final String POSITION_TABLE_CREATE =
            "CREATE TABLE " + POSITION_TABLE_NAME + " (" +
                    POSITION_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    POSITION_LATITUDE + " REAL NOT NULL, " +
                    POSITION_LONGITUDE + " REAL NOT NULL, " +
                    POSITION_ALTTITUDE + " REAL NOT NULL, " +
                    POSITION_PARCOURS + " INTEGER NOT NULL," +
                    POSITION_DATE + " TEXT NOT NULL" +
                    ");";
    private static final String PARCOURS_TABLE_CREATE =
            "CREATE TABLE " + PARCOURS_TABLE_NAME + " (" +
                    PARCOURS_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    PARCOURS_ID_PERFORMANCE + " INTEGER, " +
                    PARCOURS_NAME + " TEXT NOT NULL" +
                    ");";
    private static final String PERFORMANCE_TABLE_CREATE =
            "CREATE TABLE " + PERFORMANCE_TABLE_NAME + " (" +
                    PERFORMANCE_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    PERFORMANCE_DISTANCE + " REAL NOT NULL, " +
                    PERFORMANCE_RYTHME + " REAL NOT NULL, " +
                    PERFORMANCE_VITESSE + " REAL NOT NULL, " +
                    PERFORMANCE_TEMPS + " INTEGER NOT NULL," +
                    PERFORMANCE_DENIVELE_POSITIF + " REAL NOT NULL, " +
                    PERFORMANCE_DENIVELE_NEGATIF + " REAL NOT NULL, " +
                    PERFORMANCE_DATE + " TEXT NOT NULL" +
                    ");";

    private static final String POSITION_TABLE_DROP = "DROP TABLE IF EXISTS " + POSITION_TABLE_NAME + ";";
    private static final String PARCOURS_TABLE_DROP = "DROP TABLE IF EXISTS " + PARCOURS_TABLE_NAME + ";";
    private static final String PERFORMANCE_TABLE_DROP = "DROP TABLE IF EXISTS " + PERFORMANCE_TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POSITION_TABLE_CREATE);
        db.execSQL(PARCOURS_TABLE_CREATE);
        db.execSQL(PERFORMANCE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(POSITION_TABLE_DROP);
        db.execSQL(PARCOURS_TABLE_DROP);
        db.execSQL(PERFORMANCE_TABLE_DROP);
        onCreate(db);
    }
}
