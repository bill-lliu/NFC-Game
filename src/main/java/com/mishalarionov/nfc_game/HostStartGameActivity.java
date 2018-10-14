package com.mishalarionov.nfc_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class HostStartGameActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);

        //buttons
        findViewById(R.id.joinButton).setOnClickListener(this);
        findViewById(R.id.hostButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    protected void onClick(View v){

    }
}
