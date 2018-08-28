package com.example.admin.githubproject.MainActivity;

import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.githubproject.BaseComponents.BaseActivity;
import com.example.admin.githubproject.Commons.EndlessRecyclerViewScrollListener;
import com.example.admin.githubproject.Commons.RecyeclerViewAdapter;
import com.example.admin.githubproject.R;
import com.example.admin.githubproject.Models.Item;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivityTag";
    private MainActivityPresenter presenter;

    private RecyclerView rvReposList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyeclerViewAdapter recyeclerViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;

    private int pageCall;
    private boolean scrollListenerSet = false;
    private final Map<String, String> query = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
        presenter.addView(this);

        rvReposList = findViewById(R.id.rvReposList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();

        pageCall = 1;

        presenter.repoApiCall(updateQuery(pageCall));

        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);
                pageCall += 1;
                Log.d(TAG, "onLoadMore: check pageCall = " + String.valueOf(pageCall));

                presenter.repoApiCall(updateQuery(pageCall));
            }
        };

        setScrollListener(scrollListener);
    }

    private void setScrollListener(EndlessRecyclerViewScrollListener scrollListener){
        if(!scrollListenerSet){
            rvReposList.addOnScrollListener(scrollListener);
            scrollListenerSet = true;
        }
    }

    private Map<String, String> updateQuery(int page){

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(currentTime);

        Log.d(TAG, "updateQuery: time: " + currentDateandTime);
        Log.d(TAG, "updateQuery: page: " + String.valueOf(page));

        query.put("page", String.valueOf(page));
        query.put("order", "desc");
        query.put("sort", "stars");
        query.put("q","created:" +currentDateandTime);

        return query;
    }

    @Override
    public void setAdapter(List<Item> itemList) {
        recyeclerViewAdapter = new RecyeclerViewAdapter(this, itemList);
        rvReposList.setLayoutManager(layoutManager);
        rvReposList.setAdapter(recyeclerViewAdapter);
    }

    @Override
    public void updateAdapter() {
        recyeclerViewAdapter.notifyDataSetChanged();
    }
}
