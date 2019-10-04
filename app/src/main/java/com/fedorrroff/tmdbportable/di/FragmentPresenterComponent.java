package com.fedorrroff.tmdbportable.di;

import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.api.APIModule;
import com.fedorrroff.tmdbportable.di.main.MainComponent;
import com.fedorrroff.tmdbportable.di.repo.RepositoryModule;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.tmdbApi.APIService;
import com.fedorrroff.tmdbportable.ui.main.MainActivity;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragmentPresenter;
import com.fedorrroff.tmdbportable.ui.main.NoConnectionFragment;
import com.fedorrroff.tmdbportable.ui.main.SplashFragment;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragmentPresenter;

import dagger.Component;

@Component(dependencies = MainComponent.class, modules = RepositoryModule.class)
public interface FragmentPresenterComponent {

    MainPageFragmentPresenter mainFragmentPresenter();

    PopularMoviesFragmentPresenter popularMoviesFragmentPresenter();

    void inject(PopularMoviesFragment view);

    void inject(MainPageFragment view);

    void inject(MovieFragment view);

    void inject(MainActivity mainActivity);

    void inject(SplashFragment splashFragment);

    void inject(NoConnectionFragment noConnectionFragment);
}
