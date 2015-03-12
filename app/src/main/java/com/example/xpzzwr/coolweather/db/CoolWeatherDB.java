package com.example.xpzzwr.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xpzzwr.coolweather.model.City;
import com.example.xpzzwr.coolweather.model.County;
import com.example.xpzzwr.coolweather.model.Province;

import java.util.List;

/**
 * Created by xpzzwr on 2015/3/12.
 */
public class CoolWeatherDB  {


    public static final String dbName = "cool_weather";
    public static final int VERSION = 1;
    private SQLiteDatabase db;
    private static CoolWeatherDB coolWeatherDB;


    /**
     *构造函数私有化
     */
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper CWOH = new CoolWeatherOpenHelper(context, dbName, null, VERSION);
        db = CWOH.getWritableDatabase();
    }


    /**
    * 获取CoolWeather实例
    */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 将省份保存至数据库
     * */
    public void saveProvince(Province province) {
        ContentValues values = new ContentValues();
        values.put("province_name", province.getProvinceName());
        values.put("province_code", province.getProvinceCode());
        db.insert("province", null, values);
    }

    /**
     * 遍历省份信息
     * */
    public List<Province> loadProvince() {
        List<Province> provinceList = null;
        Cursor cursor = db.query("province", null, null,null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            }while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return provinceList;
    }


    /**
     * 将城市实例保存至数据库
     * */
    public void saveCity(City city) {
        ContentValues values = new ContentValues();
        values.put("city_name", city.getCityName());
        values.put("city_code", city.getCityCode());
        values.put("province_id", city.getProvinceId());
        db.insert("city", null, values);
    }

    /**
     * 遍历某省份下的城市
     * */
    public List<City> loadCity(int provinceId) {
        List<City> cityList = null;
        Cursor cursor = db.query("city", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            }while(cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return cityList;
    }

    /**
     * 保存县实例至数据库
     * */
    public void saveCounty(County county) {
        ContentValues values = new ContentValues();
        values.put("county_name", county.getCountyName());
        values.put("county_code", county.getCountyCode());
        values.put("city_id", county.getCityId());
        db.insert("county", null, values);
    }

    /**
     * 遍历某城市下面的县信息
     * */
    public List<County> loadCounty(int cityId) {
        List<County> countyList = null;
        Cursor cursor = db.query("county", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                countyList.add(county);
            }while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return countyList;
    }


}
