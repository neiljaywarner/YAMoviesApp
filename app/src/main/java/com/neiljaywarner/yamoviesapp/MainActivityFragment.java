package com.neiljaywarner.yamoviesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neiljaywarner.yamoviesapp.model.MoviePage;


public class MainActivityFragment extends Fragment {

    private static final int NUM_COLUMNS_GRIDVIEW = 2;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        MoviePage moviePage = MoviePage.getDummyMoviePage3();

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.myList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUM_COLUMNS_GRIDVIEW));
        recyclerView.setAdapter(new MoviesRecyclerViewAdapter(moviePage.getMovies()));
        return root;

        //TODO: CHeck network connectivity at appropriate time.
    }


}
