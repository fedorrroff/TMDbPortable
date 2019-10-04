package com.fedorrroff.tmdbportable.ui.popular;

import android.os.AsyncTask;
import android.util.Log;

import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class PopularMoviesFragmentPresenter implements BasePresenter<PopularMoviesFragment> {

    private PopularMoviesFragment mView;
    private final Navigator mNavigator;

    @Inject
    public PopularMoviesFragmentPresenter(final Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void attachView(final PopularMoviesFragment view) {
        mView = view;
    }

    public void downloadMovies() {
        Log.d("M_popularMoviesFragment", "onCreate");

        new DownloadMovieTask(mView).execute();
    }

    public void movieSelected(final MovieItem movieItem) {
        mNavigator.showMovieScreen(movieItem);
    }

     static class DownloadMovieTask extends AsyncTask <Void, Void, List<MovieItem>> {

        private final WeakReference<PopularMoviesFragment> popularMoviesFragmentRef;
        private final MovieRepository repositoryRef;

        public DownloadMovieTask (PopularMoviesFragment popularMoviesFragment) {
            popularMoviesFragmentRef = new WeakReference<>(popularMoviesFragment);
            repositoryRef = MovieRepositoryImpl.getInstance();
        }

        @Override
        protected List<MovieItem> doInBackground(Void... voids) {
            try {
                return repositoryRef.getMovies();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MovieItem> movieItems) {
            super.onPostExecute(movieItems);
            if (popularMoviesFragmentRef.get() != null) {
                popularMoviesFragmentRef.get().displayMovies(movieItems);
            }
        }
    }
}
