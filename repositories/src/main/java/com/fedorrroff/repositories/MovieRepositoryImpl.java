package com.fedorrroff.repositories;

import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.database.Database;
import com.fedorrroff.models.data.MovieDetail;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;
import com.fedorrroff.models.data.PopularMoviesPage;
import com.fedorrroff.models.data.TopRatedMoviePage;
import com.fedorrroff.api.tmdbApi.Requester;
import com.fedorrroff.utils.utils.NetworkUtil;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;

public class MovieRepositoryImpl implements MovieRepository {

    private final Requester movieResource;
    private final Database database;
    private AppCompatActivity mActivity;

    @Inject
    public MovieRepositoryImpl(Requester movieResource, Database database, AppCompatActivity activity) {
        this.movieResource = movieResource;
        this.database = database;
        this.mActivity = activity;
    }

    @Override
    public Observable<List<MovieItem>> getMovies() {
        final Observable<List<MovieItem>> movies;

//        if (NetworkUtil.isConnectionAvailable(mActivity)) {
            Observable<PopularMoviesPage> moviePage = movieResource.getPopularMoviePage();

            movies = extractMovieFromPage(moviePage);

//            Thread writePopularToDBThread = new Thread(() -> {
//                database.writePopularMovies((movies));
//                database.closeDB();
//            });
//            writePopularToDBThread.start();

//        } else {
//            movies = getPopularMoviesFromDB();
//        }

        return movies;
    }

    @Override
    public MovieTrailer getMovieTrailer(Integer id) throws IOException {
        //Create service
        Call<MovieDetail> movieDetail = movieResource.getMovieDetail(id);
        List<MovieTrailer> trailers = extractTrailersFromMovie(movieDetail);
        return trailers.size() > 0 ? trailers.get(0) : null;
    }

    @Override
    public Observable<List<MovieItem>> getTopRatedMovies() {
        final Observable<List<MovieItem>> movies;

//        if (NetworkUtil.isConnectionAvailable(mActivity)) {
            Observable<TopRatedMoviePage> moviePage = movieResource.getTopRatedMoviePage();
            movies = extractTopRatedMovieFromPage(moviePage);

//            Thread writeTopToDBThread = new Thread(() -> {
//                database.writeTopMovies(movies);
//                database.closeDB();
//            });
//            writeTopToDBThread.start();
//        } else {
//            movies = getTopMoviesFromDB();
//        }

        return movies;
    }

    private static Observable<List<MovieItem>> extractMovieFromPage(Observable<PopularMoviesPage> moviePage) {
        return moviePage.map(PopularMoviesPage::getMovieItems);
    }

    private static List<MovieTrailer> extractTrailersFromMovie(Call<MovieDetail> movieDetail) throws IOException {
        MovieDetail singleMovieDetail = movieDetail.execute().body();
        return singleMovieDetail.getResults();
    }

    private static Observable<List<MovieItem>> extractTopRatedMovieFromPage(Observable<TopRatedMoviePage> topRatedMoviePage) {
        return topRatedMoviePage.map(TopRatedMoviePage::getResults);
    }

    private List<MovieItem> getPopularMoviesFromDB() {
        return database.getPopularMovies();
    }

    private List<MovieItem> getTopMoviesFromDB() {
        return database.getTopMovies();
    }
}
