package com.fedorrroff.tmdbportable.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.util.ArrayList;
import java.util.List;

import static com.fedorrroff.tmdbportable.tmdbApi.Requester.BASE_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.SingleMovieViewHolder> {

    private List<MovieItem> items = new ArrayList<>();

    @NonNull
    @Override
    public SingleMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_movie_single, parent, false);
        Log.d("M_ChatAdapter", "onCreateViewHolder: ");
        return new SingleMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleMovieViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<MovieItem> movies) {
        items.addAll(movies);
        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    class SingleMovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_poster;
        private TextView tv_description;
        private TextView tv_title;
        private RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE);


        public SingleMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

        void bind (MovieItem item) {
            if (item.getPosterPath() == null) {
                iv_poster.setImageResource(R.drawable.movie_poster);
            } else {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + item.getPosterPath())
                        .apply(requestOptions).into(iv_poster);
            }

            tv_description.setText(item.getOverview());
            tv_title.setText(item.getTitle());
        }
    }
}
