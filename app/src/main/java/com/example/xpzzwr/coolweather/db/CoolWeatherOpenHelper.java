package com.example.xpzzwr.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xpzzwr on 2015/3/12.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    private static final String createProvice = "create table province("
            + "id integer primary key autoincrement, "
            + "province_name text,"
            + "province_code text)";

    private static final String createCity = "create table city("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)" ;

    private static final String createCounty = "create table county("
            + "id integer primary key autoincrement, "
            + "county_name text, "
            + "county_code text, "
            + "city_id integer)";


    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createProvice);    //创建省表格
        db.execSQL(createCity);       //创建城市表格
        db.execSQL(createCounty);     //创建县表格
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //   版本升级
    }
}
