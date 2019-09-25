package com.fedorrroff.tmdbportable.ui.movie;

public class MovieFragmentPresenter {

    final MovieFragment movieFragment;

    public MovieFragmentPresenter(final MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    public void downloadInfo(int id) {
        //загрузка
        movieFragment.displayMovieInfo(null, null);
    }
}
