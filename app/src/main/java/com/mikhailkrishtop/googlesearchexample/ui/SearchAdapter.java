package com.mikhailkrishtop.googlesearchexample.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhailkrishtop.googlesearchexample.App;
import com.mikhailkrishtop.googlesearchexample.R;
import com.mikhailkrishtop.googlesearchexample.data.model.SearchResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mikhail on 7/7/14.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<SearchResponse> searchResponses;

    public SearchAdapter(List<SearchResponse> searchResponses) {
        this.searchResponses = searchResponses;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResponse searchResponse = searchResponses.get(position);

        holder.titleTv.setText(searchResponse.title);
        holder.urlTv.setText(searchResponse.url);
    }

    @Override
    public int getItemCount() {
        return searchResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title_tv) TextView titleTv;
        @InjectView(R.id.url_tv) TextView urlTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @OnClick(R.id.root_block)
        public void onItemClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(searchResponses.get(getPosition()).url));
            App.inst.startActivity(intent);
        }
    }

    public List<SearchResponse> getSearchResponses() {
        return searchResponses;
    }

    public void setSearchResponses(List<SearchResponse> searchResponses) {
        this.searchResponses = searchResponses;
    }

}
