package com.fedorrroff.api.tmdbApi;

import com.fedorrroff.models.data.MovieDetail;
import com.fedorrroff.models.data.PopularMoviesPage;
import com.fedorrroff.models.data.TopRatedMoviePage;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    public static final String API_KEY = "9dcd33cbf3f386d03061b83e71d76fab";

    @GET("movie/popular?api_key=" + API_KEY)
    Observable<PopularMoviesPage> getPopularMoviePage();

    @GET("movie/{movie_id}/videos?api_key=" + API_KEY)
    Call<MovieDetail> getMovieDetail(
            @Path("movie_id") Integer id
    );

    @GET("movie/top_rated?api_key=" + API_KEY)
    Observable<TopRatedMoviePage> getTopRatedMoviePage();
}
