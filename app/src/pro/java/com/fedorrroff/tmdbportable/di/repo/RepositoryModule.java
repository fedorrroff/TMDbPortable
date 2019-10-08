package com.fedorrroff.tmdbportable.di.repo;

import com.fedorrroff.repositories.MovieRepository;
import com.fedorrroff.repositories.MovieRepositoryImpl;
import com.fedorrroff.api.tmdbApi.Requester;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public MovieRepository provideRepository(Requester requester) {
        return new MovieRepositoryImpl(requester);
    }
}
