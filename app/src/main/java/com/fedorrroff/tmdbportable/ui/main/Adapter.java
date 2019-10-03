package com.fedorrroff.tmdbportable.ui.main;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.toprated.TopRatedMoviesFragment;

import javax.inject.Inject;

public class Adapter extends FragmentPagerAdapter {

    @Inject
    Navigator navigator;

    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PopularMoviesFragment.newInstance();
            case 1:
                return TopRatedMoviesFragment.newInstance();
        }
        return MainPageFragment.newInstance();
    }

//    @Override
//    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.setPrimaryItem(container, position, object);
//        if (position == 0) {
//            navigator.showPopularPage();
//        } else {
//            navigator.showMainPage();
//        }
//    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "POPULAR";
            case 1:
                return "TOP RATED";
        }

        return "default";
    }
}

