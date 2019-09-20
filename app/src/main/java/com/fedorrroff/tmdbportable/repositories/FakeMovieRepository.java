package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.Movie;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.models.data.PopularMoviesPage;
import com.fedorrroff.tmdbportable.tmdbApi.APIService;
import com.fedorrroff.tmdbportable.tmdbApi.Requester;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class FakeMovieRepository implements MovieRepository {
    private static final FakeMovieRepository ourInstance = new FakeMovieRepository();

    public static FakeMovieRepository getInstance() {
        return ourInstance;
    }

    private FakeMovieRepository() {
    }

    @Override
    public List<MovieItem> getMovies() throws IOException {
        //Create retrofit, set the API base URL and GSonConverterFactory
        APIService movieResource = Requester.getInstance();
        //Create service
        Call<PopularMoviesPage> moviePage = movieResource.getPopularMoviePage();
        return extractMovieFromPage(moviePage);
    }

    private List<MovieItem> extractMovieFromPage (Call<PopularMoviesPage> moviePage) throws IOException {
        PopularMoviesPage page = moviePage.execute().body();
        return page.getMovieItems();
    }
}
