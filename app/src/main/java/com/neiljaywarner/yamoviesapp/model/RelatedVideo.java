package com.neiljaywarner.yamoviesapp.model;

/**
 * Created by neil on 9/10/15.
 * A related video such a trailer
 */
public class RelatedVideo {
    private String key;
    private String name;
    private String id;
    private String youtubeThumbnailUrl;

    public RelatedVideo(String key, String name, String id) {
        this.key = key;
        this.name = name;
        this.id = id; //TODO: Remove id if not needed.
    }

    public static RelatedVideo getTestVideo() {
        return new RelatedVideo("G0wGs3useV8", "Hunger games..test..test", "54300b340e0a2646400007e6");
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * @return Youtube still image for video
     * such as http://i1.ytimg.com/vi/G0wGs3useV8/default.jpg
     */
    public String getYoutubeThumbnailUrl() {
        return "http://i1.ytimg.com/vi/" + this.getKey() + "/default.jpg";
    }
}


/*
e.g.
id: "54300b340e0a2646400007e6",
iso_639_1: "en",
key: "C_Tsj_wTJkQ",
name: "The Hunger Games: Mockingjay Trailer – “The Mockingjay Lives”",
site: "YouTube",
size: 720,
type: "Trailer"

// from http://api.themoviedb.org/3/movie/131631/videos?api_key=blahblah
 */
