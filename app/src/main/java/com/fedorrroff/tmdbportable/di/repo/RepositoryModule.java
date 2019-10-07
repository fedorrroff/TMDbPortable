package com.fedorrroff.tmdbportable.di.repo;

import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.api.tmdbApi.APIService;
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
