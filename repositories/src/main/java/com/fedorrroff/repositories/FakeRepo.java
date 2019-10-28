package com.fedorrroff.repositories;

import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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
    public Observable<List<MovieItem>> getMovies() {
        List<MovieItem> fakeSet = new ArrayList<>();
        fakeSet.add(createFake());
        return Observable.just(fakeSet);
    }

    @Override
    public Observable<List<MovieItem>> getTopRatedMovies() {
        List<MovieItem> fakeSet = new ArrayList<>();
        fakeSet.add(createFake());
        return Observable.just(fakeSet);
    }

    @Override
    public MovieTrailer getMovieTrailer(Integer id) {
        return null;
    }
}
