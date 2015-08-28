package com.neiljaywarner.yamoviesapp;

import android.util.Log;

import com.neiljaywarner.yamoviesapp.model.MoviePage;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by neil on 8/28/15.
 */
public class MovieService {

    private TheMovieDbMoviesService mMoviesWebService;

    public MovieService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.themoviedb.org")
                .build();

        mMoviesWebService = restAdapter.create(TheMovieDbMoviesService.class);


    }

    //TODO: Error out if not successful, etc.
    public Observable<MoviePage> getPopularMovieFirstPage(String apiKey) {
        Log.i("NJW", "get Observable in MovieService");
        return mMoviesWebService.getPopularMovieFirstPage(apiKey);
    }

    /**
     * http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=api_key&page=2
     */
    public interface TheMovieDbMoviesService {
        @GET("/3/discover/movie?sort_by=popularity.desc&page=1")
        Observable<MoviePage> getPopularMovieFirstPage(@Query("api_key") String apiKey);

        @GET("/3/discover/movie?sort_by=vote_average.desc&page=1")
        MoviePage getHighestRated(@Query("api_key") String apiKey);


    }

    //using

}