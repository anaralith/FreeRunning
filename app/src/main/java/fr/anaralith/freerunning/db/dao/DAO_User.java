package fr.anaralith.freerunning.db.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.anaralith.freerunning.db.models.User;

public class DAO_User extends DAO {

    private final static String TABLE_NAME = "user";
    private final static String ID = "id";
    private final static String PSEUDO = "pseudo";
    private final static String MAIL = "mail";

    public DAO_User(Context context) {
        super(context);
    }

    /**
     * @param u the user to add in db
     */
    public void addUser(User u){
        ContentValues value = new ContentValues();
        value.put(PSEUDO, u.getPseudo_user());
        value.put(MAIL, u.getMail_user());
        db.insert(TABLE_NAME, null, value); //retour long = numéro de ligne
    }

    /**
     * @param id the id of the user to delete
     */
    public void deleteUser(long id){
        db.delete(TABLE_NAME, ID + "= ?",new String[]{String.valueOf(id)}); //retour in = nombre de ligne affectées
    }

    /**
     * @param u the modified user to update
     */
    public void updateUser(User u){
        ContentValues value = new ContentValues();
        value.put(PSEUDO, u.getPseudo_user());
        value.put(MAIL, u.getMail_user());
        db.update(TABLE_NAME, value, ID + "= ?",new String[]{String.valueOf(u.getId_user())});
    }

    /**
     * @param id the id of the user to search
     * @return the user find
     */
    public User getUser(long id){
        User dbUser = null;
        Cursor c = db.rawQuery("SELECT " + PSEUDO + "," + MAIL + "FROM " + TABLE_NAME + " WHERE " + ID + "= ?", new String[]{String.valueOf(id)});

        while(c.moveToNext()){
            dbUser = new User(id, c.getString(0), c.getString(1));
        }
        c.close();

        return dbUser;
    }
}
