package com.example.afishaminsk.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.view.MenuItem;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
import com.example.afishaminsk.Model.Place;
import com.example.afishaminsk.MainActivity;
import com.example.afishaminsk.R;

public class Database extends SQLiteAssetHelper {
    private static final int DB_VER = 1;
    private static final String DB_NAME = "database.db";
    public String tableName = "Films";
    MainActivity name = new MainActivity();


    public Database(Context context) {
        super (context, DB_NAME, null, DB_VER);
        setForcedUpgrade();
    }

    public List<Place> getFilms () {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id","Name","Adress","Work","Description"};


        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Place> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Place place = new Place();
                place.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                place.setName(cursor.getString(cursor.getColumnIndex("Name")));
                place.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                place.setWork(cursor.getString(cursor.getColumnIndex("Work")));
                place.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                result.add(place);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getNames () {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name"};


        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if ( cursor.moveToFirst()) {
            do {
                result.add (cursor.getString(cursor.getColumnIndex("Name")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Place> getFilmsByName (String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id","Name","Adress","Work","Description"};
        String tableName = "Films";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);
        List<Place> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Place place = new Place();
                place.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                place.setName(cursor.getString(cursor.getColumnIndex("Name")));
                place.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                place.setWork(cursor.getString(cursor.getColumnIndex("Work")));
                place.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                result.add(place);
            } while (cursor.moveToNext());
        }
        return result;
    }
}

