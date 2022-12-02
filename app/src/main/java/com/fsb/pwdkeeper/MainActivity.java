package com.fsb.pwdkeeper;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fsb.pwdkeeper.biometricauth.Authentication;
import com.fsb.pwdkeeper.errorhandler.ErrorHandler;
import com.fsb.pwdkeeper.services.AdminService;


public class MainActivity extends AppCompatActivity {

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
        TextView main_pwd_err = findViewById(R.id.main_pwd_err);
        EditText adminPwd = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        AdminService adminService = new AdminService(getContext());


        if(adminService.firstRun()){
            //first run of the app
            login.setOnClickListener(view -> {
                if(adminService.save(adminPwd.getText().toString())) switchActivity(Home.class);
                else ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.WEAKPASSWORD);
            });
        }else{

            //Digital Login
            Authentication authentication = new Authentication(this.getContext());
            if(authentication.isBiometricSupported()){
                BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, ContextCompat.getMainExecutor(getContext()),
                        new BiometricPrompt.AuthenticationCallback() {
                            @Override
                            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                                ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.AUTHENTICATIONERROR);
                            }
                            @Override
                            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                                switchActivity(Home.class);
                            }
                            @Override
                            public void onAuthenticationFailed() {
                                ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.AUTHENTICATIONFAILED);
                            }
                        });
                biometricPrompt.authenticate(authentication.getPromptInfo());
            }
            // Login with password
            login.setOnClickListener(view -> {
                if (adminService.login(adminPwd.getText().toString())) switchActivity(Home.class);
                else ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.WRONGPASSWORD);
            });
        }
    }
    private Context getContext() {
        return this;
    }

}