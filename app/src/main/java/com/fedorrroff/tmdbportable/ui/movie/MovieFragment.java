package com.fedorrroff.tmdbportable.ui.movie;

import android.annotation.SuppressLint;
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

import static com.fedorrroff.tmdbportable.ui.main.MainPageFragment.MOVIE;

public class MovieFragment extends Fragment {

    final MovieFragmentPresenter movieFragmentPresenter = new MovieFragmentPresenter(this);

    private TextView tv_descr;
    private TextView tv_title;
    private TextView tv_rating;

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

        movieFragmentPresenter.downloadMovieInfo(movie.getId());

        tv_descr = view.findViewById(R.id.tv_description_detail);
        tv_title = view.findViewById(R.id.tv_title_detail);
        tv_rating = view.findViewById(R.id.tv_rating);
        displayMovieInfo(movie);
    }

    public void displayMovieTrailer(MovieTrailer movieTrailer) {
        ImageView youtube_btn = getView().findViewById(R.id.ib_youtube);
        youtube_btn.setOnClickListener((v) ->
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()))));
    }

    public void displayMovieInfo(MovieItem movie) {
        tv_descr.setText(movie.getOverview());
        tv_title.setText(movie.getTitle());
        tv_rating.setText(movie.getVoteAverage().toString());
    }
}
