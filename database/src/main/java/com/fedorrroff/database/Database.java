package com.fedorrroff.database;

import com.fedorrroff.models.data.MovieItem;

import java.util.List;

public interface Database {

    public List<MovieItem> getPopularMovies();

    public void writePopularMovies(List<MovieItem> movies);

    public List<MovieItem> getTopMovies();

    public void writeTopMovies(List<MovieItem> movies);
}
