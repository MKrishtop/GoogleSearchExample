package com.mikhailkrishtop.googlesearchexample.data.model;

/**
 * Created by mykhailo on 4/4/15.
 */
public class SearchResponse {

    public String url;
    public String title;

    public SearchResponse(String url, String title) {
        this.url = url;
        this.title = title;
    }

}
