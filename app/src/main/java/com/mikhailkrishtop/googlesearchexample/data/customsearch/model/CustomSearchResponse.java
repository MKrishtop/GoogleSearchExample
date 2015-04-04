package com.mikhailkrishtop.googlesearchexample.data.customsearch.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by mykhailo on 4/4/15.
 */
public class CustomSearchResponse {

    @Expose
    public List<SearchItem> items;

    public static class SearchItem {
        @Expose
        public String link;
        @Expose
        public String title;
    }
}
