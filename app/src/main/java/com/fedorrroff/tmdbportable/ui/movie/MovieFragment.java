package com.fedorrroff.tmdbportable.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.ui.navigation.NavigatorImpl;

public class MovieFragment extends Fragment {

    final MovieFragmentPresenter movieFragmentPresenter = new MovieFragmentPresenter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    void displayMovieInfo(Object object, Object object1) {

    }
}
