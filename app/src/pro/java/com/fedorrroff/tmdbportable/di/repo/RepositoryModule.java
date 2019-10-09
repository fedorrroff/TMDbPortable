package com.fedorrroff.tmdbportable.di.repo;

import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.database.Database;
import com.fedorrroff.repositories.MovieRepository;
import com.fedorrroff.repositories.MovieRepositoryImpl;
import com.fedorrroff.api.tmdbApi.Requester;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public MovieRepository provideRepository(Requester requester, Database database, AppCompatActivity appCompatActivity) {
        return new MovieRepositoryImpl(requester, database, appCompatActivity);
    }
}
