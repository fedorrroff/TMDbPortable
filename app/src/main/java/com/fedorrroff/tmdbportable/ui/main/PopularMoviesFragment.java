package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.di.ActivityModule;
import com.fedorrroff.tmdbportable.di.FragmentComponent;
import com.fedorrroff.tmdbportable.di.DaggerFragmentComponent;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;


public class PopularMoviesFragment extends Fragment {

    public static final String MOVIE = "MOVIE";

    @Inject
    Navigator navigator;

    private final PopularMoviesFragmentPresenter popularMoviesFragmentPresenter = new PopularMoviesFragmentPresenter(this);

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);

    public static PopularMoviesFragment newInstance() {
        PopularMoviesFragment mainPageFragment = new PopularMoviesFragment();
        return mainPageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("M_mainPageFragment", "onViewCreated");

        popularMoviesFragmentPresenter.downloadMovies();

        recyclerView = view.findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter.setOnItemClickListener((id) -> navigator.showMovieScreen(id));
    }

    public void displayMovies(List<MovieItem> movies) {
        if (recyclerView != null) {
            recyclerView.post(() -> movieAdapter.addAllItems(movies));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentComponent fragmentComponent = DaggerFragmentComponent.
                builder().activityModule(new ActivityModule(getActivity())).build();
        fragmentComponent.inject(this);
    }
}
