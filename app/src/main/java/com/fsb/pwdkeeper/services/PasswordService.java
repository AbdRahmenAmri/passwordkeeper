package com.fsb.pwdkeeper.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.fsb.pwdkeeper.dbmanager.PasswordDB;
import com.fsb.pwdkeeper.model.Password;

import java.util.ArrayList;
import java.util.List;

public class PasswordService {
    private SQLiteDatabase db;
    public PasswordService(Context ctx){this.db = new PasswordDB(ctx).getWritableDatabase();}

    private Integer intVal(String x){
        return Integer.valueOf(x);
    }


    private String nextId(){
        String query = "SELECT * FROM "+PasswordDB.TABLE_NAME;
        Cursor row = this.db.rawQuery(query,null);
        if(row.moveToLast()){
            return Integer.toString(this.intVal(this.getAttr(row,PasswordDB.id))+1);
        }
        return "1";
    }

    private String getAttr(Cursor row,String attr){
        if(attr.equals(PasswordDB.id)) return row.getString(0);
        else if(attr.equals(PasswordDB.name)) return row.getString(1);
        else if(attr.equals(PasswordDB.email)) return row.getString(2);
        else if(attr.equals(PasswordDB.password)) return row.getString(3);
        return null;
    }

    public boolean save(Password password){
        String sql="INSERT INTO "+ PasswordDB.TABLE_NAME+" VALUES (?, ?, ?, ?)";
        try {
            db.execSQL(sql, new String[]{this.nextId(),password.getName(),password.getEmail(),password.getPassword()});
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Password> findAll(){
        List<Password> passwords = new ArrayList<>();
        String query = "SELECT * FROM "+PasswordDB.TABLE_NAME;
        Cursor row = this.db.rawQuery(query,null);
        while(row.moveToNext()){
            passwords.add(new Password(this.getAttr(row,PasswordDB.id),this.getAttr(row,PasswordDB.name),this.getAttr(row,PasswordDB.email),this.getAttr(row,PasswordDB.password)));
        }
        return passwords;
    }

    public Password findById(String id){
        Password password;
        String query = "SELECT * FROM "+PasswordDB.TABLE_NAME+" WHERE "+PasswordDB.id+" = "+id;
        Cursor row = this.db.rawQuery(query,null);
        if(row.getCount()<=0)return null;
        else return new Password(this.getAttr(row,PasswordDB.id),this.getAttr(row,PasswordDB.name),this.getAttr(row,PasswordDB.email),this.getAttr(row,PasswordDB.password));
    }
}
