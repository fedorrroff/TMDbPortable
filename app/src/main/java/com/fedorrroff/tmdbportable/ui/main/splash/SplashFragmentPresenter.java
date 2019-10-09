package com.fedorrroff.tmdbportable.ui.main.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fedorrroff.tmdbportable.core.BasePresenter;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.utils.utils.NetworkUtil;

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
        if (NetworkUtil.isConnectionAvailable(mActivity.getBaseContext())) {
            mNavigator.showMainPage();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

            builder.setTitle("Load from DB?");

            builder.setPositiveButton("YES", (dialogInterface, i) -> mNavigator.showMainPage());
            builder.setNegativeButton("NO", (dialog, id) -> mNavigator.showMainPage());

            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }
}
