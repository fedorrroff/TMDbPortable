package com.fedorrroff.tmdbportable.di;

import com.fedorrroff.tmdbportable.di.main.MainComponent;
import com.fedorrroff.tmdbportable.di.repo.RepositoryModule;
import com.fedorrroff.tmdbportable.ui.main.MainActivity;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragmentPresenter;
import com.fedorrroff.tmdbportable.ui.main.connection.NoConnectionFragment;
import com.fedorrroff.tmdbportable.ui.main.splash.SplashFragment;
import com.fedorrroff.tmdbportable.ui.main.splash.SplashFragmentPresenter;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragmentPresenter;
import com.fedorrroff.tmdbportable.ui.toprated.TopRatedMoviesFragment;

import dagger.Component;

@Component(dependencies = MainComponent.class, modules = RepositoryModule.class)
public interface FragmentPresenterComponent {

    MainPageFragmentPresenter mainFragmentPresenter();

    PopularMoviesFragmentPresenter popularMoviesFragmentPresenter();

    SplashFragmentPresenter splashFragmentPresenter();

    void inject(PopularMoviesFragment view);

    void inject(MainPageFragment view);

    void inject(MovieFragment view);

    void inject(MainActivity mainActivity);

    void inject(SplashFragment view);

    void inject(NoConnectionFragment view);

    void inject(TopRatedMoviesFragment view);
}
