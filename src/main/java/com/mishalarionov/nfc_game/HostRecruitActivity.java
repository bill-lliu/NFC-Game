package com.mishalarionov.nfc_game;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.nfc.NdefRecord.createMime;

public class HostRecruitActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback {

    Map<String, Object> game;
    FirebaseDatabase database;
    NfcAdapter nfcAdapter;
    String gameID;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_host_game);

        //Make sure the user is authenticated
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("What", currentUser.getEmail());

        //Kick out unauthenticated users
        if (currentUser == null) {
            //Start LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        gameID = "game" + Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        //Set up the Firebase Database
        database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference(gameID);

        game = new HashMap<>();
        game.put("owner", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        game.put("players", new ArrayList<String>());

        //Initialize the NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        nfcAdapter.setNdefPushMessageCallback(this, this);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        TextView incomingText = new TextView(this);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        incomingText.setText(new String(msg.getRecords()[0].getPayload()));
        LinearLayout hostLinearLayout = findViewById(R.id.hostLinearLayout);
        hostLinearLayout.addView(incomingText);


    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        NdefMessage msg = new NdefMessage(
                new NdefRecord[]{
                        createMime("text/plain", gameID.getBytes())
                        //,NdefRecord.createApplicationRecord("com.mishalarionov.nfc_game")
                }
        );
        if (gameID.length() == 0) { //Check if we don't have anything to beam
            return null;
        }
        return msg;
    }
}
