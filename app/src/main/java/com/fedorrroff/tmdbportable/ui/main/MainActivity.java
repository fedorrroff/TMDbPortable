package com.fedorrroff.tmdbportable.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.custom.decor.SpacesItemDecoration;
import com.fedorrroff.tmdbportable.ui.movie.MovieFragment;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter = new MovieAdapter();
    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();

    private  RecyclerView recyclerView;

    private Runnable getMoviesFromApi = () -> {
        try {
            List<MovieItem> movies = fakeRepo.getMovies();
            displayMovies(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        Thread loadMoviesThread = new Thread(getMoviesFromApi);
        loadMoviesThread.start();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing_tiny);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        movieAdapter.setOnItemClickListener(() -> {
            MovieFragment eventFragment = new MovieFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cl_main, eventFragment).commit();
        });
    }

    private void displayMovies(List<MovieItem> movies) {
        recyclerView.post(() -> movieAdapter.setItems(movies));
    }
}
