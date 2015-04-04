package com.mikhailkrishtop.googlesearchexample.data.html;

import com.mikhailkrishtop.googlesearchexample.data.GoogleSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.html.mapper.HtmlSearchResponseToSearchResponeListMapper;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.List;

import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
@Singleton
public class GoogleSearchApiImpl implements GoogleSearchApi{

    private final HtmlGoogleSearchApi searchApi;

    public GoogleSearchApiImpl(HtmlGoogleSearchApi searchApi) {
        this.searchApi = searchApi;
    }

    @Override
    public Observable<List<SearchResponse>> search(String query, int start) {
        return searchApi.search(query, start)
                .map(new HtmlSearchResponseToSearchResponeListMapper());
    }

}
