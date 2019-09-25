package com.fedorrroff.tmdbportable.ui.movie;

import android.view.View;

import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;

public class MovieFragmentPresenter {

    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();
    private final MovieFragment movieFragment;

    public MovieFragmentPresenter (MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    public void downloadMovieInfo(Integer id, View view) {
        Runnable getMovieDetailFromApi = () ->
        {
            try {
                downloadMovieTrailer(id, view);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadTrailersThread = new Thread(getMovieDetailFromApi);
        loadTrailersThread.start();
    }

    public void downloadMovieTrailer(Integer id, View view) throws IOException{
        movieFragment.displayMovieTrailer(fakeRepo.getMovieTrailers(id).get(0));
    }
}
