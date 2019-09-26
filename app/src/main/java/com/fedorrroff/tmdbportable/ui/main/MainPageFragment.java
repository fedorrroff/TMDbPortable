package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.ui.custom.decor.SpacesItemDecoration;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;

import java.util.List;

public class MainPageFragment extends Fragment {

    public static final String MOVIE = "MOVIE";

    private final MainPageFragmentPresenter mainPageFragmentPresenter = new MainPageFragmentPresenter(this);

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);

    public static MainPageFragment newInstance() {
        MainPageFragment mainPageFragment = new MainPageFragment();
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

        recyclerView = view.findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing_tiny);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        movieAdapter.setOnItemClickListener((id) ->
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
                    .replace(R.id.fl_toReplace, MovieFragment.newInstance(id))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
        );

        mainPageFragmentPresenter.downloadMovies();
    }

    public void displayMovies(List<MovieItem> movies) {
        if (recyclerView != null) {
            recyclerView.post(() -> movieAdapter.addAllItems(movies));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
