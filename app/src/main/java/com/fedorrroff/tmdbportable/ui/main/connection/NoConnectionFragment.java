package com.fedorrroff.tmdbportable.ui.main.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;
import com.fedorrroff.utils.utils.NetworkUtil;

import javax.inject.Inject;

public class NoConnectionFragment extends BaseFragment {

   @Inject NoConnectionFragmentPresenter noConnectionFragmentPresenter;

    public static NoConnectionFragment newInstance() {
        return new NoConnectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.connection_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_retry = view.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener((v) -> {
            noConnectionFragmentPresenter.nextPageConnection();
        });
    }

    @Override
    protected void injectDependencies(final FragmentPresenterComponent fragmentPresenterComponent
    ) {
        fragmentPresenterComponent.inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        noConnectionFragmentPresenter.attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
