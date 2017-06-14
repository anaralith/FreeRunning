package fr.anaralith.freerunning.db.models;

public class Performance {

    private long id_perf;
    private double distance_perf;
    private double rythmeMoyen_perf;
    private double vitesseMoyenne_perf;
    private int temps_perf;
    private double denivele_perf;
    private String date_perf;

    public Performance(){

    }

    public Performance(long id_perf, double distance_perf, double rythmeMoyen_perf, double vitesseMoyenne_perf, int temps_perf, double denivele_perf, String date_perf) {
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
    public double getDistance_perf() {return distance_perf;}
    public double getRythmeMoyen_perf() {return rythmeMoyen_perf;}
    public double getVitesseMoyenne_perf() {return vitesseMoyenne_perf;}
    public int getTemps_perf() {return temps_perf;}
    public double getDenivele_perf() {return denivele_perf;}
    public String getDate_perf() {return date_perf;}

    //--- Setter
    public void setId_perf(long id_perf) {this.id_perf = id_perf;}
    public void setDistance_perf(double distance_perf) {this.distance_perf = distance_perf;}
    public void setRythmeMoyen_perf(double rythmeMoyen_perf) {this.rythmeMoyen_perf = rythmeMoyen_perf;}
    public void setVitesseMoyenne_perf(double vitesseMoyenne_perf) {this.vitesseMoyenne_perf = vitesseMoyenne_perf;}
    public void setTemps_perf(int temps_perf) {this.temps_perf = temps_perf;}
    public void setDenivele_perf(double denivele_perf) {this.denivele_perf = denivele_perf;}
    public void setDate_perf(String date_perf) {this.date_perf = date_perf;}
}
