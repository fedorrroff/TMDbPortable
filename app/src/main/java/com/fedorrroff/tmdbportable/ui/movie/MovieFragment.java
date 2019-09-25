package com.fedorrroff.tmdbportable.ui.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.models.data.MovieTrailer;
import com.fedorrroff.tmdbportable.repositories.FakeMovieRepository;
import com.fedorrroff.tmdbportable.repositories.MovieRepository;

import java.io.IOException;

import static com.fedorrroff.tmdbportable.ui.main.MainPageFragment.MOVIE;

public class MovieFragment extends Fragment {

    private MovieRepository fakeRepo = FakeMovieRepository.getInstance();

    private ImageView iv;
    private TextView tv_title;
    private TextView tv_rating;
    private TextView tv_descr;
    private MovieItem movieSolo;

    public static MovieFragment newInstance(MovieItem movie) {
        MovieFragment movieFragment= new MovieFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(MOVIE, movie);
        movieFragment.setArguments(arguments);

        return movieFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("M_movieFragment", "onCreateView");
        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle idBundle = getArguments();
        MovieItem movie = (MovieItem) idBundle.getSerializable(MOVIE);

        Runnable getMovieDetailFromApi = () -> {
            try {
                downloadMovieInfo(movie.getId(), view);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadTrailersThread = new Thread(getMovieDetailFromApi);
        loadTrailersThread.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("M_movieFragment", "onCreate");
    }

    public void downloadMovieInfo(int id, View view) throws IOException{
        displayMovieTrailer(
                fakeRepo.getMovieTrailers(id).get(0), view
        );
    }

    public void displayMovieTrailer(MovieTrailer movieTrailer, View view) {
        ImageView youtube_btn = view.findViewById(R.id.ib_youtube);
        youtube_btn.setOnClickListener((v) ->
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()))));
    }

    public void displayMovieInfo(MovieItem movie, View view) {
        tv_descr = view.findViewById(R.id.tv_description_detail);
        tv_descr.setText(movie.getOverview());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
