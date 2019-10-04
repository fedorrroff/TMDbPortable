package com.fedorrroff.tmdbportable.di.repo;

import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.tmdbApi.APIService;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private final APIService requester;

    public RepositoryModule (APIService requester) {
        this.requester = requester;
    }

    @Provides
    public MovieRepository provideRepository() {
        return new MovieRepositoryImpl(requester);
    }
}
