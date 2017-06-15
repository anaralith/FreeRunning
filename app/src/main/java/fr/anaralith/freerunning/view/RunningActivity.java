package fr.anaralith.freerunning.view;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.db.DatabaseHandler;
import fr.anaralith.freerunning.metier.DataLocationGPS;
import fr.anaralith.freerunning.metier.GPSUpdateReceiver;
import fr.anaralith.freerunning.metier.RunningDataProcess;

public class RunningActivity extends Activity {

    private DataLocationGPS dlGPS;
    private GPSUpdateReceiver receiverGPS;
    private IntentFilter filtre;

    private static final String GPS = "fr.anaralith.freerunning.intent.gps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        filtre = new IntentFilter(GPS);
        receiverGPS = new GPSUpdateReceiver();
        dlGPS = new DataLocationGPS(this);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.enableActivity();
            }
        });

        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.disableActivity();
                Toast.makeText(RunningActivity.this, "Distance : " + new RunningDataProcess(0d, "").calcDistance(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(receiverGPS, filtre);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(receiverGPS);
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
