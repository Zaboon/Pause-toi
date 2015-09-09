package com.example.zaboon.pause_toi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Button          enjoy = (Button) findViewById(R.id.enjoy);

        enjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "DETECTION OPERATIONNAL BITCH", Toast.LENGTH_LONG).show();
            }
        });
    }
}