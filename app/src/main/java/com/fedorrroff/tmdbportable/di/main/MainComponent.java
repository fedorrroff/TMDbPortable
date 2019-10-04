package com.fedorrroff.tmdbportable.di.main;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.di.main.ActivityModule;
import com.fedorrroff.tmdbportable.ui.main.MainActivity;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.main.NoConnectionFragment;
import com.fedorrroff.tmdbportable.ui.main.SplashFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import dagger.Component;

@Component(modules = ActivityModule.class)
public interface MainComponent {

    FragmentActivity activity();

    FragmentManager fragmentManager();

    Navigator navigator();

    void inject(PopularMoviesFragment popularMoviesFragment);

    void inject(MainActivity mainActivity);

    void inject(MainPageFragment mainPageFragment);

    void inject(SplashFragment splashFragment);

    void inject(NoConnectionFragment noConnectionFragment);
}