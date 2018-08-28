package com.example.admin.githubproject.MainActivity;

import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

/*
    MainActivity is the first screen user will interact with
 */

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private MainActivityPresenter presenter;

    private RecyclerView rvReposList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyeclerViewAdapter recyeclerViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;

//  pageCall is used to index what page needs to be loaded as user scrolls
    private int pageCall;
//  scrollListenerSet checks to see if scroll listener is set already
    private boolean scrollListenerSet = false;
//  query is used to specify what data is to be asked of the API
    private final Map<String, String> query = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
        presenter.addView(this);

        rvReposList = findViewById(R.id.rvReposList);
        layoutManager = new LinearLayoutManager(this);

        pageCall = 1;

        presenter.repoApiCall(updateQuery(pageCall));

        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);
                pageCall += 1;
                presenter.repoApiCall(updateQuery(pageCall));
            }
        };

        setScrollListener(scrollListener);
    }

//  Adds scrollListener to RecyclerView rvReposList
    private void setScrollListener(EndlessRecyclerViewScrollListener scrollListener){
        if(!scrollListenerSet){
            rvReposList.addOnScrollListener(scrollListener);
            scrollListenerSet = true;
        }
    }

//  Called whenever page on query needs to be updated
    private Map<String, String> updateQuery(int page){

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(currentTime);

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
