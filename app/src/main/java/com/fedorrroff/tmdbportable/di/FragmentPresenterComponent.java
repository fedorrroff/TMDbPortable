package com.fedorrroff.tmdbportable.di;

import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.main.MainComponent;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragmentPresenter;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment;
import com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragmentPresenter;

import dagger.Component;

@Component(dependencies = MainComponent.class)
public interface FragmentPresenterComponent {

    MainPageFragmentPresenter mainFragmentPresenter();

    PopularMoviesFragmentPresenter popularMoviesFragmentPresenter();

    void inject(PopularMoviesFragment view);

    void inject(MainPageFragment view);

}
