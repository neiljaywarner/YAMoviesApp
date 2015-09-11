package com.neiljaywarner.yamoviesapp.model;

import java.util.List;

/**
 * Handle result from REST request for retrofit.
 */
public class VideosList {

    private int id;
    private List<RelatedVideo> results;  //from JSON

    public VideosList() {
    }


    public List<RelatedVideo> getVideos() {
        return results;
    }


}
