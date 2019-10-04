package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.models.data.MovieTrailer;

import java.io.IOException;
import java.util.List;

public interface MovieRepository {

    List<MovieItem> getMovies() throws IOException;

    List<MovieItem> getTopRatedMovies() throws IOException;

    MovieTrailer getMovieTrailer(Integer id) throws IOException;
}
