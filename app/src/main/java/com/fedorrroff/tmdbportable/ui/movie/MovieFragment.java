package com.fedorrroff.tmdbportable.ui.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle idBundle = getArguments();
        MovieItem movie = (MovieItem) idBundle.getSerializable(MOVIE);

        Runnable getMovieDetailFromApi = () -> {
            try {
                downloadMovieInfo(movie.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread loadTrailersThread = new Thread(getMovieDetailFromApi);
        loadTrailersThread.start();
    }

    public void downloadMovieInfo(int id) throws IOException{
        displayMovieInfo(
                fakeRepo.getMovieTrailers(id).get(0)
        );
    }

    public void displayMovieInfo(MovieTrailer movieTrailer) {
        ImageView youtube_btn = getView().findViewById(R.id.ib_youtube);
        youtube_btn.setOnClickListener((v) ->
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()))));
    }

    public void displayMovieTitle(MovieItem movie) {
        //тут раскидываю по вьюхам
        //можно ли делать вызов этого метода в onViewCreate?
    }
}
