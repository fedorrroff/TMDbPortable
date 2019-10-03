package com.fedorrroff.tmdbportable.ui.navigation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.main.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;

import javax.inject.Singleton;

@Singleton
public class NavigatorImpl implements Navigator {

    private static final int FRAGMENT_CONTAINER = R.id.fl_toReplace;

    private final FragmentManager fragmentManager;

    public NavigatorImpl(final FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void showMovieScreen(MovieItem movie) {
        replaceFragmentMovie(MovieFragment.newInstance(movie));
    }

    @Override
    public void showMainPage() {
        replaceFragmentMain(PopularMoviesFragment.newInstance());
    }

    private void replaceFragmentMain(final Fragment fragment) {
       fragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .commit();
    }

    private void replaceFragmentMovie(final Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}