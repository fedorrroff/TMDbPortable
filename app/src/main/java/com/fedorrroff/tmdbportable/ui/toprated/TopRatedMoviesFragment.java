package com.fedorrroff.tmdbportable.ui.toprated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;

public class TopRatedMoviesFragment extends Fragment {

    public static TopRatedMoviesFragment newInstance () {
        return new TopRatedMoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_rated_movies_fragment, container, false);
    }
}
