package com.fedorrroff.tmdbportable.ui.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.main.connection.NoConnectionFragment;
import com.fedorrroff.tmdbportable.ui.main.splash.SplashFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;

import javax.inject.Singleton;

@Singleton
public class NavigatorImpl implements Navigator {

    private static final int FRAGMENT_CONTAINER = R.id.fl_toReplace;

    private final FragmentManager fragmentManager;

    public NavigatorImpl(final AppCompatActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void showMovieScreen(MovieItem movie) {
        replaceFragmentBackStack(MovieFragment.newInstance(movie));
    }

    @Override
    public void showMainPage() {
        replaceFragmentMain(MainPageFragment.newInstance());
    }

    @Override
    public void showSplashScreen() {
        replaceFragmentMain(SplashFragment.newInstance());
    }

    @Override
    public void showConnectionPage() {
        replaceFragmentMain(NoConnectionFragment.newInstance());
    }

    @Override
    public void showPopularPage() {
        replaceFragmentBackStack(PopularMoviesFragment.newInstance());
    }

    private void replaceFragmentBackStack(final Fragment fragment) {
       fragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragmentMain(final Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .commit();
    }
}