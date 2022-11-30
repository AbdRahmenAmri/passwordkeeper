package com.fsb.pwdkeeper;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fsb.pwdkeeper.services.AdminService;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Validation")
            .setSubtitle("This is is to validate using your biometric to check whether you are valid user to see the screen content. \n " +
                    "You can enter Device PIN if your fingerprint is failing")
            .setDeviceCredentialAllowed(true)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void switchActivity(Class<Home> c){
        Intent secondActivity = new Intent(MainActivity.this,c);
        startActivity(secondActivity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView log = (TextView) findViewById(R.id.log);
        EditText adminPwd = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        AdminService adminService = new AdminService(getContext());

        if(adminService.firstRun()){
            log.setText("first run");
            //first run of the app
            login.setOnClickListener(view -> {
                log.setText("btn click");
                if(adminService.save(adminPwd.getText().toString())) switchActivity(Home.class);
            });
        }else{

            //Digital Login
            if(isBiometricSupported()){
                BiometricPrompt biometricPrompt = new BiometricPrompt(this, ContextCompat.getMainExecutor(getContext()),
                        new BiometricPrompt.AuthenticationCallback() {
                            @Override
                            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {}
                            @Override
                            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                                switchActivity(Home.class);
                            }
                            @Override
                            public void onAuthenticationFailed() {}
                        });
                biometricPrompt.authenticate(promptInfo);
            }


            // Login with password
            login.setOnClickListener(view -> {
                if (adminService.login(adminPwd.getText().toString())) switchActivity(Home.class);
            });
        }
    }

    private boolean isBiometricSupported() {
        int id = BiometricManager.from(getContext()).canAuthenticate();
        return id == BiometricManager.BIOMETRIC_SUCCESS;
    }

    private Context getContext() {
        return this;
    }

}