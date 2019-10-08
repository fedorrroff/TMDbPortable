package com.fedorrroff.repositories;

import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FakeRepo implements MovieRepository {

    private MovieItem createFake() {
        MovieItem fake = new MovieItem();
        fake.setId(228);
        fake.setTitle("Слыш Плати");
        fake.setOverview("Плес");
        fake.setReleaseDate("54.27");
        fake.setVoteAverage((float)14.88);
        fake.setPosterPath("No");

        return fake;
    }

    @Override
    public List<MovieItem> getMovies() throws IOException {
        List<MovieItem> fakeSet = new ArrayList<>();
        fakeSet.add(createFake());

        return fakeSet;
    }

    @Override
    public List<MovieItem> getTopRatedMovies() throws IOException {
        List<MovieItem> fakeSet = new ArrayList<>();
        fakeSet.add(createFake());

        return fakeSet;
    }

    @Override
    public MovieTrailer getMovieTrailer(Integer id) throws IOException {
        return null;
    }
}
