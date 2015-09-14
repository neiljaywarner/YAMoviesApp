package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.neiljaywarner.yamoviesapp.model.YAMovie;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainActivityFragment extends Fragment {

    private static final int NUM_COLUMNS_GRIDVIEW = 2;
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private static final String MOVIE_PAGE = "MOVIE_PAGE";
    private static final String PREF_SORT_TYPE = "sort_type";


    public MoviesRecyclerViewAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;
    private MoviePage mMoviePage;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //TODO: Consider rx 'replay' operator instead?
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE_PAGE, mMoviePage);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG, "View State Restored");
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
            saveCurrentSortType(MoviePageSortType.most_popular);
            updateMoviesPage(MoviePageSortType.most_popular);
            return true;
        }

        if (id == R.id.action_sort_rating) {
            saveCurrentSortType(MoviePageSortType.highest_rated);
            updateMoviesPage(MoviePageSortType.highest_rated);
            return true;
        }

        if (id == R.id.action_refresh) {
            updateMoviesPage();
            //NOTE: for now it's OK to reload/refresh when selecting the same one...

            return true;
        }

        if (id == R.id.action_favorites) {
            this.getActivity().setTitle(R.string.action_favorites);
            updateMoviesPageWithFavorites();
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMoviesPageWithFavorites() {
        YAMApplication app = (YAMApplication) getActivity().getApplication();
        List<YAMovie> favoriteMovies = app.getFavorites();
        mAdapter.setData(favoriteMovies);
    }
    private void updateMoviesPage() {
        updateMoviesPage(getCurrentSortType());
    }

    private MoviePageSortType getCurrentSortType() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String sortType = sharedPref.getString(PREF_SORT_TYPE, MoviePageSortType.most_popular.toString());
        Log.i(TAG, "current sortType=" + sortType);

        if (sortType.equals(MoviePageSortType.highest_rated.toString())) {
            return MoviePageSortType.highest_rated;
        } else {
            return MoviePageSortType.most_popular;
        }
    }

    private void saveCurrentSortType(MoviePageSortType sortType) {
        Log.i(TAG, "Saving sort type=" + sortType.toString());
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_SORT_TYPE, sortType.toString());
        editor.apply();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.myList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUM_COLUMNS_GRIDVIEW));
        mAdapter = new MoviesRecyclerViewAdapter();

        mCompositeSubscription = new CompositeSubscription();

        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            updateMoviesPage();
        } else {
            mMoviePage = savedInstanceState.getParcelable(MOVIE_PAGE);
            mAdapter.setData(mMoviePage.getMovies());

        }

        return root;

    }

    private void updateMoviesPage(MoviePageSortType moviePageSortType) {
        final MovieService movieService = MovieService.getInstance();

        if (moviePageSortType == MoviePageSortType.highest_rated) {
            this.getActivity().setTitle(R.string.highest_rated);
        } else {
            this.getActivity().setTitle(R.string.most_popular);
        }

        final Observable<MoviePage> moviePageObservable;
        if (moviePageSortType.equals(MoviePageSortType.highest_rated)) {
            moviePageObservable = movieService.getHighestRatedMoviesFirstPage();
        } else {
            moviePageObservable = movieService.getPopularMoviesFirstPage();
        }
        if (moviePageObservable == null) {
            Log.i(TAG, "retrofit observable=null");
            return;
        }
        mCompositeSubscription.add(moviePageObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<MoviePage>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "observable completed.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "observable error;" + e.getMessage());
                                Toast.makeText(getActivity().getApplicationContext(), "..Please check internet connection and refresh.", Toast.LENGTH_LONG).show();
                                //TODO: Strings.xml strings.
                            }

                            @Override
                            public void onNext(MoviePage moviePage) {

                                mMoviePage = moviePage;
                                mAdapter.setData(mMoviePage.getMovies());
                            }
                        })
        );

    }

    @Override
    public void onDestroyView() {
        mCompositeSubscription.unsubscribe();
        super.onDestroyView();
    }


}



