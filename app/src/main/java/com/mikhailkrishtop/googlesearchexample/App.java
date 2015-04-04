package com.mikhailkrishtop.googlesearchexample;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by mykhailo on 4/4/15.
 */
public class App extends Application {

    ObjectGraph objectGraph;

    public static App inst;

    public App() {
        inst = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildObjectGraph();
    }

    public void inject(Object obj) {
        objectGraph.inject(obj);
    }

    public void buildObjectGraph() {
        objectGraph = ObjectGraph.create(Modules.list());
    }

}
