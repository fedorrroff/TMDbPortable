package com.fedorrroff.tmdbportable.ui.toprated;

import android.os.AsyncTask;
import android.util.Log;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

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
        new DownloadMovieTask(mView, mMovieRepository).execute();
    }

    public void movieSelected(final MovieItem movieItem) {
        mNavigator.showMovieScreen(movieItem);
    }

    static class DownloadMovieTask extends AsyncTask<Void, Void, List<MovieItem>> {

        private final WeakReference<TopRatedMoviesFragment> topRatedMoviesFragmentRef;
        private final WeakReference<MovieRepository> repositoryRef;

        public DownloadMovieTask (TopRatedMoviesFragment topRatedMoviesFragment, MovieRepository repository) {
            topRatedMoviesFragmentRef = new WeakReference<>(topRatedMoviesFragment);
            repositoryRef = new WeakReference<>(repository);
        }

        @Override
        protected List<MovieItem> doInBackground(Void... voids) {
            try {
                return repositoryRef.get().getTopRatedMovies();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MovieItem> movieItems) {
            super.onPostExecute(movieItems);
            if (topRatedMoviesFragmentRef.get() != null) {
                topRatedMoviesFragmentRef.get().displayMovies(movieItems);
            }
        }
    }
}
