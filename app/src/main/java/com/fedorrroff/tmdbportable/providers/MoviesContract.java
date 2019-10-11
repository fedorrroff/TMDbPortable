package com.fedorrroff.tmdbportable.providers;

import android.content.ContentUris;
import android.net.Uri;

import com.fedorrroff.database.DBHelper;

public class MoviesContract {

    static final String TABLE_NAME = DBHelper.TABLE_MOVIES_POPULAR;
    static final String CONTENT_AUTHORITY = "com.fedorrroff.tmdbportable.MoviesProvider";
    static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE= "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    public static class Columns {
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";

        private Columns() {

        }
    }

    static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static Uri buildMovieUri(long taskId){
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    static long getMovieId(Uri uri){
        return ContentUris.parseId(uri);
    }
}
