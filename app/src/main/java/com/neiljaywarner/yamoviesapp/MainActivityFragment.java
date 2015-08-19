package com.neiljaywarner.yamoviesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neiljaywarner.yamoviesapp.model.MoviePage;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        MoviePage moviePage = MoviePage.getDummyMoviePage3();
        Log.i("NJW", "Movie1 Original Title=" + moviePage.getMovie(0).getOriginalTitle());
        Log.i("NJW", "Movie2 Poster Path=" + moviePage.getMovie(1).getPosterFullUrl());


        return inflater.inflate(R.layout.fragment_main, container, false);
    }


}
