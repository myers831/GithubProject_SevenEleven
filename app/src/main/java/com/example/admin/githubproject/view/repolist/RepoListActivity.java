package com.example.admin.githubproject.view.repolist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.githubproject.model.RepoItem;
import com.example.admin.githubproject.presenter.repolist.RepoListPresenter;
import com.example.admin.githubproject.utils.RepoListContract;
import com.example.admin.githubproject.view.base.BaseActivity;
import com.example.admin.githubproject.utils.EndlessRecyclerViewScrollListener;
import com.example.admin.githubproject.utils.RecyeclerViewAdapter;
import com.example.admin.githubproject.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
    RepoListActivity is the first screen user will interact with
 */

public class RepoListActivity extends BaseActivity implements RepoListContract.View {

    private RepoListPresenter presenter;

    private RecyclerView rvReposList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyeclerViewAdapter recyeclerViewAdapter;
    private EndlessRecyclerViewScrollListener repoListScrollListener;

//  pageCall is used to index what page needs to be loaded as user scrolls
    private int pageCall;
//  scrollListenerSet checks to see if scroll listener is set already
    private boolean scrollListenerSet = false;
//  query is used to specify what data is to be asked of the API
    private final Map<String, String> query = new ArrayMap<>();

    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        presenter = new RepoListPresenter();
        presenter.addView(this);

        rvReposList = findViewById(R.id.rvReposList);
        layoutManager = new LinearLayoutManager(this);

        pageCall = 1;

        presenter.repoApiCall(updateQuery(pageCall));

        repoListScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);
                pageCall += 1;
                presenter.repoApiCall(updateQuery(pageCall));
            }
        };

        setRepoListScrollListener(repoListScrollListener);
    }

//  Adds repoListScrollListener to RecyclerView rvReposList
    private void setRepoListScrollListener(EndlessRecyclerViewScrollListener repoListScrollListener){
        if(!scrollListenerSet){
            rvReposList.addOnScrollListener(repoListScrollListener);
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
    public void setAdapter(List<RepoItem> repoItemList) {
        recyeclerViewAdapter = new RecyeclerViewAdapter(repoItemList);
        rvReposList.setLayoutManager(layoutManager);
        rvReposList.setAdapter(recyeclerViewAdapter);
    }

    @Override
    public void updateAdapter() {
        recyeclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean checkNetworkConnectivity() {
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        return false;
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
