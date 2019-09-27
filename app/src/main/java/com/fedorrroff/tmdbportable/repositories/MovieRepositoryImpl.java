package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.MovieDetail;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.models.data.PopularMoviesPage;
import com.fedorrroff.tmdbportable.tmdbApi.APIService;
import com.fedorrroff.tmdbportable.tmdbApi.Requester;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MovieRepositoryImpl implements MovieRepository {
    private static final MovieRepositoryImpl ourInstance = new MovieRepositoryImpl();

    public static MovieRepositoryImpl getInstance() {
        return ourInstance;
    }

    private MovieRepositoryImpl() {
    }

    @Override
    public List<MovieItem> getMovies() throws IOException {
        //Create retrofit, set the API base URL and GSonConverterFactory
        APIService movieResource = Requester.getInstance();
        //Create service
        Call<PopularMoviesPage> moviePage = movieResource.getPopularMoviePage();
        return extractMovieFromPage(moviePage);
    }

    @Override
    public MovieTrailer getMovieTrailer(Integer id) throws IOException {
        APIService movieResource = Requester.getInstance();
        //Create service
        Call<MovieDetail> movieDetail = movieResource.getMovieDetail(id);
        List<MovieTrailer> trailers = extractTrailersFromMovie(movieDetail);
        return trailers.size() > 0 ? trailers.get(0) : null;
    }

    private static List<MovieItem> extractMovieFromPage (Call<PopularMoviesPage> moviePage) throws IOException {
        PopularMoviesPage page = moviePage.execute().body();
        return page.getMovieItems();
    }

    private static List<MovieTrailer> extractTrailersFromMovie (Call<MovieDetail> movieDetail) throws IOException {
        MovieDetail singleMovieDetail = movieDetail.execute().body();
        return singleMovieDetail.getResults();
    }
}
