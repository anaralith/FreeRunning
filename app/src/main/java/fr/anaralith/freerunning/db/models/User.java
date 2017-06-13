package fr.anaralith.freerunning.db.models;

public class User {

    private long id_user;
    private String pseudo_user;
    private String mail_user;

    public User(long id_user, String pseudo_user, String mail_user) {
        this.id_user = id_user;
        this.pseudo_user = pseudo_user;
        this.mail_user = mail_user;
    }

    //--- Getter
    public long getId_user() {return id_user;}
    public String getPseudo_user() {return pseudo_user;}
    public String getMail_user() {return mail_user;}

    //--- Setter
    public void setId_user(long id_user) {this.id_user = id_user;}
    public void setPseudo_user(String pseudo_user) {this.pseudo_user = pseudo_user;}
    public void setMail_user(String mail_user) {this.mail_user = mail_user;}
}
