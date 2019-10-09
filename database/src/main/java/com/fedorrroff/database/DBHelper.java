package com.fedorrroff.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movieDB1";
    public static final String TABLE_MOVIES_POPULAR = "movies_popular";
    public static final String TABLE_MOVIES_TOP = "movies_top";

    public static final String KEY_TITLE = "title";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_BACKGROUND_PATH = "background_path";
    public static final String KEY_RATING = "rating";
    public static final String KEY_DATE = "date";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MOVIES_TOP +
                "(" + KEY_TITLE +
                " text," + KEY_OVERVIEW +
                " text," + KEY_POSTER_PATH +
                " text," + KEY_BACKGROUND_PATH +
                " text," + KEY_RATING +
                " text," + KEY_DATE +
                " text" + ")");

        db.execSQL("create table " + TABLE_MOVIES_POPULAR +
                "(" + KEY_TITLE +
                " text," + KEY_OVERVIEW +
                " text," + KEY_POSTER_PATH +
                " text," + KEY_BACKGROUND_PATH +
                " text," + KEY_RATING +
                " text," + KEY_DATE +
                " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_MOVIES_POPULAR);
        db.execSQL("drop table if exists " + TABLE_MOVIES_TOP);
        onCreate(db);
    }
}
