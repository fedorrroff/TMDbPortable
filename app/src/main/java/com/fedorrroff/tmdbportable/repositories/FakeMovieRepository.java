package com.fedorrroff.tmdbportable.repositories;

import com.fedorrroff.tmdbportable.models.data.Movie;
import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieRepository implements MovieRepository {
    private static final FakeMovieRepository ourInstance = new FakeMovieRepository();

    public static FakeMovieRepository getInstance() {
        return ourInstance;
    }

    private FakeMovieRepository() {
    }

    @Override
    public List<MovieItem> getMovies() {
        List<MovieItem> items = new ArrayList<>();

        items.add(new MovieItem("desc1", "title1", null));
        items.add(new MovieItem("desc2", "title2", null));
        items.add(new MovieItem("desc2", "title2", null));

        return items;
    }
}
