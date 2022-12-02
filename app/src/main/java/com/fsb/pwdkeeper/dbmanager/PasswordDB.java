package com.fsb.pwdkeeper.dbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
public class PasswordDB extends SQLiteOpenHelper {
    static String DATABASE_NAME="passwords";
    public static final String TABLE_NAME="accounts";
    public static final String id="id";
    public static final String name="name";
    public static final String email="email";
    public static final String password="password";
    public PasswordDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+id+" VARCHAR PRIMARY KEY, "+name+" VARCHAR, "+email+" VARCHAR, "+password+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
