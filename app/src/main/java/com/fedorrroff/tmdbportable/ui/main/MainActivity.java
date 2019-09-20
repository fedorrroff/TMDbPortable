package com.fedorrroff.tmdbportable.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.adapters.MovieAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

        Thread loadMovies = new Thread(getMoviesFromApi);
        loadMovies.start();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rw_movies);

        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void displayMovies(List<MovieItem> movies) {
        recyclerView.post(() -> movieAdapter.setItems(movies));
    }
}
