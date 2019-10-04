package com.fedorrroff.tmdbportable.ui.main.splash;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;

import javax.inject.Inject;

public class SplashFragment extends BaseFragment {

    @Inject SplashFragmentPresenter splashFragmentPresenter;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TMDB_TAG", "onCreateView: Main Fragment");
        return inflater.inflate(R.layout.splash_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.postDelayed(() ->  {
            splashFragmentPresenter.nextPageSplash();
        }, 1000);
    }

    @Override
    protected void injectDependencies(final FragmentPresenterComponent fragmentPresenterComponent
    ) {
        fragmentPresenterComponent.inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        splashFragmentPresenter.attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
