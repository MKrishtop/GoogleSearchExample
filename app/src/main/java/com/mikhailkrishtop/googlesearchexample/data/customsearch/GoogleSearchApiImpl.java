package com.mikhailkrishtop.googlesearchexample.data.customsearch;

import com.mikhailkrishtop.googlesearchexample.data.GoogleSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.customsearch.mapper.CustomSearchResponseToSearchResponseListMapper;
import com.mikhailkrishtop.googlesearchexample.data.internal.StringPreference;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
@Singleton
public class GoogleSearchApiImpl implements GoogleSearchApi {

    private final GoogleCustomSearchApi customSearchApi;
    private final StringPreference engineId;
    private final StringPreference apiKey;

    @Inject public GoogleSearchApiImpl(GoogleCustomSearchApi customSearchApi,
                                       @Named("engineId") StringPreference engineId,
                                       @Named("apiKey") StringPreference apiKey) {
        this.customSearchApi = customSearchApi;
        this.engineId = engineId;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<List<SearchResponse>> search(String query, int start) {
        return customSearchApi.search(apiKey.get(), engineId.get(), query, start)
                .map(new CustomSearchResponseToSearchResponseListMapper());
    }

}
