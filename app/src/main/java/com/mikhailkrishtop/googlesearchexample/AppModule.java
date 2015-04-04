package com.mikhailkrishtop.googlesearchexample;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikhailkrishtop.googlesearchexample.data.GoogleSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.customsearch.GoogleCustomSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.internal.StringPreference;
import com.mikhailkrishtop.googlesearchexample.ui.SearchFragment;
import com.mikhailkrishtop.googlesearchexample.ui.SearchOptionsItemSelectedStrategy;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mykhailo on 4/4/15.
 */
@Module(
        injects = {
                SearchFragment.class
        }
)
public final class AppModule {

    public static final String GOOGLE_CUSTOM_SEARCH_ENDPOINT_URL = "https://www.googleapis.com";

    @Provides @Singleton
    GoogleSearchApi provideGoogleSearchApi(
            com.mikhailkrishtop.googlesearchexample.data.customsearch.GoogleSearchApiImpl searchApi) {
        return searchApi;
    }

    @Provides @Singleton
    public GoogleCustomSearchApi provideGoogleCustomSearchApi(
            @Named("customsearch") RestAdapter.Builder restAdapter) {
        return restAdapter.build().create(GoogleCustomSearchApi.class);
    }

    @Provides @Singleton @Named("customsearch")
    public RestAdapter.Builder provideRestAdapterBuilder(
            @Named("customsearch") Endpoint endpoint, @Named("customsearch") Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson));
    }

    @Provides @Singleton @Named("customsearch")
    public Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(GOOGLE_CUSTOM_SEARCH_ENDPOINT_URL);
    }

    @Provides @Singleton @Named("customsearch")
    public Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    SearchOptionsItemSelectedStrategy provideSearchOptionsItemSelectedStrategy() {
        return SearchOptionsItemSelectedStrategy.DEFAULT;
    }

    @Provides @Singleton
    SharedPreferences provideSharedPreferences() {
        return App.inst.getSharedPreferences("appPrefs", MODE_PRIVATE);
    }

    @Provides @Singleton @Named("engineId")
    StringPreference provideEngineId(SharedPreferences prefs) {
        return new StringPreference(prefs, "engineId", "005348304652098354227:msmszw3hbvw");
    }

    @Provides @Singleton @Named("apiKey")
    StringPreference provideApiKey(SharedPreferences prefs) {
        return new StringPreference(prefs, "apiKey", "AIzaSyBFunPHpMTbfvqkVu8zzd_tVxmAtN0e3P8");
    }

}
