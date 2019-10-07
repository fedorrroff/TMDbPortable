package com.fedorrroff.tmdbportable.ui.movie;

import android.os.AsyncTask;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class MovieFragmentPresenter implements BasePresenter<MovieFragment> {

    private MovieFragment mView;
    private final Navigator mNavigator;
    private final MovieRepository mMovieRepository;

    @Inject
    public MovieFragmentPresenter(final Navigator navigator, final MovieRepository movieRepository) {
        mNavigator = navigator;
        mMovieRepository = movieRepository;
    }

    @Override
    public void attachView(MovieFragment view) {
        mView = view;
    }

    public void downloadMovieInfo(Integer id) {
        new DownloadMovieInfoTask(mView, mMovieRepository).execute(id);
    }

    static class DownloadMovieInfoTask extends AsyncTask<Integer, Void, MovieTrailer> {

        private final WeakReference<MovieFragment> movieFragmentRef;
        private final WeakReference<MovieRepository> repositoryRef;

        public DownloadMovieInfoTask(MovieFragment movieFragment, MovieRepository repository) {
            movieFragmentRef = new WeakReference<>(movieFragment);
            repositoryRef = new WeakReference<>(repository);
        }

        @Override
        protected MovieTrailer doInBackground(Integer... integers) {
            try {
                for (Integer integer: integers) {
                    return repositoryRef.get().getMovieTrailer(integer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MovieTrailer trailer) {
            super.onPostExecute(trailer);
            if (trailer != null && movieFragmentRef.get() != null) {
                movieFragmentRef.get().displayMovieTrailer(trailer);
            }
        }
    }
}
