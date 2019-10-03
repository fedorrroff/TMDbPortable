package com.fedorrroff.tmdbportable.ui.popular;

import android.os.AsyncTask;
import android.util.Log;

import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class PopularMoviesFragmentPresenter {

    private final PopularMoviesFragment popularMoviesFragment;

    public PopularMoviesFragmentPresenter(PopularMoviesFragment popularMoviesFragment) {
        this.popularMoviesFragment = popularMoviesFragment;
    }

    public void downloadMovies() {
        Log.d("M_popularMoviesFragment", "onCreate");

        new DownloadMovieTask(popularMoviesFragment).execute();
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
