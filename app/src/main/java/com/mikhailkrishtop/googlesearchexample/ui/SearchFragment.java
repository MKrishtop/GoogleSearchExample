package com.mikhailkrishtop.googlesearchexample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mikhailkrishtop.googlesearchexample.App;
import com.mikhailkrishtop.googlesearchexample.R;
import com.mikhailkrishtop.googlesearchexample.data.GoogleSearchApi;
import com.mikhailkrishtop.googlesearchexample.data.internal.ObservableHelper;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;


public class SearchFragment extends Fragment {

    public static final String SEARCH_QUERY = "SEARCH_QUERY";

    @InjectView(R.id.animator) BetterViewAnimator animator;
    @InjectView(R.id.search_rv) RecyclerView searchRv;

    SearchView searchView;

    @Inject SearchOptionsItemSelectedStrategy itemSelectedStrategy;
    @Inject GoogleSearchApi googleSearchApi;

    SearchAdapter adapter;
    Subscription request;

    String initQuery;

    public static SearchFragment newInstance(String query) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        adapter = new SearchAdapter(new ArrayList<SearchResponse>());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        App.inst.inject(this);

        initQuery = getArguments().getString(SEARCH_QUERY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.inject(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        searchRv.setAdapter(adapter);
        searchRv.setItemAnimator(new DefaultItemAnimator());
        searchRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItemCompat.expandActionView(searchItem);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.hint_search));
        searchView.setOnQueryTextListener(queryListener);
        searchView.setIconified(false);

        if (!TextUtils.isEmpty(initQuery)) {
            searchView.setQuery(initQuery, true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        } else if (itemSelectedStrategy.onItemClick(id, getActivity())) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            requestSearch(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    @OnClick(R.id.try_again_b)
    public void onTryAgainClick() {
        requestSearch(searchView.getQuery().toString());
    }

    public void requestSearch(String query) {
        animator.setDisplayedChildId(R.id.progress);
        request = ObservableHelper.ui(googleSearchApi.search(query, 1))
                .subscribe(new Observer<List<SearchResponse>>() {
                    @Override
                    public void onCompleted() {
                        animator.setDisplayedChildId(R.id.content);
                    }

                    @Override
                    public void onError(Throwable e) {
                        animator.setDisplayedChildId(R.id.error);
                    }

                    @Override
                    public void onNext(List<SearchResponse> searchResponses) {
                        adapter.setSearchResponses(searchResponses);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDetach() {
        if (request != null && !request.isUnsubscribed()) {
            request.unsubscribe();
        }
        super.onDetach();
    }

}
