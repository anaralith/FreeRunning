package fr.anaralith.freerunning.db.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Performance implements Parcelable{

    private long id_perf;
    private double distance_perf;
    private double rythmeMoyen_perf;
    private double vitesseMoyenne_perf;
    private long temps_perf;
    private double denivele_positif_perf;
    private double denivele_negatif_perf;
    private String date_perf;

    public Performance(){

    }

    public Performance(long id_perf, double distance_perf, double rythmeMoyen_perf, double vitesseMoyenne_perf, long temps_perf, double denivele_positif_perf, double denivele_negatif_perf, String date_perf) {
        this.id_perf = id_perf;
        this.distance_perf = distance_perf;
        this.rythmeMoyen_perf = rythmeMoyen_perf;
        this.vitesseMoyenne_perf = vitesseMoyenne_perf;
        this.temps_perf = temps_perf;
        this.denivele_positif_perf = denivele_positif_perf;
        this.denivele_negatif_perf = denivele_negatif_perf;
        this.date_perf = date_perf;
    }
    //--- Getter
    public long getId_perf() {return id_perf;}
    public double getDistance_perf() {return distance_perf;}
    public double getRythmeMoyen_perf() {return rythmeMoyen_perf;}
    public double getVitesseMoyenne_perf() {return vitesseMoyenne_perf;}
    public long getTemps_perf() {return temps_perf;}
    public String getDate_perf() {return date_perf;}
    public double getDenivele_positif_perf() {return denivele_positif_perf;}
    public double getDenivele_negatif_perf() {return denivele_negatif_perf;}

    //--- Setter
    public void setId_perf(long id_perf) {this.id_perf = id_perf;}
    public void setDistance_perf(double distance_perf) {this.distance_perf = distance_perf;}
    public void setRythmeMoyen_perf(double rythmeMoyen_perf) {this.rythmeMoyen_perf = rythmeMoyen_perf;}
    public void setVitesseMoyenne_perf(double vitesseMoyenne_perf) {this.vitesseMoyenne_perf = vitesseMoyenne_perf;}
    public void setTemps_perf(long temps_perf) {this.temps_perf = temps_perf;}
    public void setDate_perf(String date_perf) {this.date_perf = date_perf;}
    public void setDenivele_positif_perf(double denivele_positif_perf) {this.denivele_positif_perf = denivele_positif_perf;}
    public void setDenivele_negatif_perf(double denivele_negatif_perf) {this.denivele_negatif_perf = denivele_negatif_perf;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id_perf);
        dest.writeLong(temps_perf);
        dest.writeDouble(rythmeMoyen_perf);
        dest.writeDouble(vitesseMoyenne_perf);
        dest.writeDouble(distance_perf);
        dest.writeDouble(denivele_positif_perf);
        dest.writeDouble(denivele_negatif_perf);
        dest.writeString(date_perf);
    }

    public static final Parcelable.Creator<Performance> CREATOR = new Parcelable.Creator<Performance>()
    {
        @Override
        public Performance createFromParcel(Parcel source)
        {
            return new Performance(source);
        }

        @Override
        public Performance[] newArray(int size)
        {
            return new Performance[size];
        }
    };

    public Performance(Parcel in) {
        this.id_perf = in.readLong();
        this.temps_perf = in.readLong();
        this.rythmeMoyen_perf = in.readDouble();
        this.vitesseMoyenne_perf = in.readDouble();
        this.distance_perf = in.readDouble();
        this.denivele_positif_perf = in.readDouble();
        this.denivele_negatif_perf = in.readDouble();
        this.date_perf = in.readString();
    }
}