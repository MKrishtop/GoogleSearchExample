package com.mikhailkrishtop.googlesearchexample;

import com.google.gson.Gson;
import com.mikhailkrishtop.googlesearchexample.data.customsearch.GoogleCustomSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.customsearch.model.CustomSearchResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.http.Query;
import rx.Observable;

/**
 * Created by mykhailo on 4/4/15.
 */
public class MockGoogleCustomSearchApi implements GoogleCustomSearchApi {

    CustomSearchResponse customSearchResponse;

    public MockGoogleCustomSearchApi(Gson gson) {
        try {
            customSearchResponse = gson.fromJson(readCustomSearchResponseFromAssets(), CustomSearchResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException("readCustomSearchResponseFromAssets failed");
        }
    }

    @Override
    public Observable<CustomSearchResponse> search(@Query("key") String apiKey, @Query("cx") String cx, @Query("q") String query, @Query("start") int start) {
        return Observable.just(customSearchResponse);
    }

    private String readCustomSearchResponseFromAssets() throws IOException {
        StringBuilder builder =new StringBuilder();
        InputStream json = App.inst.getAssets().open("customsearch_response1.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String buffer;
        while ((buffer=reader.readLine()) != null) {
            builder.append(buffer);
        }
        reader.close();

        return  builder.toString();
    }
}
