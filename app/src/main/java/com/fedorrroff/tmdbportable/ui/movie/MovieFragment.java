package com.fedorrroff.tmdbportable.ui.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;
import com.fedorrroff.tmdbportable.ui.main.MainPageFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();
    private List<MovieTrailer> trailers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieTrailer trailerExample = trailers.get(1);

        ImageView youtube_btn = view.findViewById(R.id.ib_youtube);
        youtube_btn.setOnClickListener((v) ->
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailerExample.getKey()))));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable getMoviesFromApi = () -> {
            try {
                trailers = fakeRepo.getMovieTrailers(getArguments().getInt(MainPageFragment.MOVIE_ID));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadMoviesThread = new Thread(getMoviesFromApi);
        loadMoviesThread.start();
    }
}
