package com.fedorrroff.tmdbportable.ui.toprated;

import android.os.AsyncTask;
import android.util.Log;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopRatedMoviesFragmentPresenter implements BasePresenter<TopRatedMoviesFragment> {

    private TopRatedMoviesFragment mView;
    private final Navigator mNavigator;
    private final MovieRepository mMovieRepository;

    @Inject
    public TopRatedMoviesFragmentPresenter(final Navigator navigator, final MovieRepository movieRepository) {
        mNavigator = navigator;
        mMovieRepository = movieRepository;
    }

    @Override
    public void attachView(TopRatedMoviesFragment view) {
        mView = view;
    }

    public void downloadTopRatedMovies() {
        Log.d("M_popularMoviesFragment", "onCreate");
//        new DownloadMovieTask(mView, mMovieRepository).execute();
        mMovieRepository.getTopRatedMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> mView.displayMovies(movies));
    }

    public void movieSelected(final MovieItem movieItem) {
        mNavigator.showMovieScreen(movieItem);
    }

}
