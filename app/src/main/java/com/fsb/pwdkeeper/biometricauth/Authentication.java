package com.fsb.pwdkeeper.biometricauth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


public class Authentication {
    private Context ctx;

    public static String ERROR = "ERROR";
    public static String FAILED = "FAILED";
    public static String SUCCEEDED = "SUCCEEDED";

    private String status = null;

    public Authentication(Context ctx){
        this.ctx = ctx;
    }


    public String getStatus() throws InterruptedException {
        while (this.status == null) wait(500);
        return this.status;

    }

    public boolean isBiometricSupported() {
        int id = BiometricManager.from(this.ctx).canAuthenticate();
        return id == BiometricManager.BIOMETRIC_SUCCESS;
    }
    private BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Validation")
            .setSubtitle("This is is to validate using your biometric to check whether you are valid user to see the screen content. \n " +
                    "You can enter Device PIN if your fingerprint is failing")
            .setDeviceCredentialAllowed(true)
            .build();

    public void authenticate (){
        BiometricPrompt biometricPrompt = new BiometricPrompt((FragmentActivity) this.ctx, ContextCompat.getMainExecutor(this.ctx),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        status = Authentication.ERROR;
                    }
                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        status = Authentication.SUCCEEDED;
                    }
                    @Override
                    public void onAuthenticationFailed() {
                        status = Authentication.FAILED;
                    }
                });
        biometricPrompt.authenticate(this.promptInfo);
    }
}
