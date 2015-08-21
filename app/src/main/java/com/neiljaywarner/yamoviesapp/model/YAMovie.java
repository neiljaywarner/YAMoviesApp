package com.neiljaywarner.yamoviesapp.model;

/**
 * Created by neil on 8/19/15.
 */
public class YAMovie {

    public final String BASE_POSTER_PATH = "http://image.tmdb.org/t/p/";
    public final String POSTER_PATH_SIZE_STRING = "w342";
    private String original_title;
    private String poster_path; //includes "/"


    public String getPosterFullUrl() {
        return BASE_POSTER_PATH + "/" + POSTER_PATH_SIZE_STRING + poster_path;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    //size is one of the following: "w92", "w154", "w185", "w342", "w500", "w780", or "original".
    // For most phones we recommend using “w185”.
    // 342 is much better for my Nexus 6.
    //so example URL is http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
}
