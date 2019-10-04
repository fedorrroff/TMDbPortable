package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class MainPageFragment extends BaseFragment {

    @Inject
    MainPageFragmentPresenter presenter;

    public static MainPageFragment newInstance() {
        MainPageFragment mainPageFragment = new MainPageFragment();
        return mainPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TMDB_TAG", "onCreate: Main Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TMDB_TAG", "onCreateView: Main Fragment");
        return inflater.inflate(R.layout.main_page_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TMDB_TAG", "onViewCreated: Main Fragment");
        ViewPager pager = view.findViewById(R.id.pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        pager.setAdapter(new ViewPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected void injectDependencies(final FragmentPresenterComponent fragmentPresenterComponent
    ) {
        fragmentPresenterComponent.inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        presenter.attachView(this);
    }
}
