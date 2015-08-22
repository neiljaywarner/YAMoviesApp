package com.neiljaywarner.yamoviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neil on 8/19/15.
 */
public class YAMovie implements Parcelable {

    public static final Parcelable.Creator<YAMovie> CREATOR = new Parcelable.Creator<YAMovie>() {
        public YAMovie createFromParcel(Parcel source) {
            return new YAMovie(source);
        }

        public YAMovie[] newArray(int size) {
            return new YAMovie[size];
        }
    };
    public final String BASE_POSTER_PATH = "http://image.tmdb.org/t/p/";
    public final String POSTER_PATH_SIZE_STRING = "w342";
    private String original_title;
    private String poster_path; //includes "/"
    private String overview;
    private String release_date;
    private float vote_average;

    public YAMovie() {
    }


    protected YAMovie(Parcel in) {

        this.original_title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readFloat();
    }

    public String getPosterFullUrl() {
        return BASE_POSTER_PATH + "/" + POSTER_PATH_SIZE_STRING + poster_path;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    //size is one of the following: "w92", "w154", "w185", "w342", "w500", "w780", or "original".
    // For most phones we recommend using “w185”.
    // 342 is much better for my Nexus 6.
    //so example URL is http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg

    public String getYear() {
        return release_date.substring(0, 4);
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeFloat(this.vote_average);
    }
}
