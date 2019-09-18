package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getMovies();
}
