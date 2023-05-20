package com.aoslec.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MemberInfo extends SQLiteOpenHelper {

//    public MemberInfo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
    public MemberInfo(Context context){
        super(context, "MemberInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE member(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, userid TEXT, passwd INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS member";
        db.execSQL(query);
        onCreate(db);
    }
}
