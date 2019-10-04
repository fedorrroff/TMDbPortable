package com.fedorrroff.tmdbportable.ui.popular;

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
import com.fedorrroff.tmdbportable.di.main.MainComponentHolder;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.main.MovieAdapter;
import com.fedorrroff.tmdbportable.ui.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;


public class PopularMoviesFragment extends Fragment {

    public static final String MOVIE = "MOVIE";
    public static final int SPAN_COUNT = 2;

    @Inject
    Navigator navigator;

    private final PopularMoviesFragmentPresenter popularMoviesFragmentPresenter = new PopularMoviesFragmentPresenter(this);

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);

    public static PopularMoviesFragment newInstance() {
        PopularMoviesFragment popularMoviesFragment = new PopularMoviesFragment();
        return popularMoviesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("TMDB_TAG", "onCreateView: Popular");
        return inflater.inflate(R.layout.popular_movies_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TMDB_TAG", "onViewCreated: Popular");

        recyclerView = view.findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter.setOnItemClickListener((id) -> navigator.showMovieScreen(id));

        if (savedInstanceState != null) {
            popularMoviesFragmentPresenter.downloadMovies();
        }
    }

    public void displayMovies(List<MovieItem> movies) {
        if (recyclerView != null) {
            recyclerView.post(() -> movieAdapter.addAllItems(movies));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponentHolder.getInstance().getMainComponent().inject(this);

        popularMoviesFragmentPresenter.downloadMovies();
    }
}
