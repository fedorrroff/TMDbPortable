package com.fedorrroff.tmdbportable.ui.popular;

import android.util.Log;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PopularMoviesFragmentPresenter implements BasePresenter<PopularMoviesFragment> {

    private PopularMoviesFragment mView;
    private final Navigator mNavigator;
    private final MovieRepository mMovieRepository;

    @Inject
    public PopularMoviesFragmentPresenter(final Navigator navigator, final MovieRepository movieRepository) {
        mNavigator = navigator;
        mMovieRepository = movieRepository;
    }

    @Override
    public void attachView(final PopularMoviesFragment view) {
        mView = view;
    }

    public void downloadMovies() {
        Log.d("M_popularMoviesFragment", "onCreate");

        mMovieRepository.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> mView.displayMovies(movies));
    }

    public void movieSelected(final MovieItem movieItem) {
        mNavigator.showMovieScreen(movieItem);
    }
}
