package fr.anaralith.freerunning.view;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.metier.DataLocationGPS;
import fr.anaralith.freerunning.metier.GPSUpdateReceiver;
import fr.anaralith.freerunning.metier.RunningDataProcess;

public class RunningActivity extends Activity {

    private DataLocationGPS dlGPS;
    private GPSUpdateReceiver receiverGPS;
    private IntentFilter filtre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //filtre = new IntentFilter(GPS);
        //receiverGPS = new GPSUpdateReceiver();
        dlGPS = new DataLocationGPS(this);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                dlGPS.enableActivity("Parcours", c.getTime().toString());
            }
        });

        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.disableActivity();
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
