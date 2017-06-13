package fr.anaralith.freerunning.db.models;

public class Position {

    private long id_position;
    private int lattitude_position;
    private int longitude_position;

    public Position(long id_position, int lattitude_position, int longitude_position) {
        this.id_position = id_position;
        this.lattitude_position = lattitude_position;
        this.longitude_position = longitude_position;
    }

    //--- Getter
    public long getId_position() {return id_position;}
    public int getLattitude_position() {return lattitude_position;}
    public int getLongitude_position() {return longitude_position;}

    //--- Setter
    public void setId_position(long id_position) {this.id_position = id_position;}
    public void setLattitude_position(int lattitude_position) {this.lattitude_position = lattitude_position;}
    public void setLongitude_position(int longitude_position) {this.longitude_position = longitude_position;}
}
