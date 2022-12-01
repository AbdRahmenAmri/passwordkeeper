package com.fsb.pwdkeeper;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fsb.pwdkeeper.biometricauth.Authentication;
import com.fsb.pwdkeeper.errormsg.ErrorHandler;
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
        TextView main_pwd_err = (TextView) findViewById(R.id.main_pwd_err);
        EditText adminPwd = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        AdminService adminService = new AdminService(getContext());


        if(adminService.firstRun()){
            //first run of the app
            login.setOnClickListener(view -> {
                if(adminService.save(adminPwd.getText().toString())) switchActivity(Home.class);
            });
        }else{

            //Digital Login
            Authentication authentication = new Authentication(this.getContext());
            if(authentication.isBiometricSupported()){
                try {
                    String status = authentication.getStatus();
                    if(status.equals(Authentication.SUCCEEDED)) switchActivity(Home.class);
                    else if(status.equals(Authentication.ERROR)) ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.AUTHENTICATIONERROR);
                    else if(status.equals(Authentication.FAILED)) ErrorHandler.displayOnTextView(main_pwd_err,ErrorHandler.AUTHENTICATIONFAILED);
                }catch (Exception e){
                    ErrorHandler.displayOnTextView(main_pwd_err,e.toString());
                }
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