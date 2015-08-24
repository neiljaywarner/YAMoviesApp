package com.neiljaywarner.yamoviesapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.neiljaywarner.yamoviesapp.model.MoviePage;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by neil on 8/24/15.
 */
public class MoviePageLoader extends AsyncTaskLoader<MoviePage> {

    private static final String TAG = "NJW";
    private MoviePage mMoviePage;
    private boolean dataIsReady = false;

    public MoviePageLoader(Context context) {
        super(context);
        Log.i(TAG, "in loader constructor");

    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "+++ onStartLoading() called! +++");
        if (dataIsReady) {
            deliverResult(mMoviePage);
        } else {
            forceLoad();
        }

    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(MoviePage moviePage) {
        Log.i(TAG, "in deliverResult");

        super.deliverResult(moviePage);

    }

    @Override
    public MoviePage loadInBackground() {

        Log.i(TAG, "loadInBackground");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org")
                .build();

        TheMovieDbMoviesService service = restAdapter.create(TheMovieDbMoviesService.class);

        mMoviePage = service.getPopularMovieFirstPage(TheMovieDb.APIKey);
        dataIsReady = true;
        // Done!
        return mMoviePage;
    }


    /**
     * http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=api_key&page=2
     */
    public interface TheMovieDbMoviesService {
        @GET("/3/discover/movie?sort_by=popularity.desc&page=1")
        MoviePage getPopularMovieFirstPage(@Query("api_key") String apiKey);
    }


}

