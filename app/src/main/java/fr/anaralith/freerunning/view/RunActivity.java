package fr.anaralith.freerunning.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.db.models.Performance;

import static fr.anaralith.freerunning.metier.GPSService.RAPPORT;
import static fr.anaralith.freerunning.util.FormatPerfUtil.formatDate;
import static fr.anaralith.freerunning.util.FormatPerfUtil.formatDistance;
import static fr.anaralith.freerunning.util.FormatPerfUtil.formatRythme;

public class RunActivity extends AppCompatActivity {
    private TextView tv_distance = null;
    private TextView tv_deniv_negatif = null;
    private TextView tv_deniv_positif = null;
    private TextView tv_rythme = null;
    private TextView tv_temps = null;
    private TextView tv_vitesse = null;
    private TextView tv_date = null;

    private BottomNavigationView navigation = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_activity:
                    Log.i("DevApp", "Activité");
                    Intent intent = new Intent(RunActivity.this, RunningActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_profil:
                    Log.i("DevApp", "Profil");
                    return true;
                case R.id.navigation_history:
                    Log.i("DevApp", "Historique");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        tv_distance = (TextView) findViewById(R.id.tv_display_distance);
        tv_deniv_negatif = (TextView) findViewById(R.id.tv_display_deniv_negatif);
        tv_deniv_positif = (TextView) findViewById(R.id.tv_display_deniv_positif);
        tv_rythme = (TextView) findViewById(R.id.tv_display_rythme);
        tv_temps = (TextView) findViewById(R.id.tv_display_temps);
        tv_vitesse = (TextView) findViewById(R.id.tv_display_vitesse);
        tv_date = (TextView) findViewById(R.id.tv_display_date);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Performance perf = getIntent().getExtras().getParcelable(RAPPORT);
        tv_date.setText(formatDate(perf.getDate_perf()));
        tv_distance.setText(formatDistance(perf.getDistance_perf()));
        tv_deniv_negatif.setText(String.valueOf(perf.getDenivele_negatif_perf()) + " m");
        tv_deniv_positif.setText(String.valueOf(perf.getDenivele_positif_perf()) + " m");
        tv_rythme.setText(formatRythme(perf.getRythmeMoyen_perf()));
        tv_temps.setText(String.valueOf(perf.getTemps_perf()) + " s");
        tv_vitesse.setText(String.valueOf(perf.getVitesseMoyenne_perf()) + " Km/h");
    }

    @Override
    protected void onResume() {
        navigation.setSelectedItemId(R.id.navigation_history);
        super.onResume();
    }
}