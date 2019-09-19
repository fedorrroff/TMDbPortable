package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.Movie;
import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.util.List;

public interface MovieRepository {

    List<MovieItem> getMovies();
}
