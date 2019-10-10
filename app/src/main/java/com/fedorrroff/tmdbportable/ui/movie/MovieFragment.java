package com.fedorrroff.tmdbportable.ui.movie;

import android.content.Intent;
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
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.core.BaseFragment;
import com.fedorrroff.tmdbportable.di.FragmentPresenterComponent;
import com.fedorrroff.models.data.MovieItem;
import com.fedorrroff.models.data.MovieTrailer;

import javax.inject.Inject;

import static com.fedorrroff.tmdbportable.ui.popular.PopularMoviesFragment.MOVIE;

public class MovieFragment extends BaseFragment {

    @Inject MovieFragmentPresenter movieFragmentPresenter;

    private TextView tv_descr;
    private TextView tv_title;
    private TextView tv_rating;
    private ImageView iv_poster;
    private ImageView iv_background;
    private RequestOptions requestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public static MovieFragment newInstance(MovieItem movie) {
        MovieFragment movieFragment= new MovieFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(MOVIE, movie);
        movieFragment.setArguments(arguments);

        return movieFragment;
    }

    @Override
    protected void injectDependencies(final FragmentPresenterComponent fragmentPresenterComponent
    ) {
        fragmentPresenterComponent.inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        movieFragmentPresenter.attachView(this);
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


        iv_background = view.findViewById(R.id.iv_background_detail);
        tv_descr = view.findViewById(R.id.tv_description_detail);
        tv_title = view.findViewById(R.id.tv_title_detail);
        tv_rating = view.findViewById(R.id.tv_rating);
        iv_poster = view.findViewById(R.id.iv_poster_detail);

        movieFragmentPresenter.downloadMovieInfo(movie.getId());

        displayMovieInfo(movie);
    }

    public void displayMovieTrailer(MovieTrailer movieTrailer) {
        final View view = getView();
        if (view != null){
            ImageView youtube_btn = getView().findViewById(R.id.ib_youtube);
            youtube_btn.setOnClickListener((v) ->
                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKey()))));
        }
    }


    public void displayMovieInfo(MovieItem movie) {
        tv_descr.setText(movie.getOverview());
        tv_title.setText(movie.getTitle());
        tv_rating.setText(movie.getVoteAverage().toString());

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .apply(requestOptions).into(iv_poster);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getBackdropPath())
                .apply(requestOptions).into(iv_background);
    }
}
