package com.example.admin.githubproject.model.data.repository.repolist;

import android.util.Log;

import com.example.admin.githubproject.model.RepoItem;
import com.example.admin.githubproject.model.data.local.LocalDataSource;
import com.example.admin.githubproject.model.data.remote.RemoteDataSource;
import com.example.admin.githubproject.utils.RepoListContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 8/27/2018.
 */

public class RepoListRepository implements RepoListContract.Repository {

    private RepoListContract.Presenter presenter;
    private List<RepoItem> returnRepoItemList = new ArrayList<>();
    private boolean adapterSet = false;
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;


    private RepoListRepository(){}

    public static RepoListRepository getInstance() {
        return new RepoListRepository();
    }

    @Override
    public void addPresenter(RepoListContract.Presenter presenter) {
        this.presenter = presenter;
        localDataSource = new LocalDataSource(presenter.getViewContext());
        remoteDataSource = new RemoteDataSource();
    }

    @Override
    public void removePresenter() {
        this.presenter = null;
    }

//  Handles data that comes from API call and sets it to view
    public void repoApiCall(Map<String, String> query){

        remoteDataSource.getRepoItemList(query, presenter.checkNetworkConnectivity(), localDataSource, new RepoListCallBack() {
            @Override
            public void onRepoItemList(List<RepoItem> repoItemList) {
                returnRepoItemList = repoItemList;
                setAdapter();
            }

            @Override
            public void onRepoItem(RepoItem repoItem) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public void setAdapter(){
        if(!adapterSet){
            presenter.setAdapter(returnRepoItemList);
            adapterSet = true;
        }else{
            presenter.updateAdapter();
        }
    }
}
