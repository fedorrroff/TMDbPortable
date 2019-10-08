package com.fedorrroff.repositories;

import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;

import java.io.IOException;
import java.util.List;

public interface MovieRepository {

    List<MovieItem> getMovies() throws IOException;

    List<MovieItem> getTopRatedMovies() throws IOException;

    MovieTrailer getMovieTrailer(Integer id) throws IOException;
}
