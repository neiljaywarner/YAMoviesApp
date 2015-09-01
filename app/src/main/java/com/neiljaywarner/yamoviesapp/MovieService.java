package com.neiljaywarner.yamoviesapp;

import android.util.Log;

import com.neiljaywarner.yamoviesapp.model.MoviePage;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;


public class MovieService {

    private static MovieService sInstance = new MovieService();
    private static Observable<MoviePage> sHighestRatedMoviesFirstPage;
    private static Observable<MoviePage> sPopularMoviesFirstPage;
    private static String sApiKey = TheMovieDb.APIKey;

    private TheMovieDbMoviesService mMoviesWebService;

    private MovieService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.themoviedb.org")
                .build();

        mMoviesWebService = restAdapter.create(TheMovieDbMoviesService.class);


    }

    public static MovieService getInstance() {
        return sInstance;
    }

    public Observable<MoviePage> getPopularMoviesFirstPage() {
        Log.i("NJW", "get Observable in MovieService");
        if (sPopularMoviesFirstPage == null) {
            sPopularMoviesFirstPage = mMoviesWebService.getPopularMoviesFirstPage(sApiKey);
        }
        return sPopularMoviesFirstPage;
    }

    public Observable<MoviePage> getHighestRatedMoviesFirstPage() {
        Log.i("NJW", "get Observable in MovieService");
        if (sHighestRatedMoviesFirstPage == null) {
            sHighestRatedMoviesFirstPage = mMoviesWebService.getHighestRatedMoviesFirstPage(sApiKey);
        }
        return sHighestRatedMoviesFirstPage;
    }

    /**
     * http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=api_key&page=2
     */
    public interface TheMovieDbMoviesService {
        @GET("/3/discover/movie?sort_by=popularity.desc&page=1")
        Observable<MoviePage> getPopularMoviesFirstPage(@Query("api_key") String apiKey);

        @GET("/3/discover/movie?sort_by=vote_average.desc&page=1")
        Observable<MoviePage> getHighestRatedMoviesFirstPage(@Query("api_key") String apiKey);


    }


}
