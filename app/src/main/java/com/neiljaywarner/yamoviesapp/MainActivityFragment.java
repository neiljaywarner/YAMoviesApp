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


    public MoviesRecyclerViewAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;

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
            updateMoviesPage();
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
        //  ((MoviePageLoader) getLoaderManager().initLoader(0, null, this)).load(MoviePageSortType.most_popular);

    }

    public void loadHighestRated() {
        Log.i(TAG, "load highest rated.");

        //  ((MoviePageLoader) getLoaderManager().initLoader(0, null, this)).load(MoviePageSortType.highest_rated);

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

        updateMoviesPage();
        return root;

        //TODO: CHeck network connectivity at appropriate time.
    }

    private void updateMoviesPage() {
        //        mAdapter.setData(data);
        final MovieService movieService = new MovieService();
        final Observable<MoviePage> moviePageObservable = movieService.getPopularMovieFirstPage(TheMovieDb.APIKey);
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
                                Log.i("NJW", "observable error.");

                            }

                            @Override
                            public void onNext(MoviePage moviePage) {
                                Log.i("NJW", "we 'have' a moviepage->first movie=" +
                                        moviePage.getMovie(0).getOriginalTitle());
                                mAdapter.setData(moviePage);
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



