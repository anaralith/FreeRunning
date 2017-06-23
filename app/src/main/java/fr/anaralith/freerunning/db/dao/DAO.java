package fr.anaralith.freerunning.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import fr.anaralith.freerunning.db.DatabaseHandler;

//Permet d'ouvrir, fermer ou recupérer la db
public abstract class DAO {
    //Version de la DB, à incrémenter si mise à jour de la db
    protected final static int VERSION_DB = 9;
    //Nom du fichier représentant la db
    protected  final static String NAME_DB = "FreeRunningDB.db";

    protected final static String LAST_AI_ID = "SELECT last_insert_rowid()";

    protected DatabaseHandler handler = null;
    protected SQLiteDatabase db = null;

    public DAO(Context context){
        this.handler = new DatabaseHandler(context, NAME_DB, null, VERSION_DB);
    }

    public SQLiteDatabase open(){
        db = handler.getWritableDatabase();
        return db;
    }

    public void close(){
        db.close();
    }
}
