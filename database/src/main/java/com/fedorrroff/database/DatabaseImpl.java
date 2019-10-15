package com.fedorrroff.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fedorrroff.models.data.MovieItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.fedorrroff.database.DBHelper.KEY_BACKGROUND_PATH;
import static com.fedorrroff.database.DBHelper.KEY_DATE;
import static com.fedorrroff.database.DBHelper.KEY_OVERVIEW;
import static com.fedorrroff.database.DBHelper.KEY_POSTER_PATH;
import static com.fedorrroff.database.DBHelper.KEY_RATING;
import static com.fedorrroff.database.DBHelper.KEY_TITLE;

public class DatabaseImpl implements Database {

    private final DBHelper dbHelper;

    @Inject
    public DatabaseImpl (final DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<MovieItem> getPopularMovies() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_MOVIES_POPULAR,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );

        List<MovieItem> movies = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(KEY_TITLE);
            int overviewIndex = cursor.getColumnIndex(KEY_OVERVIEW );
            int posterPathIndex = cursor.getColumnIndex(KEY_POSTER_PATH );
            int backgroundPathIndex = cursor.getColumnIndex(KEY_BACKGROUND_PATH );
            int ratingIndex = cursor.getColumnIndex(KEY_RATING);
            int dateIndex = cursor.getColumnIndex(KEY_DATE);

            do {
                MovieItem singleItem = new MovieItem();

                singleItem.setTitle(cursor.getString(titleIndex));
                singleItem.setOverview(cursor.getString(overviewIndex));
                singleItem.setPosterPath(cursor.getString(posterPathIndex));
                singleItem.setBackdropPath(cursor.getString(backgroundPathIndex));
                singleItem.setVoteAverage(cursor.getFloat(ratingIndex));
                singleItem.setReleaseDate(cursor.getString(dateIndex));

                movies.add(singleItem);

            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();

        return movies;
    }

    @Override
    public void writePopularMovies(List<MovieItem> movies) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        database.delete(DBHelper.TABLE_MOVIES_POPULAR, null, null);

        for (MovieItem movie: movies) {
            contentValues.put(KEY_TITLE, movie.getTitle());
            contentValues.put(KEY_OVERVIEW , movie.getOverview());
            contentValues.put(KEY_POSTER_PATH , movie.getPosterPath());
            contentValues.put(KEY_BACKGROUND_PATH , movie.getBackdropPath());
            contentValues.put(KEY_RATING , movie.getVoteAverage());
            contentValues.put(KEY_DATE , movie.getReleaseDate());
            database.insert(DBHelper.TABLE_MOVIES_POPULAR, null, contentValues);
        }
//        dbHelper.close();
    }

    @Override
    public List<MovieItem> getTopMovies() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_MOVIES_TOP,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<MovieItem> movies = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(KEY_TITLE);
            int overviewIndex = cursor.getColumnIndex(KEY_OVERVIEW );
            int posterPathIndex = cursor.getColumnIndex(KEY_POSTER_PATH );
            int backgroundPathIndex = cursor.getColumnIndex(KEY_BACKGROUND_PATH );
            int ratingIndex = cursor.getColumnIndex(KEY_RATING);
            int dateIndex = cursor.getColumnIndex(KEY_DATE);

            do {
                MovieItem singleItem = new MovieItem();

                singleItem.setTitle(cursor.getString(titleIndex));
                singleItem.setOverview(cursor.getString(overviewIndex));
                singleItem.setPosterPath(cursor.getString(posterPathIndex));
                singleItem.setBackdropPath(cursor.getString(backgroundPathIndex));
                singleItem.setVoteAverage(cursor.getFloat(ratingIndex));
                singleItem.setReleaseDate(cursor.getString(dateIndex));

                movies.add(singleItem);

            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();

        return movies;
    }

    @Override
    public void writeTopMovies(List<MovieItem> movies) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        database.delete(DBHelper.TABLE_MOVIES_TOP, null, null);

        for (MovieItem movie: movies) {
            contentValues.put(KEY_TITLE, movie.getTitle());
            contentValues.put(KEY_OVERVIEW , movie.getOverview());
            contentValues.put(KEY_POSTER_PATH , movie.getPosterPath());
            contentValues.put(KEY_BACKGROUND_PATH , movie.getBackdropPath());
            contentValues.put(KEY_RATING , movie.getVoteAverage());
            contentValues.put(KEY_DATE , movie.getReleaseDate());
            database.insert(DBHelper.TABLE_MOVIES_TOP, null, contentValues);
        }
        dbHelper.close();
    }
}
