package com.fsb.pwdkeeper.dbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
public class LoginDB extends SQLiteOpenHelper {
    static String DATABASE_NAME="passwordKeeper";
    public static final String TABLE_NAME="admin";
    public static final String username="username";
    public static final String password="password";
    public LoginDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+username+" VARCHAR, "+password+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}