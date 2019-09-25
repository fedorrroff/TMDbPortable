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

import static android.provider.Contacts.SettingsColumns.KEY;

public class MainPageFragment extends Fragment {

    public static final String MOVIE = "MOVIE";

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(null);
    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();

    public static MainPageFragment newInstance() {

        MainPageFragment mainPageFragment = new MainPageFragment();

        return mainPageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("M_mainPageFragment", "onCreateView: ");
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
                    .commit()
        );

    }

    private void displayMovies(List<MovieItem> movies) {
        if (recyclerView != null) {
            recyclerView.post(() -> movieAdapter.addAllItems(movies));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("M_mainPageFragment", "onCreate");
        Runnable getMoviesFromApi = () -> {
            try {
                List<MovieItem> movies = fakeRepo.getMovies();
                displayMovies(movies);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadMoviesThread = new Thread(getMoviesFromApi);
        loadMoviesThread.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("M_mainPageFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("M_mainPageFragment", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment curr = getFragmentManager().findFragmentById(R.id.fragment_movie);
        outState.putString("CURR", curr.getTag());
    }
}
