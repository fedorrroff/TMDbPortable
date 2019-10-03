package com.fedorrroff.tmdbportable.ui.navigation;

import com.fedorrroff.tmdbportable.models.data.MovieItem;

public interface Navigator {

    void showMovieScreen(MovieItem movie);
    void showPopularPage();
    void showMainPage();
    void showSplashScreen();
    void showConnectionPage();
}