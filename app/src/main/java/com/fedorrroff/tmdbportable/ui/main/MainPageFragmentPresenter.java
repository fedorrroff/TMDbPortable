package com.fedorrroff.tmdbportable.ui.main;

import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import javax.inject.Inject;

public class MainPageFragmentPresenter {

    private final Navigator mNavigator;

    @Inject
    public MainPageFragmentPresenter(
            final Navigator navigator
    ) {
        mNavigator = navigator;
    }

}
