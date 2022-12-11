package com.fsb.pwdkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fsb.pwdkeeper.model.Password;
import com.fsb.pwdkeeper.services.PasswordService;

public class NewPassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        PasswordService passwordService = new PasswordService(this);
        EditText new_name = findViewById(R.id.new_name);
        EditText new_login = findViewById(R.id.new_login);
        EditText new_pass = findViewById(R.id.new_pass);
        Button save = findViewById(R.id.save);
        TextView new_log = findViewById(R.id.new_log);
        save.setOnClickListener(v -> {
            String name = String.valueOf(new_name.getText());
            String login = String.valueOf(new_login.getText());
            String pass = String.valueOf(new_pass.getText());
            Password p = new Password(name,login,pass);
            if(passwordService.save(p)) new_log.setText("Record Add"); else new_log.setText("Error");
        });
    }
}