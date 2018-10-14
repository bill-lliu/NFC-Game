package com.mishalarionov.nfc_game;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GameActivity extends AppCompatActivity { //implements NfcAdapter.CreateNdefMessageCallback {

    private EditText beamText;
    private TextView incomingText;
    private NfcAdapter nfcAdapter;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //Make sure the user is authenticated
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("What", currentUser.getEmail());

        //Kick out unauthenticated users
        if (currentUser == null) {
            //Start LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        //Find the appropriate buttons
        beamText = findViewById(R.id.beamText);

//        //Initialize the NFC adapter
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//
//        nfcAdapter.setNdefPushMessageCallback(this, this);


    }

//    @Override
//    public NdefMessage createNdefMessage(NfcEvent event) {
//        String sendText = String.valueOf(beamText.getText());
//        NdefMessage msg = new NdefMessage(
//                new NdefRecord[]{
//                        createMime("text/plain", sendText.getBytes())
//                        //,NdefRecord.createApplicationRecord("com.mishalarionov.nfc_game")
//                }
//        );
//        if (sendText.length() == 0) { //Check if we don't have anything to beam
//            return null;
//        }
//        return msg;
//    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        incomingText = findViewById(R.id.incomingText);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        incomingText.setText(new String(msg.getRecords()[0].getPayload()));

    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

}
