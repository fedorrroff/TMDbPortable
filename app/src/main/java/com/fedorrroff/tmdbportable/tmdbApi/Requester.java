package com.fedorrroff.tmdbportable.tmdbApi;

import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "9dcd33cbf3f386d03061b83e71d76fab";

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
