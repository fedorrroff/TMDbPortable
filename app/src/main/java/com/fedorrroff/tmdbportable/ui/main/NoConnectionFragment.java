package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.di.main.MainComponentHolder;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;
import com.fedorrroff.tmdbportable.utils.NetworkUtil;

import javax.inject.Inject;

public class NoConnectionFragment extends Fragment {

    @Inject
    Navigator navigator;

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
            if (NetworkUtil.getConnectivityStatus(getContext())) {
                navigator.showMainPage();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponentHolder.getInstance().getMainComponent().inject(this);
    }
}
