package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainActivityFragment extends Fragment {

    private static final int NUM_COLUMNS_GRIDVIEW = 2;
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private static final String MOVIE_PAGE = "MOVIE_PAGE";


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
        Log.i("NJW", "View State Restored");
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
            updateMoviesPage(MoviePageSortType.most_popular);
            saveCurrentSortType(MoviePageSortType.most_popular);
            return true;
        }

        if (id == R.id.action_sort_rating) {
            updateMoviesPage(MoviePageSortType.highest_rated);
            saveCurrentSortType(MoviePageSortType.highest_rated);
            return true;
        }

        if (id == R.id.action_refresh) {
            updateMoviesPage();

            return true;
        }
        //NOTE: for now it's OK to reload/refresh when selecting the same one...

        return super.onOptionsItemSelected(item);
    }

    private void updateMoviesPage() {
        updateMoviesPage(getCurrentSortType());
    }

    private MoviePageSortType getCurrentSortType() {
        String sortType = this.getActivity().getApplication().getSharedPreferences("yama", Context.MODE_PRIVATE).getString("sort_type", "");
        if (sortType.equals(MoviePageSortType.highest_rated.toString())) {
            return MoviePageSortType.highest_rated;
        } else {
            return MoviePageSortType.most_popular;
        }
    }

    private void saveCurrentSortType(MoviePageSortType sortType) {
        this.getActivity().getApplication().getSharedPreferences("yama", Context.MODE_PRIVATE).edit().putString("sort_type", sortType.toString()).commit();


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
        this.getActivity().setTitle(R.string.most_popular);

        if (savedInstanceState == null) {
            updateMoviesPage(MoviePageSortType.most_popular); //TODO: Use sharedpreferences
        } else {
            mMoviePage = savedInstanceState.getParcelable(MOVIE_PAGE);
            mAdapter.setData(mMoviePage);

        }

        return root;

    }

    private void updateMoviesPage(MoviePageSortType moviePageSortType) {
        final MovieService movieService = MovieService.getInstance();
        this.getActivity().setTitle(moviePageSortType.toString());
        final Observable<MoviePage> moviePageObservable;
        if (moviePageSortType.equals(MoviePageSortType.highest_rated)) {
            moviePageObservable = movieService.getHighestRatedMoviesFirstPage(TheMovieDb.APIKey);
        } else {
            moviePageObservable = movieService.getPopularMoviesFirstPage(TheMovieDb.APIKey);
        }
        if (moviePageObservable == null) {
            Log.i("NJW", "retrofit observable=null; airplane mode etd?");
            return;
        }
        mCompositeSubscription.add(moviePageObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<MoviePage>() {
                            @Override
                            public void onCompleted() {
                                Log.i("NJW", "observable completed.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("NJW", "observable error;" + e.getMessage());
                                Toast.makeText(getActivity().getApplicationContext(), "..Please check internet connection and refresh.", Toast.LENGTH_LONG).show();
                                //TODO: Strings.xml strings.
                            }

                            @Override
                            public void onNext(MoviePage moviePage) {
                                Log.i("NJW", "we 'have' a moviepage->first movie=" +
                                        moviePage.getMovie(0).getOriginalTitle());
                                mMoviePage = moviePage;
                                mAdapter.setData(mMoviePage);
                            }
                        })
        );

    }

    @Override
    public void onDestroyView() {
        mCompositeSubscription.unsubscribe();
        super.onDestroyView();
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



