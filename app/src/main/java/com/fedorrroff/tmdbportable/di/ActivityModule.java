package com.fedorrroff.tmdbportable.di;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.tmdbportable.ui.navigation.NavigatorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final FragmentActivity mActivity;

    public ActivityModule(final FragmentActivity activity) {
        mActivity = activity;
    }

    @Provides
    public FragmentActivity provideActivity() {
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
