package com.fedorrroff.repositories;

import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<List<MovieItem>> getMovies();

    Observable<List<MovieItem>> getTopRatedMovies();

    MovieTrailer getMovieTrailer(Integer id) throws IOException;
}
