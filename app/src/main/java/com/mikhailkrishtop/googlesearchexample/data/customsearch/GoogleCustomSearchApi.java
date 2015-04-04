package com.mikhailkrishtop.googlesearchexample.data.customsearch;

import com.mikhailkrishtop.googlesearchexample.data.customsearch.model.CustomSearchResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
public interface GoogleCustomSearchApi {

    @GET("/customsearch/v1")
    Observable<CustomSearchResponse> search(@Query("key") String apiKey, @Query("cx") String cx,
                                            @Query("q") String query, @Query("start") int start);

}
