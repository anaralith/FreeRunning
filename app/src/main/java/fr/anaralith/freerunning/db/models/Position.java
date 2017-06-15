package fr.anaralith.freerunning.db.models;

public class Position {

    private long id_position;
    private long id_parcours;
    private double latitude_position;
    private double longitude_position;
    String date_position;

    //--- Construct
    public Position(){

    }

    public Position(double latitude_position, double longitude_position, String date_position, long id_parcours) {
        this.latitude_position = latitude_position;
        this.longitude_position = longitude_position;
        this.date_position = date_position;
        this.id_parcours = id_parcours;
    }

    //--- Getter
    public long getId_position() {return id_position;}
    public double getLatitude_position() {return latitude_position;}
    public double getLongitude_position() {return longitude_position;}
    public long getId_parcours() {return id_parcours;}
    public String getDate_position() {return date_position;}

    //--- Setter
    public void setId_position(long id_position) {this.id_position = id_position;}
    public void setLatitude_position(double latitude_position) {this.latitude_position = latitude_position;}
    public void setLongitude_position(double longitude_position) {this.longitude_position = longitude_position;}
    public void setId_parcours(long id_parcours) {this.id_parcours = id_parcours;}
    public void setDate_position(String date_position) {this.date_position = date_position;}
}
