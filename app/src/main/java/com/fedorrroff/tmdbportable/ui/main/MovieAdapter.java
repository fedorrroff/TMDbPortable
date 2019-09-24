package com.fedorrroff.tmdbportable.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class  MovieAdapter extends RecyclerView.Adapter<MovieAdapter.SingleMovieViewHolder> {

    private final List<MovieItem> items;
    private OnItemClickListener listener;

    public MovieAdapter (final List<MovieItem> startItems) {
        items = new ArrayList<>();
        if (startItems != null) {
            items.addAll(startItems);
        }
    }

    @NonNull
    @Override
    public SingleMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_single, parent, false);
        return new SingleMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleMovieViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(final MovieItem movie) {
        items.add(movie);
        notifyDataSetChanged();
    }

    public void addAllItems(final List<MovieItem> movies) {
        items.addAll(movies);
        notifyDataSetChanged();
    }

    public void removeItem(final MovieItem movie) {
        items.remove(movie);
        notifyDataSetChanged();
    }

    public void removeAllItems() {
        items.clear();
        notifyDataSetChanged();
    }

    class SingleMovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_poster;
        private TextView tv_description;
        private TextView tv_title;
        private CardView card_view;
        private RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        public SingleMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
            card_view = itemView.findViewById(R.id.card_view);
        }

        void bind (MovieItem item, OnItemClickListener listener) {
            if (item.getPosterPath() == null) {
                iv_poster.setImageResource(R.drawable.ic_video_library_black_24dp);
            } else {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + item.getPosterPath())
                        .apply(requestOptions).into(iv_poster);
            }

            tv_description.setText(item.getOverview());
            tv_title.setText(item.getTitle());

            itemView.setOnClickListener((v) -> {
                if(listener != null) {
                    listener.onItemClick(item
                    );
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(MovieItem movie);
    }
}
