package fr.anaralith.freerunning.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String POSITION_TABLE_NAME = "position";
    private static final String POSITION_KEY = "id_position";
    private static final String POSITION_LATITUDE = "latitude_position";
    private static final String POSITION_LONGITUDE = "longitude_position";

    private static final String POSITION_TABLE_CREATE =
            "CREATE TABLE " + POSITION_TABLE_NAME + " (" +
                    POSITION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    POSITION_LATITUDE + " REAL, " +
                    POSITION_LONGITUDE + " REAL);";

    private static final String POSITION_TABLE_DROP = "DROP TABLE IF EXISTS " + POSITION_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POSITION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(POSITION_TABLE_DROP);
        onCreate(db);
    }
}
