package com.neiljaywarner.yamoviesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neiljaywarner.yamoviesapp.model.MoviePage;


public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<MoviePage> {

    private static final int NUM_COLUMNS_GRIDVIEW = 2;
    //   private static final String TAG = MainActivityFragment.class.getSimpleName();
    private static final String TAG = "NJW";

    public static MoviePageSortType sCurrentSortType = MoviePageSortType.highest_rated;
    //TODO: What's the right way to do this?

    public MainActivityFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
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


    @Override
    public Loader<MoviePage> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "in onCreateLoader");


        return new MoviePageLoader(getActivity());

    }

    @Override
    public void onLoadFinished(Loader<MoviePage> loader, MoviePage data) {
        Log.i(TAG, "in onLoadFinished");
        // mAdapter.setData(data.getMovies());
        Log.i("NJW", "firstTile=" + data.getMovie(1).getOriginalTitle());


    }

    @Override
    public void onLoaderReset(Loader<MoviePage> loader) {

    }



}



