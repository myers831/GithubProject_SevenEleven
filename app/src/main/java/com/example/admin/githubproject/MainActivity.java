package com.example.admin.githubproject;

import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.githubproject.models.Item;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivityTag";
    MainActivityPresenter presenter;

    RecyclerView rvReposList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RecyeclerViewAdapter recyeclerViewAdapter;
    EndlessRecyclerViewScrollListener scrollListener;

    int pageCall;
    Map<String, String> query = new ArrayMap<>();

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

        rvReposList.addOnScrollListener(scrollListener);

        presenter.repoApiCall(updateQuery(pageCall));
    }

    public Map<String, String> updateQuery(int page){

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String currentDateandTime = sdf.format(currentTime);

        query.put("created", currentDateandTime);
        query.put("sort", "stars");
        query.put("order", "desc");
        query.put("page", String.valueOf(page));

        return query;
    }

    @Override
    public void setAdapter(List<Item> itemList) {
        recyeclerViewAdapter = new RecyeclerViewAdapter(this, itemList);
        rvReposList.setLayoutManager(layoutManager);
        rvReposList.setAdapter(recyeclerViewAdapter);
    }
}
