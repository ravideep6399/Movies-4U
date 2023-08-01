package com.example.finalone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalone.databinding.ItemMoviesBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private LayoutInflater layoutInflater;
    private final List<Movie> movies;
    private Context context;
    public MovieAdapter(@NonNull List<Movie> movies) {
        this.movies = movies;
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemMoviesBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.item_movies,parent,false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.binding.tvMovieTitle.setText(movies.get(position).getTitle());
        holder.binding.rating.setText(Float.toString(movies.get(position).getVotes()));
        Glide.with(holder.binding.ivMoviePoster.getContext())
                .load(movies.get(position).getLargePosterUrl())
                .apply(new RequestOptions().placeholder(R.drawable.popcorn))
                .into(holder.binding.ivMoviePoster);
//        holder.binding.ivMoviePoster.setImageResource(Integer.parseInt(movies.get(position).getLargePosterUrl()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMoviesBinding binding;
        public MovieViewHolder(@NonNull ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
