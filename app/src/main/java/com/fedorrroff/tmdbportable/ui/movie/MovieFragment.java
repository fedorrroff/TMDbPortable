package com.fedorrroff.tmdbportable.ui.movie;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;
import com.fedorrroff.tmdbportable.models.data.MovieTrailer;

import static com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment.MOVIE;

public class MovieFragment extends Fragment {

    private final MovieFragmentPresenter movieFragmentPresenter = new MovieFragmentPresenter(this);

    private TextView tv_descr;
    private TextView tv_title;
    private TextView tv_rating;
    private ImageView iv_poster;

    private RequestOptions requestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

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
        Log.d("TMDB_TAG", "onCreateView: Detail");
        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("TMDB_TAG", "onViewCreated: Detail");

        Bundle idBundle = getArguments();
        MovieItem movie = (MovieItem) idBundle.getSerializable(MOVIE);

        movieFragmentPresenter.downloadMovieInfo(movie.getId());

        tv_descr = view.findViewById(R.id.tv_description_detail);
        tv_title = view.findViewById(R.id.tv_title_detail);
        tv_rating = view.findViewById(R.id.tv_rating);
        iv_poster = view.findViewById(R.id.iv_poster_detail);
        displayMovieInfo(movie);
    }

    public void displayMovieTrailer(MovieTrailer movieTrailer) {
//        ImageView youtube_btn = getView().findViewById(R.id.ib_youtube);
//        youtube_btn.setOnClickListener((v) ->
//                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()))));

        VideoView vv_trailer = getView().findViewById(R.id.vv_trailer);
        vv_trailer.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()));
        vv_trailer.setMediaController(new MediaController(getContext()));

        vv_trailer.requestFocus();
        vv_trailer.start();
    }

    public void displayMovieInfo(MovieItem movie) {
        tv_descr.setText(movie.getOverview());
        tv_title.setText(movie.getTitle());
        tv_rating.setText(movie.getVoteAverage().toString());

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .apply(requestOptions).into(iv_poster);
    }
}
