package com.fedorrroff.tmdbportable.ui.main.splash;

import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.tmdbportable.utils.NetworkUtil;

import javax.inject.Inject;

public class SplashFragmentPresenter implements BasePresenter<SplashFragment> {

    private SplashFragment mView;
    private final Navigator mNavigator;
    private AppCompatActivity mActivity;

    @Inject
    public SplashFragmentPresenter(Navigator navigator, AppCompatActivity activity) {
        this.mNavigator = navigator;
        this.mActivity = activity;
    }

    @Override
    public void attachView(SplashFragment view) {
        mView = view;
    }

    public void nextPageSplash() {
        if (NetworkUtil.getConnectivityStatus(mActivity.getBaseContext())) {
            mNavigator.showMainPage();
        } else {
            mNavigator.showConnectionPage();
        }
    }
}
