package fr.anaralith.freerunning.db.models;

public class User_performance {

    private long id_userPerf;
    private long id_user_userPerf;
    private long id_performance_userPerf;

    public User_performance(long id_userPerf, long id_user_userPerf, long id_performance_userPerf) {
        this.id_userPerf = id_userPerf;
        this.id_user_userPerf = id_user_userPerf;
        this.id_performance_userPerf = id_performance_userPerf;
    }

    //--- Getter
    public long getId_userPerf() {return id_userPerf;}
    public long getId_user_userPerf() {return id_user_userPerf;}
    public long getId_performance_userPerf() {return id_performance_userPerf;}

    //--- Setter
    public void setId_userPerf(long id_userPerf) {this.id_userPerf = id_userPerf;}
    public void setId_user_userPerf(long id_user_userPerf) {this.id_user_userPerf = id_user_userPerf;}
    public void setId_performance_userPerf(long id_performance_userPerf) {this.id_performance_userPerf = id_performance_userPerf;}
}
