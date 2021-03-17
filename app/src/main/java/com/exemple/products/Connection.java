/*
 * Class: Connection
 * Version: 1.0.0
 * Author: Mário Oliveira
 */

package com.exemple.products;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper {

    static final String name = "banco.db";
    static final Integer version = 1;

    public Connection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table product(id integer primary key autoincrement," +
                "name varchar(50), active Boolean, description varchar(150), price double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
