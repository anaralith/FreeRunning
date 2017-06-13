package fr.anaralith.freerunning.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.anaralith.freerunning.db.DatabaseHandler;

//Permet d'ouvrir, fermer ou recupérer la db
public abstract class DAO {
    //Version de la DB, à incrémenter si mise à jour de la db
    protected final static int VERSION_DB = 1;
    //Nom du fichier représentant la db
    protected  final static String NAME_DB = "FreeRunningDB.db";

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
