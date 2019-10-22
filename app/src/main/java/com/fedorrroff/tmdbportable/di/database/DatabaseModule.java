package com.fedorrroff.tmdbportable.di.database;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.database.DBHelper;
import com.fedorrroff.database.Database;
import com.fedorrroff.database.DatabaseImpl;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    public DBHelper provideDBHelper(AppCompatActivity context) {
        return new DBHelper(context);
    }

    @Provides
    public Database provideDatabase(DBHelper dbHelper) {
        return new DatabaseImpl(dbHelper);
    }
}
