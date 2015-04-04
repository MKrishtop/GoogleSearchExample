package com.mikhailkrishtop.googlesearchexample;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mikhailkrishtop.googlesearchexample.data.customsearch.GoogleCustomSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.internal.StringPreference;
import com.mikhailkrishtop.googlesearchexample.ui.SearchOptionsItemSelectedStrategy;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

/**
 * Created by mykhailo on 4/4/15.
 */
@Module(
        addsTo = AppModule.class,
        injects = {
                DebugSearchOptionsItemSelectedStrategy.class
        },
        overrides = true
)
public class DebugAppModule {

    @Provides @Singleton @Named("dataSource")
    StringPreference provideDataSource(SharedPreferences prefs) {
        return new StringPreference(prefs, "dataSource", "live");
    }

    @Provides @Singleton
    public GoogleCustomSearchApi provideGoogleCustomSearchApi(
            @Named("customsearch") RestAdapter.Builder restAdapter,
            @Named("dataSource") StringPreference dataSource,
            @Named("customsearch") Gson gson) {

        if (dataSource.isSet() && dataSource.get().equals("mock")) {
            return MockRestAdapter.from(restAdapter.build()).create(GoogleCustomSearchApi.class,
                    new MockGoogleCustomSearchApi(gson));
        } else {
            return restAdapter.build().create(GoogleCustomSearchApi.class);
        }
    }

    @Provides
    SearchOptionsItemSelectedStrategy provideSearchOptionsItemSelectedStrategy(
            DebugSearchOptionsItemSelectedStrategy itemSelectedStrategy) {
        return itemSelectedStrategy;
    }

}
