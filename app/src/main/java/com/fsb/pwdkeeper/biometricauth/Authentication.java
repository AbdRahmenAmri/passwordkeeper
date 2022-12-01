package com.fsb.pwdkeeper.biometricauth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


public class Authentication {
    private Context ctx;
    private BiometricPrompt.PromptInfo promptInfo;

    public BiometricPrompt.PromptInfo getPromptInfo(){
        return this.promptInfo;
    }
    public Authentication(Context ctx){
        this.ctx = ctx;
        this.promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Validation")
                .setSubtitle("This is is to validate using your biometric to check whether you are valid user to see the screen content. \n " +
                        "You can enter Device PIN if your fingerprint is failing")
                .setDeviceCredentialAllowed(true)
                .build();
    }
    public boolean isBiometricSupported() {
        int id = BiometricManager.from(this.ctx).canAuthenticate();
        return id == BiometricManager.BIOMETRIC_SUCCESS;
    }
}
