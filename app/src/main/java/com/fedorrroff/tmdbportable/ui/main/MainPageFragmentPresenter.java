package com.fedorrroff.tmdbportable.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.MovieRepositoryImpl;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class MainPageFragmentPresenter {

    private final MainPageFragment mainPageFragment;

    public MainPageFragmentPresenter (MainPageFragment mainPageFragment) {
        this.mainPageFragment = mainPageFragment;
    }

    public void downloadMovies() {
        Log.d("M_mainPageFragment", "onCreate");

        new DownloadMovieTask(mainPageFragment).execute();
    }

     static class DownloadMovieTask extends AsyncTask <Void, Void, List<MovieItem>> {

        private final WeakReference<MainPageFragment> mainPageFragmentRef;
        private final MovieRepository repositoryRef;

        public DownloadMovieTask (MainPageFragment mainPageFragment) {
            mainPageFragmentRef = new WeakReference<>(mainPageFragment);
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
            if (mainPageFragmentRef.get() != null) {
                mainPageFragmentRef.get().displayMovies(movieItems);
            }
        }
    }
}
