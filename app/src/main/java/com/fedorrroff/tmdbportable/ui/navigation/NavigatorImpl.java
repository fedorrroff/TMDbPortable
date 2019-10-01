package com.fedorrroff.tmdbportable.ui.navigation;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.R;
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
    public void showMovieScreen(int id) {
        replaceFragment(new MovieFragment());
    }

    private void replaceFragment(final Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
                .commit();
    }

}
