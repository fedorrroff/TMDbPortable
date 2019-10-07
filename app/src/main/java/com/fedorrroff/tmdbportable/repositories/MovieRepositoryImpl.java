package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.models.data.MovieDetail;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;
import com.fedorrroff.models.data.PopularMoviesPage;
import com.fedorrroff.models.data.TopRatedMoviePage;
import com.fedorrroff.api.tmdbApi.Requester;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MovieRepositoryImpl implements MovieRepository {

    private final Requester movieResource;

    @Inject
    public MovieRepositoryImpl(Requester movieResource) {
        this.movieResource = movieResource;
    }

    @Override
    public List<MovieItem> getMovies() throws IOException {
        //Create service
        Call<PopularMoviesPage> moviePage = movieResource.getPopularMoviePage();
        return extractMovieFromPage(moviePage);
    }

    @Override
    public MovieTrailer getMovieTrailer(Integer id) throws IOException {
        //Create service
        Call<MovieDetail> movieDetail = movieResource.getMovieDetail(id);
        List<MovieTrailer> trailers = extractTrailersFromMovie(movieDetail);
        return trailers.size() > 0 ? trailers.get(0) : null;
    }

    @Override
    public List<MovieItem> getTopRatedMovies() throws IOException {
        Call<TopRatedMoviePage> moviePage = movieResource.getTopRatedMoviePage();
        return extractTopRatedMovieFromPage(moviePage);
    }

    private static List<MovieItem> extractMovieFromPage(Call<PopularMoviesPage> moviePage) throws IOException {
        PopularMoviesPage page = moviePage.execute().body();
        return page.getMovieItems();
    }

    private static List<MovieTrailer> extractTrailersFromMovie(Call<MovieDetail> movieDetail) throws IOException {
        MovieDetail singleMovieDetail = movieDetail.execute().body();
        return singleMovieDetail.getResults();
    }

    private static List<MovieItem> extractTopRatedMovieFromPage(Call<TopRatedMoviePage> topRatedMoviePage) throws IOException {
        TopRatedMoviePage moviePage = topRatedMoviePage.execute().body();
        return moviePage.getResults();
    }
}
