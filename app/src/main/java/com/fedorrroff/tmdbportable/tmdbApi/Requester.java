package com.fedorrroff.tmdbportable.tmdbApi;

import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final APIService ourInstance = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService.class);

    private Requester() {
    }

    public static APIService getInstance() {
        return ourInstance;
    }
}
