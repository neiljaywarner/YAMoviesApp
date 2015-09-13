package com.neiljaywarner.yamoviesapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.neiljaywarner.yamoviesapp.model.YAMovie;

import java.util.List;

/**
 * Created by neil on 9/13/15.
 */
public class YAMApplication extends Application {

    private static final String PREFS_NAME = "YAMA_PREFS";
    private String favoritesListString;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NJW", )
    }

    public List<YAMovie> getFavorites() {
        if (TextUtils.isEmpty(favoritesListString)) {
            favoritesListString = loadFavoritesListString();

        }
    }

    private void saveSharedPreference(String prefKey, String prefString) {
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(prefKey, prefString);
        sharedPreferences.edit().commit();
    }

    private String loadStringPreference(String prefKey) {
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.getString(prefKey, )
    }

    //TODO: MOve these to a LocalRepository() class that can be a singleton that can be replaced with another db layer more easily
    // and db package/folder etc.  at least i got context and singletons for free here.

    /**
     * Save the favorite to persisted storage.
     *
     * @param movie
     */
    public void saveFavorite(YAMovie movie) {
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        saveSharedPreference(String.valueOf(movie.getId()), json);
    }

}
