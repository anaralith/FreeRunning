package fr.anaralith.freerunning.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.metier.DataLocationGPS;

public class RunningActivity extends Activity {
    private Chronometer chronometer;
    private DataLocationGPS dlGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        dlGPS = new DataLocationGPS(this);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.startRunning("Parcours");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                dlGPS.endRunning(time);
                chronometer.stop();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
