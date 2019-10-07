package com.fedorrroff.tmdbportable.di.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.tmdbportable.ui.navigation.NavigatorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(final AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    public AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    public Navigator provideNavigator() {
        return new NavigatorImpl(mActivity);
    }
}