package com.fsb.pwdkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
    @Override
    protected void onStart() {
        super.onStart();
        /**
         * Class PasswordService howa eli fih ay commande nest3louha fl base
         * Example: awl 7aja nas3o Object PasswordService
         => PasswordService passwordService = new PasswordService(this);
         ! tnsech this fl constructor

         * Example bch na3mlo save ll Password fl base
         => passwordService.save(new Password("test","test@test.test","test"));

         * Example bch na3mlo find(Select) ll Password eli msajlin fl base lkol
         *      => List<Password> passwords = passwordService.findAll();
         *      Type de return hiya list ta3 Password
         *
         *
         * Example bch na3mlo find(select) ll Password bl id t3o
         *          *      => List<Password> passwords = passwordService.findById(id);
         *                  id type te3o String
         *          *      Type de return hiya Object Password
         *          *
         */
    }
}