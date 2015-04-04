package com.mikhailkrishtop.googlesearchexample.ui;

import android.app.Activity;

/**
 * Created by mykhailo on 4/4/15.
 */
public interface SearchOptionsItemSelectedStrategy {

    boolean onItemClick(int id, Activity activity);

    SearchOptionsItemSelectedStrategy DEFAULT = new SearchOptionsItemSelectedStrategy() {
        @Override
        public boolean onItemClick(int id, Activity activity) {
            return false;
        }
    };
}
