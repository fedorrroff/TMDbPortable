package com.fedorrroff.tmdbportable.ui.main;

import android.util.Log;

import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

public class MainPageFragmentPresenter {

    private final MainPageFragment mainPageFragment;
    private MovieRepository fakeRepo = MovieRepositoryImpl.getInstance();

    public MainPageFragmentPresenter (MainPageFragment mainPageFragment) {
        this.mainPageFragment = mainPageFragment;
    }

    public void downloadMovies() {
        Log.d("M_mainPageFragment", "onCreate");

        Thread loadMoviesThread = new Thread(() -> {
            try {
                List<MovieItem> movies = fakeRepo.getMovies();
                mainPageFragment.displayMovies(movies);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadMoviesThread.start();
    }
}
