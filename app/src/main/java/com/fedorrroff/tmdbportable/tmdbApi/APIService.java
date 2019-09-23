package com.fedorrroff.tmdbportable.tmdbApi;

import com.fedorrroff.tmdbportable.models.data.PopularMoviesPage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.fedorrroff.tmdbportable.tmdbApi.Requester.API_KEY;


public interface APIService {

    @GET("movie/popular?api_key=" + API_KEY)
    Call<PopularMoviesPage> getPopularMoviePage();
}
