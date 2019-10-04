package com.fedorrroff.tmdbportable.di.main;

import androidx.appcompat.app.AppCompatActivity;

public class MainComponentHolder {

    private MainComponent mainComponent;

    public static MainComponentHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public void initDaggerComponent(final AppCompatActivity activity) {

        mainComponent = DaggerMainComponent.builder()
                .activityModule(new ActivityModule(activity))
                .build();
    }

    private static class InstanceHolder {

        private static final MainComponentHolder INSTANCE = new MainComponentHolder();
    }

}
