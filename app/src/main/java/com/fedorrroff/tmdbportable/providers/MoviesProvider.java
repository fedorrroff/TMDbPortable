package com.fedorrroff.tmdbportable.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fedorrroff.database.DBHelper;

public class MoviesProvider extends ContentProvider {

    private DBHelper dbHelper = new DBHelper(getContext());

//    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int MOVIES = 100;
    public static final int MOVIES_ID = 101;

//    private static UriMatcher buildUriMatcher(){
//        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//        // content://com.fedorrroff.tmdbportable.providers/MOVIES
//        matcher.addURI("content://com.fedorrroff.tmdbportable.providers.MoviesProvider/", "movies_popular", MOVIES);
//        // content://com.fedorrroff.tmdbportable.providers/MOVIES/{id}
//        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.TABLE_NAME + "/#", MOVIES_ID);
//        return matcher;
//    }

    public MoviesProvider() {

    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
//        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        switch(match){
//            case MOVIES:
                queryBuilder.setTables(MoviesContract.TABLE_NAME);
//                break;
//            case MOVIES_ID:
                queryBuilder.setTables(MoviesContract.TABLE_NAME);
                long taskId = MoviesContract.getMovieId(uri);
                queryBuilder.appendWhere(MoviesContract.Columns.TITLE + " = " + taskId);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI: "+ uri);
//        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case MOVIES:
                return MoviesContract.CONTENT_TYPE;
                //TODO delete _ID
            case MOVIES_ID:
                return MoviesContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db;
        Uri returnUri;
        long recordId;

        switch(match){
            case MOVIES:
                db = dbHelper.getWritableDatabase();
                recordId = db.insert(MoviesContract.TABLE_NAME, null, contentValues);
                if(recordId > 0){
                    returnUri = MoviesContract.buildMovieUri(recordId);
                }
                else{
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selectionCriteria = selection;

        if(match != MOVIES && match != MOVIES_ID)
            throw new IllegalArgumentException("Unknown URI: "+ uri);

        if(match == MOVIES_ID) {
            long taskId = MoviesContract.getMovieId(uri);
            selectionCriteria = MoviesContract.Columns.TITLE + " = " + taskId;
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
        }
        return db.delete(MoviesContract.TABLE_NAME, selectionCriteria, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues contentValues,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectionCriteria = selection;

        if(match != MOVIES && match != MOVIES_ID)
            throw new IllegalArgumentException("Unknown URI: "+ uri);

        if(match == MOVIES_ID) {
            long taskId = MoviesContract.getMovieId(uri);
            selectionCriteria = MoviesContract.Columns.TITLE + " = " + taskId;
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
        }
        return db.update(MoviesContract.TABLE_NAME, contentValues, selectionCriteria, selectionArgs);
    }
}
