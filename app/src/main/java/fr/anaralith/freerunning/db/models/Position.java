package fr.anaralith.freerunning.db.models;

public class Position {

    private long id_position;
    private double latitude_position;
    private double longitude_position;

    public Position(){

    }

    public Position(double latitude_position, double longitude_position) {
        this.latitude_position = latitude_position;
        this.longitude_position = longitude_position;
    }

    //--- Getter
    public long getId_position() {return id_position;}
    public double getLatitude_position() {return latitude_position;}
    public double getLongitude_position() {return longitude_position;}

    //--- Setter
    public void setId_position(long id_position) {this.id_position = id_position;}
    public void setLatitude_position(double latitude_position) {this.latitude_position = latitude_position;}
    public void setLongitude_position(double longitude_position) {this.longitude_position = longitude_position;}
}
