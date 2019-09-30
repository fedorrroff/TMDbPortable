package com.fedorrroff.tmdbportable.ui.movie;

import android.os.AsyncTask;

import com.fedorrroff.tmdbportable.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class MovieFragmentPresenter {

    private final MovieFragment movieFragment;

    public MovieFragmentPresenter (MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    public void downloadMovieInfo(Integer id) {
        new DownloadMovieInfoTask(movieFragment).execute(id);
    }

    static class DownloadMovieInfoTask extends AsyncTask<Integer, Void, MovieTrailer> {

        private final WeakReference<MovieFragment> movieFragmentRef;
        private final MovieRepository repositoryRef;

        public DownloadMovieInfoTask(MovieFragment movieFragment) {
            movieFragmentRef = new WeakReference<>(movieFragment);
            repositoryRef = MovieRepositoryImpl.getInstance();
        }

        @Override
        protected MovieTrailer doInBackground(Integer... integers) {
            try {
                for (Integer integer: integers) {
                    return repositoryRef.getMovieTrailer(integer);
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
