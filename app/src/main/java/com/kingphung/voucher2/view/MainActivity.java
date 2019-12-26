package com.kingphung.voucher2.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kingphung.voucher2.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {


    //UI
    Button btLogin;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //instants
    private int RC_LOGIN = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            openMapActivity();
        }else{
            updateUILogin();
            btLogin.setOnClickListener(this);

        }
    }

    private void updateUILogin() {
        btLogin = findViewById(R.id.btLogin);
    }

    private void openMapActivity() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                handleLogin();
        }
    }

    private void handleLogin() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build()
                                )
                        )
                        .build(), RC_LOGIN
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOGIN && data!= null){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                openMapActivity();
            }else{
                Log.d("KingPhung", response.getError()+"");
                Toast.makeText(this, "error: " + resultCode+"", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Some error, pls try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
