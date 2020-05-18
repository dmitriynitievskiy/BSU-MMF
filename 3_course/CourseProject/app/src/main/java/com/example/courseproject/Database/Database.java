package com.example.courseproject.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.courseproject.Model.Favorites;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="CPDB.db";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    public void addToFavorites(Favorites place) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favorites(" +
                "PlaceId,PlaceName,PlaceMenuId,PlaceImage,PlaceDescription,UserPhone) " +
                "VALUES('%s','%s','%s','%s','%s','%s');",
                place.getPlaceId(),
                place.getPlaceName(),
                place.getPlaceMenuId(),
                place.getPlaceImage(),
                place.getPlaceDescription(),
                place.getUserPhone());
        db.execSQL(query);
    }
    public void removeFromFavorites(String placeId,String UserPhone) {
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("DELETE FROM Favorites WHERE PlaceId='%s' and UserPhone='%s';",placeId,UserPhone);
        db.execSQL(query);
    }
    public boolean isFavorite(String placeId,String UserPhone) {
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("SELECT * FROM Favorites WHERE PlaceId='%s' and UserPhone='%s';",placeId,UserPhone);
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<Favorites> getAllFavorites(String UserPhone) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"UserPhone", "PlaceId", "PlaceName", "PlaceMenuId", "PlaceImage", "PlaceDescription"};
        String sqlTable = "Favorites";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, "UserPhone=?", new String[]{UserPhone}, null, null, null);
        final List<Favorites> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new Favorites(
                        c.getString(c.getColumnIndex("PlaceId")),
                        c.getString(c.getColumnIndex("PlaceName")),
                        c.getString(c.getColumnIndex("PlaceMenuId")),
                        c.getString(c.getColumnIndex("PlaceImage")),
                        c.getString(c.getColumnIndex("PlaceDescription")),
                        c.getString(c.getColumnIndex("UserPhone"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }
}

