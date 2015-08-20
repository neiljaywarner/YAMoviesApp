package com.neiljaywarner.yamoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.neiljaywarner.yamoviesapp.model.YAMovie;
import com.squareup.picasso.Picasso;

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
        //  holder.textView.setText(movies.get(position).getOriginalTitle());
        String posterUrl = movies.get(position).getPosterFullUrl();
        //    Picasso.with(holder.imageViewPoster.getContext())
        //           .load(posterUrl).into(holder.imageViewPoster);
        Picasso.with(holder.imageViewPoster.getContext()).load(posterUrl).into(holder.imageViewPoster);

        //TODO: Loading Image and fail image if it were in production,
        // possible fail image animated 10,9,8 gif via compile 'com.felipecsl:gifimageview:1.2.0'
        // https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0CAcQjRxqFQoTCOXStcKruMcCFUwakgodsBwMvA&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26cad%3Drja%26uact%3D8%26ved%3D0CAcQjRxqFQoTCOXStcKruMcCFUwakgodsBwMvA%26url%3Dhttp%253A%252F%252Fwww.tejadirects.com%252F%26ei%3DDCPWVeWcHsy0yASwubDgCw%26psig%3DAFQjCNGKqm2eloa8vAIt9GNbAlIHJWASaQ%26ust%3D1440183434178045&ei=DCPWVeWcHsy0yASwubDgCw&psig=AFQjCNGKqm2eloa8vAIt9GNbAlIHJWASaQ&ust=1440183434178045
        // or something better.

        //or maybe http://www.mycutegraphics.com/graphics/movie/movie-night.html
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

            // Question: Should they be format imageview_poster for best practice?

        }
    }

}
