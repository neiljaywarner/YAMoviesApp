package com.neiljaywarner.yamoviesapp.model;

import java.util.List;

/**
 * Handle result from REST request for retrofit.
 */
public class ReviewsList {

    private int id;
    private List<Review> results;  //from JSON

    public ReviewsList() {
    }


    public List<Review> getResults() {
        return results;
    }

    //TODO: Pull this out into java generic since they are the same!! (this and VideosList!) or use the pages etc or both.

}

// example json
/*
{
id: 131631,
page: 1,
results: [
{
id: "547e6075c3a368256200022f",
author: "anthonypagan1975",
content: "It was good. Although I wish it had more action scenes. It's worth watching ago don't miss out!",
url: "http://j.mp/1vjM0BW"
},
{
id: "54d9b29c9251410a4500100a",
author: "Andres Gomez",
content: "Yet more of the same extended in an inexcusable way. Let's hope the last movie of the saga can get a proper end.",
url: "http://j.mp/16Ki1te"
}
],
total_pages: 1,
total_results: 2
}
 */