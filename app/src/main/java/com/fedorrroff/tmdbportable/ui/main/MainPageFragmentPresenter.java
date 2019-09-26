package com.fedorrroff.tmdbportable.ui.main;

import android.util.Log;

import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

public class MainPageFragmentPresenter {

    private final MainPageFragment mainPageFragment;
    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();

    public MainPageFragmentPresenter (MainPageFragment mainPageFragment) {
        this.mainPageFragment = mainPageFragment;
    }

    public void downloadMovies() {
        Log.d("M_mainPageFragment", "onCreate");
        Runnable getMoviesFromApi = () -> {
            try {
                List<MovieItem> movies = fakeRepo.getMovies();
                mainPageFragment.displayMovies(movies);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadMoviesThread = new Thread(getMoviesFromApi);
        loadMoviesThread.start();
    }
}
