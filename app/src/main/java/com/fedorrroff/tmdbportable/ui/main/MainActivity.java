package com.fedorrroff.tmdbportable.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.ui.adapters.MovieAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.rw_movies);

        RecyclerView.Adapter movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
