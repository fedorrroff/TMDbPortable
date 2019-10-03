package com.fedorrroff.tmdbportable.di;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.ui.main.MainActivity;
import com.fedorrroff.tmdbportable.ui.main.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import dagger.Component;

@Component(modules = ActivityModule.class)
public interface FragmentComponent {

    FragmentActivity activity();

    FragmentManager fragmentManager();

    Navigator navigator();

    void inject(PopularMoviesFragment mainPageFragment);

    void inject(MainActivity mainActivity);
}