package com.mishalarionov.nfc_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostStartGameActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);

        //buttons
        findViewById(R.id.hostStartButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.hostStartButton){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("https://nfc-game-933e3.firebaseio.com/");
            myRef.setValue(findViewById(R.id.question1Field).qetText());
            myRef = database.getReference("Question 2");
            myRef.setValue(findViewById(R.id.question2Field).getText());
            myRef = database.getReference("Question 3");
            myRef.setValue(findViewById(R.id.question3Field).getText());
        }
    }
}
