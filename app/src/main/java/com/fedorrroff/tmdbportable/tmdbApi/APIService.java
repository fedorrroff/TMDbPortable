package com.fedorrroff.tmdbportable.tmdbApi;

import com.fedorrroff.tmdbportable.models.data.PopularMoviesPage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    public static final String API_KEY = "9dcd33cbf3f386d03061b83e71d76fab";

    @GET("movie/popular?api_key=" + API_KEY)
    Call<PopularMoviesPage> getPopularMoviePage();
}
