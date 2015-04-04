package com.mikhailkrishtop.googlesearchexample.data.html.mapper;

import com.mikhailkrishtop.googlesearchexample.data.html.model.HtmlSearchResponse;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by mykhailo on 4/4/15.
 */
public class HtmlSearchResponseToSearchResponeListMapper implements Func1<HtmlSearchResponse, List<SearchResponse>> {

    @Override
    public List<SearchResponse> call(HtmlSearchResponse htmlSearchResponse) {
        return null; //TODO
    }

}
