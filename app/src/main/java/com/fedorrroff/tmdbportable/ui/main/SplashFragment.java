package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.di.ActivityModule;
import com.fedorrroff.tmdbportable.di.DaggerFragmentComponent;
import com.fedorrroff.tmdbportable.di.FragmentComponent;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import javax.inject.Inject;

public class SplashFragment extends Fragment {

    @Inject
    Navigator navigator;

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

        view.postDelayed(() ->  navigator.showMainPage(), 1000);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentComponent fragmentComponent = DaggerFragmentComponent.
                builder().activityModule(new ActivityModule(getActivity())).build();
        fragmentComponent.inject(this);
    }
}
