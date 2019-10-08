package com.fedorrroff.tmdbportable.ui.toprated;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.custom.decor.MovieAdapter;

import java.util.List;

import javax.inject.Inject;

import static com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment.SPAN_COUNT;

public class TopRatedMoviesFragment extends BaseFragment {

    @Inject TopRatedMoviesFragmentPresenter topRatedMoviesFragmentPresenter;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);

    public static TopRatedMoviesFragment newInstance () {
        return new TopRatedMoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_rated_movies_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TMDB_TAG", "onViewCreated: Popular");

        recyclerView = view.findViewById(R.id.rw_movies_top_rated);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter.setOnItemClickListener((movieItem) -> topRatedMoviesFragmentPresenter.movieSelected(movieItem));

        if (savedInstanceState != null) {
            topRatedMoviesFragmentPresenter.downloadTopRatedMovies();
        }
    }

    @Override
    protected void injectDependencies(
            final FragmentPresenterComponent fragmentPresenterComponent
    ) {
        fragmentPresenterComponent.inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        topRatedMoviesFragmentPresenter.attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topRatedMoviesFragmentPresenter.downloadTopRatedMovies();
    }

    public void displayMovies(List<MovieItem> movies) {
        if (recyclerView != null) {
            recyclerView.post(() -> movieAdapter.addAllItems(movies));
        }
    }
}
