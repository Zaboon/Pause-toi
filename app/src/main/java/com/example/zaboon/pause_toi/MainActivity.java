package com.example.zaboon.pause_toi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Zaboon on 9/8/2015.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent          intent = new Intent(MainActivity.this, MapsActivity.class);
        Button          enjoy = (Button) findViewById(R.id.enjoy);

        enjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

                if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    buildAlertMessageNoGps();
                }
                switch (am.getRingerMode()) {
                    case AudioManager.RINGER_MODE_SILENT:
                        startActivity(intent);
                        break;
                    case AudioManager.RINGER_MODE_VIBRATE:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("WARNING !")
                                .setMessage("Did you desactivate notifications ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS));
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        Log.i("MyApp", "Vibrate mode");
                        break;
                    case AudioManager.RINGER_MODE_NORMAL:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("WARNING !")
                                .setMessage("Did you desactivate notifications ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS));
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                       dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        Log.i("MyApp","Normal mode");
                        break;
                }
            }
            private void buildAlertMessageNoGps() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}