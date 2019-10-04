package com.fedorrroff.tmdbportable.di;

import com.fedorrroff.tmdbportable.di.main.MainComponent;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragmentPresenter;

import dagger.Component;

@Component(dependencies = MainComponent.class)
public interface FragmentPresenterComponent {

    MainPageFragmentPresenter mainFragmentPresenter();

}
