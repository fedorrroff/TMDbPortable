package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.Movie;

import java.util.List;

public class FakeMovieRepository implements MovieRepository {
    private static final FakeMovieRepository ourInstance = new FakeMovieRepository();

    public static FakeMovieRepository getInstance() {
        return ourInstance;
    }

    private FakeMovieRepository() {
    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }
}
