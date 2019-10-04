package com.fedorrroff.tmdbportable.tmdbApi;

import com.fedorrroff.tmdbportable.models.data.MovieDetail;
import com.fedorrroff.tmdbportable.models.data.PopularMoviesPage;

import retrofit2.Call;

public class Requester {

    private final APIService apiService;

    public Requester(APIService apiService) {
        this.apiService = apiService;
    }

    public Call<PopularMoviesPage> getPopularMoviePage() {
        return apiService.getPopularMoviePage();
    }

    public Call<MovieDetail> getMovieDetail(Integer id) {
        return apiService.getMovieDetail(id);
    }
}
