package com.neiljaywarner.yamoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neiljaywarner.yamoviesapp.model.YAMovie;

import java.util.List;

/**
 * Created by neil on 8/20/15.
 */
public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder> {

    private final List<YAMovie> movies;

    public MoviesRecyclerViewAdapter(List<YAMovie> movies) {
        this.movies = movies;
    }

    @Override
    public MoviesRecyclerViewAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        //false b/c it attaches to root elsewhere.

        return new MoviesRecyclerViewAdapter.MovieViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MoviesRecyclerViewAdapter.MovieViewHolder holder, int position) {
        holder.textView.setText(movies.get(position).getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewTitle);
            //TODO: ImageView only...
            //    compile 'com.felipecsl:gifimageview:1.2.0'

        }
    }

}
