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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "EmailPassword";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        //Make sure the user starts signed out
        mAuth.signOut();

        //Check if the user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //views
        mEmailField = findViewById(R.id.emailEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
        //buttons
        findViewById(R.id.joinSignInButton).setOnClickListener(this);
        findViewById(R.id.hostSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        //updateUI(currentUser);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    protected void updateUI(FirebaseUser user){
        final TextView textView = findViewById(R.id.profile);
    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                             Toast.makeText(MainActivity.this, "Authentication failed.", //*************NO ONE NEEDS A TOAST MATE ************** jk its just a notifcation panel but we dont know what enclosing classes are oop
                                Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                       // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    protected void signIn(String email, String password, final Intent intent){
        if (!email.equals("") && !password.equals("")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Toast.makeText(MainActivity.this,
                                        "Signed in as " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.", //*************NO ONE NEEDS A TOAST MATE ************** jk its just a notifcation panel but we dont know what enclosing classes are oop
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    protected FirebaseUser getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }

    protected boolean validateForm(){
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.joinSignInButton) {
            //Make sure the sign in is successful, then launch game as client
            Intent intent = new Intent(this, GameActivity.class);
            //intent.putExtra("", ""); //might also go here
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString(), intent);
        } else if (i == R.id.hostSignInButton) {
            //Make sure the sign in is successful, then launch game as host
            Intent intent = new Intent(this, HostGameActivity.class);
            //intent.putExtra("", ""); //might also go here
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString(), intent);
        }
//        else if (i == R.id.verifyEmailButton) {
//            sendEmailVerification();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Intent: ", String.valueOf(getIntent()));
    }

    private void signOut() {
        mAuth.signOut();
        Toast.makeText(MainActivity.this,
                "Signed out",
                Toast.LENGTH_SHORT).show();
        updateUI(null);
    }
}




