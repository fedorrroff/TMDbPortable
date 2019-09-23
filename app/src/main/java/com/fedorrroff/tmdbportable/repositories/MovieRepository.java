package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.io.IOException;
import java.util.List;

public interface MovieRepository {

    List<MovieItem> getMovies() throws IOException;

}
