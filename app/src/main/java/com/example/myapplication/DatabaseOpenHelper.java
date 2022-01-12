package com.example.myapplication;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "cookforpet.db";
    private static final int DB_VERSION=1;

    //constructor
    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
}
