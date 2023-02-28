package com.example.shoppingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, "user_db", null, dbVersion);
    }
    //只在创建的时候用一次
    public void onCreate(SQLiteDatabase db) {
        String sql1="create table user(id integer primary key autoincrement,username TEXT ,password varchar(20),consignee TEXT,phonenumber varchar(20),address TEXT)";
        db.execSQL(sql1);
        String sql2="create table shop_table(id integer primary key autoincrement,goodname TEXT,number interger,price REAL,username TEXT,danjia REAL,image interger)";
        db.execSQL(sql2);
        String sql3="create table order_table(id integer primary key autoincrement,username TEXT,goodname TEXT,number interger,price REAL,danjia REAL,time TEXT,image interger)";
        db.execSQL(sql3);
        String sql4="create table good_table(id integer primary key autoincrement,goodname TEXT,price REAL,image interger)";
        db.execSQL(sql4);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
