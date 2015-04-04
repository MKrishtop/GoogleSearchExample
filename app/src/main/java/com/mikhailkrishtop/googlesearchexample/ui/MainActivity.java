package com.mikhailkrishtop.googlesearchexample.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.mikhailkrishtop.googlesearchexample.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
                String query = getIntent().getStringExtra(SearchManager.QUERY);
                openSearchFragment(query);
            } else {
                openSearchFragment("");
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);

            if (!TextUtils.isEmpty(query)) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_holder);
                if (fragment != null) {
                    if (fragment instanceof SearchFragment) {
                        SearchFragment searchFragment = ((SearchFragment) fragment);
                        searchFragment.requestSearch(query);
                    } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_holder, SearchFragment.newInstance(query))
                                .commit();
                    }
                } else {
                    openSearchFragment(query);
                }
            }
        }

    }

    private void openSearchFragment(String query) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_holder, SearchFragment.newInstance(query))
                .commit();
    }

}
