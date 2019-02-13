package com.mishalarionov.nfc_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OpenScreen extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "EmailPassword";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Test", "test");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        //buttons
        findViewById(R.id.joinButton).setOnClickListener(this);
        findViewById(R.id.hostButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.joinButton) {
            new Intent(this, HostRecruitActivity.class);
        } else if (i == R.id.hostButton) {
            //Make sure the sign in is successful, then launch game as client
            Intent intent = new Intent(this, HostStartGameActivity.class);
            //intent.putExtra("", ""); //might also go here
            }
    }

}




