package com.fedorrroff.api.tmdbApi;

import com.fedorrroff.models.data.MovieDetail;
import com.fedorrroff.models.data.PopularMoviesPage;
import com.fedorrroff.models.data.TopRatedMoviePage;

import io.reactivex.Observable;
import retrofit2.Call;

public class Requester {

    private final APIService apiService;

    public Requester(APIService apiService) {
        this.apiService = apiService;
    }

    public Observable<PopularMoviesPage> getPopularMoviePage() {
        return apiService.getPopularMoviePage();
    }

    public Observable<TopRatedMoviePage> getTopRatedMoviePage() {
        return apiService.getTopRatedMoviePage();
    }

    public Call<MovieDetail> getMovieDetail(Integer id) {
        return apiService.getMovieDetail(id);
    }
}
