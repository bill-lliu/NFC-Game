package com.mishalarionov.nfc_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class HostStartGameActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //buttons
        findViewById(R.id.hostStartButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.hostStartButton){
            Random r = new Random();
            String id = "game:"+currentUser.getDisplayName()+Integer.toString(r.nextInt());
            String[] questions = {findViewById(R.id.question1Field).qetText(),
                    findViewById(R.id.question2Field).qetText(),
                    findViewById(R.id.question3Field).qetText()};

            DatabaseReference database = FirebaseDatabase.getInstance().getReference("to create");
            database.child(id).setValue(questions);

        }
    }
}
