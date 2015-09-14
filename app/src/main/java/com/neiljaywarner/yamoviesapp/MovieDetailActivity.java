package com.neiljaywarner.yamoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.neiljaywarner.yamoviesapp.model.YAMovie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie_extra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

    }

    public YAMovie getMovie() {
        return getIntent().getExtras().getParcelable(EXTRA_MOVIE);
    }



}
