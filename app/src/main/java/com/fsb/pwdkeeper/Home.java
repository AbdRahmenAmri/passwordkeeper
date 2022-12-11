package com.fsb.pwdkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fsb.pwdkeeper.model.Password;
import com.fsb.pwdkeeper.services.PasswordService;

import java.util.List;

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

public class Home extends AppCompatActivity implements RecycleViewInterface{
    List<Password> passwordList;
    private void loadData(){
        PasswordService passwordService = new PasswordService(this);
        passwordList = passwordService.findAll();
        RecyclerView recyclerView = findViewById(R.id.RCV);
        PasswordRecycleViewAdapter adapter = new PasswordRecycleViewAdapter(passwordList,this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.loadData();
        Button new_password = findViewById(R.id.new_password);
        new_password.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this,NewPassword.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.loadData();
    }

    @Override
    public void onItemClick(int pos) {
        startActivity(new Intent(Home.this,Details.class).putExtra("ID",passwordList.get(pos).getId()));
    }
}