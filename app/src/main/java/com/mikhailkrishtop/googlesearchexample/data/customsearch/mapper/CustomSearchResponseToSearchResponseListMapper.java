package com.mikhailkrishtop.googlesearchexample.data.customsearch.mapper;

import com.mikhailkrishtop.googlesearchexample.data.customsearch.model.CustomSearchResponse;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by mykhailo on 4/4/15.
 */
public class CustomSearchResponseToSearchResponseListMapper implements Func1<CustomSearchResponse, List<SearchResponse>> {

    @Override
    public List<SearchResponse> call(CustomSearchResponse customSearchResponse) {
        List<SearchResponse> searchResponses = new ArrayList<>();

        for (CustomSearchResponse.SearchItem searchItem : customSearchResponse.items) {
            searchResponses.add(new SearchResponse(searchItem.link, searchItem.title));
        }

        return searchResponses;
    }

}
