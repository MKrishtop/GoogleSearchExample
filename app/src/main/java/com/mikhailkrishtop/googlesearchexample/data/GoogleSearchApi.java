package com.mikhailkrishtop.googlesearchexample.data;

import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
public interface GoogleSearchApi {

    Observable<List<SearchResponse>> search(String query, int start);

}
