package com.fedorrroff.tmdbportable.di.api;

import com.fedorrroff.api.tmdbApi.APIService;
import com.fedorrroff.api.tmdbApi.Requester;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Provides
    public Requester provideRequester() {
        return new Requester(
                getApiService()
        );
    }

    private APIService getApiService() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }
}
