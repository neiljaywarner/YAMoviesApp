package com.neiljaywarner.yamoviesapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.neiljaywarner.yamoviesapp.model.YAMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 9/13/15.
 */
public class YAMApplication extends Application {

    private static final String PREFS_NAME = "yama_prefs";
    private static final String FAVORITES_LIST_STRING = "favorites_list_string";
    private String mFavoritesListString;
    private List<YAMovie> mFavoriteMovies = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NJW", "app onCreate()");
        mFavoritesListString = loadFavoritesListString(); //initializing/loading string from prefs.
        mFavoriteMovies = getFavorites();
    }

    /**
     * @return List of Favorite Movies user has chosen.
     */
    public List<YAMovie> getFavorites() {
        if (mFavoriteMovies == null || mFavoriteMovies.size() == 0) {
            mFavoritesListString = loadFavoritesListString();
        } else {
            return mFavoriteMovies;
        }

        if (TextUtils.isEmpty(mFavoritesListString)) {
            return null; //if still b/c there are no favorites, then there are none, return null.
        }

        mFavoriteMovies = new ArrayList<>();
        for (String movieId : mFavoritesListString.split(",")) {
            String movieJson = getStringPreference(movieId);
            YAMovie movie = YAMovie.newMovieFromJson(movieJson);
            mFavoriteMovies.add(movie);
        }
        return mFavoriteMovies;
    }

    /**
     *
     * @return List of Movie Ids.
     */
    private String loadFavoritesListString() {
        String csvString = getStringPreference(FAVORITES_LIST_STRING);
        Log.i("NJW", "-->Getting favorites list String:'" + csvString);
        mFavoritesListString = csvString;
        return csvString;
    }

    private void saveFavoritesListString(String csvString) {
        Log.i("NJW", "-->Saving favorites list String:'" + csvString);
        mFavoritesListString = csvString;
        saveSharedPreference(FAVORITES_LIST_STRING, csvString);
    }

    /**
     * Save CSV List to xml prefs storage from list of movies.
     *
     * @param movies
     */
    private void saveFavoritesList(List<YAMovie> movies) {
        Log.i("NJW", "About to save favorites list, size:" + movies.size());
        String favoritesListString = "";
        for (YAMovie movie : movies) {
            favoritesListString += movie.getId() + ",";
        }

        Log.i("NJW", "favoritesListString='" + favoritesListString + "'");
        if (movies.size() > 0) {
            favoritesListString = favoritesListString.substring(0, favoritesListString.length() - 1);
        }
        Log.i("NJW", "favoritesListString='" + favoritesListString + "'");

        saveFavoritesListString(favoritesListString);
    }


    private void saveSharedPreference(String prefKey, String prefString) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(prefKey, prefString);
        Log.i("NJW", "Savign " + prefKey + "/'" + prefString + "'");
        sharedPreferences.edit().commit();
    }

    private String getStringPreference(String prefKey) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String prefString = sharedPreferences.getString(prefKey, "");
        Log.i("NJW", "gettign " + prefKey + "/" + prefString);

        return prefString;
    }

    //TODO: MOve these to a LocalRepository() class that can be a singleton that can be replaced with another db layer more easily
    // and db package/folder etc.  at least i got context and singletons for free here.

    /**
     * Save the favorite to persisted storage.
     *
     * @param movie
     */
    public void saveFavorite(YAMovie movie) {
        if (mFavoriteMovies == null) {
            mFavoriteMovies = new ArrayList<>();
        }
        mFavoriteMovies.add(movie);
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        saveSharedPreference(String.valueOf(movie.getId()), json);
        saveFavoritesList(mFavoriteMovies);
    }

    public void removeFavorite(YAMovie movie) {
        Log.i("NJW", "About to remove from list size:" + mFavoriteMovies.size());
        mFavoriteMovies.remove(movie);
        Log.i("NJW", "List now size:" + mFavoriteMovies.size());

        saveFavoritesList(mFavoriteMovies);
    }

    public boolean isFavorite(YAMovie movie) {
        if (mFavoriteMovies == null || mFavoriteMovies.isEmpty()) {
            return false;
        }
        Log.i("NJW", "Looking for:" + movie.getId());
        String testString = "," + mFavoritesListString + ",";
        Log.i("NJW", "in " + testString);
        return testString.contains("," + movie.getId() + ",");
    }

}
