package com.fedorrroff.tmdbportable.ui.main.connection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.utils.utils.NetworkUtil;

import javax.inject.Inject;

public class NoConnectionFragmentPresenter implements BasePresenter<NoConnectionFragment> {

    private NoConnectionFragment mView;
    private final Navigator mNavigator;
    private AppCompatActivity mActivity;

    @Inject
    public NoConnectionFragmentPresenter(Navigator navigator, AppCompatActivity activity) {
        mNavigator = navigator;
        mActivity = activity;
    }

    @Override
    public void attachView(NoConnectionFragment view) {
        mView = view;
    }

    public void nextPageConnection() {
        if (NetworkUtil.isConnectionAvailable(mActivity.getBaseContext())) {
            mNavigator.showMainPage();
        }
    }
}
