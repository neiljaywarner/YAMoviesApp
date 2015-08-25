package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neiljaywarner.yamoviesapp.model.MoviePage;


public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<MoviePage> {

    private static final int NUM_COLUMNS_GRIDVIEW = 2;
    private static final String TAG = MainActivityFragment.class.getSimpleName();


    public MoviesRecyclerViewAdapter mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (isOnline(this.getContext())) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            Toast.makeText(this.getContext(), "Please check internet connection and try again.", Toast.LENGTH_LONG).show();
            this.getActivity().finish();
            //TODO: Dialog or snackbar.
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_sort_popularity) {
            this.getActivity().setTitle(R.string.most_popular);
            loadMostPopular();
            return true;
        }

        if (id == R.id.action_sort_rating) {
            this.getActivity().setTitle(R.string.highest_rated);
            loadHighestRated();
            return true;
        }
        //NOTE: for now it's OK to reload/refresh when selecting the same one...

        return super.onOptionsItemSelected(item);
    }

    public void loadMostPopular() {
        Log.i(TAG, "load most popular");
        ((MoviePageLoader) getLoaderManager().initLoader(0, null, this)).load(MoviePageSortType.most_popular);

    }

    public void loadHighestRated() {
        Log.i(TAG, "load highest rated.");

        ((MoviePageLoader) getLoaderManager().initLoader(0, null, this)).load(MoviePageSortType.highest_rated);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.myList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUM_COLUMNS_GRIDVIEW));
        mAdapter = new MoviesRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        this.getActivity().setTitle(R.string.most_popular);
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
        mAdapter.setData(data);


    }

    @Override
    public void onLoaderReset(Loader<MoviePage> loader) {
        mAdapter.setData(null);

    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }



}



