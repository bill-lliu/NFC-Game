package com.mishalarionov.nfc_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InitializeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intialize_game);
        final String question1;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/games/questions"); //WHAT IS OUR SERVER?
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question1
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        TextView question1 = findViewById(R.id.question1);
        question1.setText();
        //buttons
        findViewById(R.id.confirmButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    public void onClick(View v) {
        int i = v.getId();
    }


    ;
      mPostReference.addValueEventListener(postListener);

}
