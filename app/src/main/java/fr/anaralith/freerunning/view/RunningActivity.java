package fr.anaralith.freerunning.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.anaralith.freerunning.R;
import fr.anaralith.freerunning.metier.DataLocationGPS;

public class RunningActivity extends Activity {
    private DataLocationGPS dlGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        dlGPS = new DataLocationGPS(this);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlGPS.enableActivity("Parcours");
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
