package com.fedorrroff.tmdbportable.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.custom.decor.SpacesItemDecoration;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);
    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();

    private Runnable getMoviesFromApi = () -> {
        try {
            List<MovieItem> movies = fakeRepo.getMovies();
            displayMovies(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

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

        recyclerView = view.findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing_tiny);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        movieAdapter.setOnItemClickListener(() ->
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
                    .replace(R.id.fl_toReplace, new MovieFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
        );
    }

    private void displayMovies(List<MovieItem> movies) {
        recyclerView.post(() -> movieAdapter.addAllItems(movies));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread loadMoviesThread = new Thread(getMoviesFromApi);
        loadMoviesThread.start();
    }
}
