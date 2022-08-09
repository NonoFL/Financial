package com.example.financial.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "financial.db";
    public static final int VERSION = 1;
    public MyDBHelper(Context context){
        super(context, DBNAME, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tb_userinfo (" +
                "id integer primary key autoincrement," +
                "name varchar(10)," +
                "pwd varchar(15)," +
                "email varchar(50)," +
                "phone varchar(11))");

        sqLiteDatabase.execSQL("create table in_come (" +
                "id integer primary key autoincrement," +
                "inmoney double," +
                "intime varchar(20)," +
                "intype varchar(30)," +
                "inpayer varchar(100)," +
                "inremark varchar(500))");

        sqLiteDatabase.execSQL("create table pay_out (" +
                "id integer primary key autoincrement," +
                "outmoney double," +
                "outtime varchar(20)," +
                "outtype varchar(30)," +
                "outpayee varchar(100)," +
                "outremark varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
