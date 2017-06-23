package fr.anaralith.freerunning.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.metier.DataLocationGPS;

public class RunningActivity extends Activity {
    private Chronometer chronometer;
    private DataLocationGPS dlGPS;

    private BottomNavigationView navigation = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_activity:
                    Log.i("DevApp", "Activité");
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
        setContentView(R.layout.activity_connexion);

        dlGPS = new DataLocationGPS(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        final Button stopBtn = (Button) findViewById(R.id.stopBtn);
        final Button startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn.setEnabled(false);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.startRunning("Parcours");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

                //Bloque le bouton start et la barre de navigation, active le bouton stop
                stopBtn.setEnabled(true);
                startBtn.setEnabled(false);
                navigation.setEnabled(false);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                dlGPS.endRunning(time);
                chronometer.stop();

                //Bloque le boutonn stop et réactive la barre de navigation et le bouton start
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);
                navigation.setEnabled(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_activity);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
