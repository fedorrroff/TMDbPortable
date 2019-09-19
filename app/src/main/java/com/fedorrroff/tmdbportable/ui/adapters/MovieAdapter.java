package com.fedorrroff.tmdbportable.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fedorrroff.tmdbportable.R;
import com.fedorrroff.tmdbportable.models.data.MovieItem;

import java.util.ArrayList;
import java.util.List;

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

    public void setItems(List<MovieItem> tweets) {
        items.addAll(tweets);
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

        public SingleMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

        void bind (MovieItem item) {
            if (item.getAvatar() == null) {
                iv_poster.setImageResource(R.drawable.movie_poster);
            } else {
                iv_poster.setVisibility(View.INVISIBLE);
            }

            tv_description.setText(item.getDescription());
            tv_title.setText(item.getTitle());
        }
    }
}
