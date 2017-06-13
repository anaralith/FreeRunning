package fr.anaralith.freerunning.db.models;

public class Performance {

    private long id_perf;
    private int distance_perf;
    private int rythmeMoyen_perf;
    private int vitesseMoyenne_perf;
    private int temps_perf;
    private int denivele_perf;
    private String date_perf;

    public Performance(long id_perf, int distance_perf, int rythmeMoyen_perf, int vitesseMoyenne_perf, int temps_perf, int denivele_perf, String date_perf) {
        this.id_perf = id_perf;
        this.distance_perf = distance_perf;
        this.rythmeMoyen_perf = rythmeMoyen_perf;
        this.vitesseMoyenne_perf = vitesseMoyenne_perf;
        this.temps_perf = temps_perf;
        this.denivele_perf = denivele_perf;
        this.date_perf = date_perf;
    }

    //--- Getter
    public long getId_perf() {return id_perf;}
    public int getDistance_perf() {return distance_perf;}
    public int getRythmeMoyen_perf() {return rythmeMoyen_perf;}
    public int getVitesseMoyenne_perf() {return vitesseMoyenne_perf;}
    public int getTemps_perf() {return temps_perf;}
    public int getDenivele_perf() {return denivele_perf;}
    public String getDate_perf() {return date_perf;}

    //--- Setter
    public void setId_perf(long id_perf) {this.id_perf = id_perf;}
    public void setDistance_perf(int distance_perf) {this.distance_perf = distance_perf;}
    public void setRythmeMoyen_perf(int rythmeMoyen_perf) {this.rythmeMoyen_perf = rythmeMoyen_perf;}
    public void setVitesseMoyenne_perf(int vitesseMoyenne_perf) {this.vitesseMoyenne_perf = vitesseMoyenne_perf;}
    public void setTemps_perf(int temps_perf) {this.temps_perf = temps_perf;}
    public void setDenivele_perf(int denivele_perf) {this.denivele_perf = denivele_perf;}
    public void setDate_perf(String date_perf) {this.date_perf = date_perf;}
}
