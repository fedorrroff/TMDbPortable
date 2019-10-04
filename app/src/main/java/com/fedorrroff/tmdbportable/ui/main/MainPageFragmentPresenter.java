package com.fedorrroff.tmdbportable.ui.main;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.tmdbportable.ui.main.splash.SplashFragment;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import javax.inject.Inject;

public class MainPageFragmentPresenter implements BasePresenter<MainPageFragment> {

    private final Navigator mNavigator;
    private MainPageFragment mView;


    @Inject
    public MainPageFragmentPresenter(
            final Navigator navigator
    ) {
        mNavigator = navigator;
    }

    @Override
    public void attachView(MainPageFragment view) {
        mView = view;
    }
}
