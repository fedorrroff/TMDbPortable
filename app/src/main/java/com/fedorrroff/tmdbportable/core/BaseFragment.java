package com.fedorrroff.tmdbportable.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.di.DaggerFragmentPresenterComponent;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;
import com.fedorrroff.tmdbportable.di.main.MainComponentHolder;

public abstract class BaseFragment extends Fragment {

    protected FragmentPresenterComponent getPresenterComponent() {
        return DaggerFragmentPresenterComponent
                .builder()
                .mainComponent(MainComponentHolder.getInstance().getMainComponent())
                .build();
    }

    protected abstract void injectDependencies(FragmentPresenterComponent fragmentPresenterComponent);

    public abstract void attachViewToPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(getPresenterComponent());
        attachViewToPresenter();
    }
}
