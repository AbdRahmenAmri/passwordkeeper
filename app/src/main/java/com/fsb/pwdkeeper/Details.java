package com.fsb.pwdkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fsb.pwdkeeper.model.Password;
import com.fsb.pwdkeeper.services.PasswordService;

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

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        PasswordService passwordService = new PasswordService(this);
        EditText addr =(EditText) findViewById(R.id.link);
        Button btnadrcopy = (Button) findViewById(R.id.copyadr);
        EditText pass =(EditText) findViewById(R.id.pass);
        Button btnpasscopy = (Button) findViewById(R.id.copypass);
        TextView logins_name = findViewById(R.id.logins_name);

        Intent i = getIntent();
        Password p = passwordService.findById(i.getStringExtra("ID"));

        addr.setText(p.getEmail());
        pass.setText(p.getPassword());
        logins_name.setText(p.getName());


        btnadrcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip1 = ClipData.newPlainText("address",addr.getText().toString());
                clipboard.setPrimaryClip(clip1);
                Toast.makeText(Details.this,"Copied",Toast.LENGTH_SHORT).show();
            }
        });

        btnpasscopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip2 = ClipData.newPlainText("password",pass.getText().toString());
                clipboard.setPrimaryClip(clip2);
                Toast.makeText(Details.this,"Copied",Toast.LENGTH_SHORT).show();
            }
        });

    }

}