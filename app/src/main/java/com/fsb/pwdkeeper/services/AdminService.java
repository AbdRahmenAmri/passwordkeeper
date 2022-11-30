package com.fsb.pwdkeeper.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fsb.pwdkeeper.dbmanager.LoginDB;

public class AdminService {
    private SQLiteDatabase loginDB;

    public AdminService(Context ctx){
        this.loginDB = new LoginDB(ctx).getWritableDatabase();
    }

    private boolean validePassword(String pwd){
        return !pwd.equals("");
    }

    public boolean save(String pwd){
        if(!this.validePassword(pwd)) return false;
        String sql="INSERT INTO "+LoginDB.TABLE_NAME+" VALUES (?, ?)";
        try {
            loginDB.execSQL(sql, new String[]{"admin",pwd});
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean firstRun(){
        String query = "SELECT * FROM "+LoginDB.TABLE_NAME;
        Cursor row = this.loginDB.rawQuery(query,null);
        return row.getCount()<=0;
    }

    public boolean login(String pwd){
        String query = "SELECT "+LoginDB.password+" FROM "+LoginDB.TABLE_NAME;
        Cursor row = this.loginDB.rawQuery(query,null);
        try{
            if(row.moveToFirst()){
                if(row.getString(0).toString().equals(pwd)) return true;
            }
        }catch (Exception e) {
            return false;
        }
        return false;
    }
}
