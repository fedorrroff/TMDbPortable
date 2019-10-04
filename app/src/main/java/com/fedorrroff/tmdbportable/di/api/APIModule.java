package com.fedorrroff.tmdbportable.di.api;

import com.fedorrroff.tmdbportable.tmdbApi.APIService;
import com.fedorrroff.tmdbportable.tmdbApi.Requester;

import dagger.Module;
import dagger.Provides;

@Module
public class APIModule {

    @Provides
    public APIService provideRequester() {
        return Requester.getInstance();
    }
}
