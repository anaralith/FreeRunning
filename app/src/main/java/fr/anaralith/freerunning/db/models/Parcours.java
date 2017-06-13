package fr.anaralith.freerunning.db.models;

public class Parcours {

    private long id_parcours;
    private long id_position_parcours;
    private long id_performance_parcours;

    public Parcours(long id_parcours, long id_position_parcours, long id_performance_parcours) {
        this.id_parcours = id_parcours;
        this.id_position_parcours = id_position_parcours;
        this.id_performance_parcours = id_performance_parcours;
    }

    //--- Getter
    public long getId() {return id_parcours;}
    public long getId_position() {return id_position_parcours;}
    public long getId_performance() {return id_performance_parcours;}

    //--- Setter
    public void setId(long id_parcours) {this.id_parcours = id_parcours;}
    public void setId_position(long id_position) {this.id_position_parcours = id_position_parcours;}
    public void setId_performance(long id_performance) {this.id_performance_parcours = id_performance_parcours;}
}
