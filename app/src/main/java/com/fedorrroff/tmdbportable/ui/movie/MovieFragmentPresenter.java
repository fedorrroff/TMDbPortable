package com.fedorrroff.tmdbportable.ui.movie;

import android.view.View;

import com.fedorrroff.tmdbportable.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;

public class MovieFragmentPresenter {

    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();
    private final MovieFragment movieFragment;

    public MovieFragmentPresenter (MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    public void downloadMovieInfo(Integer id) {
        Thread loadTrailersThread = new Thread(() -> {
            try {
                MovieTrailer trailer = fakeRepo.getMovieTrailer(id);
                if (trailer != null) {
                    downloadMovieTrailer((trailer1) -> movieFragment.displayMovieTrailer(trailer), trailer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadTrailersThread.start();
    }

    public void downloadMovieTrailer(ShowMovieTrailer showMovieTrailer, MovieTrailer trailer) {
        showMovieTrailer.showMovieTrailer(trailer);
    }

    @FunctionalInterface
    public interface ShowMovieTrailer{
        void showMovieTrailer(MovieTrailer trailer);
    }
}
