package com.mikhailkrishtop.googlesearchexample.data.html;

import com.mikhailkrishtop.googlesearchexample.data.html.model.HtmlSearchResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
public interface HtmlGoogleSearchApi {

    @GET("/customsearch/v1")
    Observable<HtmlSearchResponse> search(@Query("q") String query, @Query("start") int start);

}
