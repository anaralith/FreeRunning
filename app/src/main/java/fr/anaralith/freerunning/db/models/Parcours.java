package fr.anaralith.freerunning.db.models;

public class Parcours {

    private long id_parcours;
    private long id_performance_parcours;
    private String name_parcours;


    public Parcours(String name_parcours) {
        this.name_parcours = name_parcours;
    }

    //--- Getter
    public long getId() {return id_parcours;}
    public long getId_performance() {return id_performance_parcours;}
    public String getName() {return name_parcours;}

    //--- Setter
    public void setId(long id_parcours) {this.id_parcours = id_parcours;}
    public void setId_performance(long id_performance) {this.id_performance_parcours = id_performance;}
    public void setName(String name_parcours) {this.name_parcours = name_parcours;}
}
