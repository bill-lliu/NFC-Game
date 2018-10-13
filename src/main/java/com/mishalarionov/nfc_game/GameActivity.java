package com.mishalarionov.nfc_game;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.nfc.NdefRecord.createMime;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, NfcAdapter.CreateNdefMessageCallback {

    private EditText beamText;
    private TextView incomingText;
    private Button beamButton;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Make sure the user is authenticated
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Kick out unauthenticated users
        if (user == null) {
            //Start MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //Find the appropriate buttons
        beamText = findViewById(R.id.beamText);
        beamButton = findViewById(R.id.beamButton); //This is just for testing rn, todo replace

        //Initialize the NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        nfcAdapter.setNdefPushMessageCallback(this, this);

        beamButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.beamButton) {
            String sendText = String.valueOf(beamText.getText());

        }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String sendText = String.valueOf(beamText.getText());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[]{
                        createMime("text/plain", sendText.getBytes())
                        //,NdefRecord.createApplicationRecord("com.mishalarionov.nfc_game")
                }
        );
        if (sendText.length() == 0) { //Check if we don't have anything to beam
            return null;
        }
        return msg;
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
