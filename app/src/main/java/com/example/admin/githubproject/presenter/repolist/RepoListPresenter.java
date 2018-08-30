package com.example.admin.githubproject.presenter.repolist;

import android.content.Context;

import com.example.admin.githubproject.model.Item;
import com.example.admin.githubproject.model.RepoItem;
import com.example.admin.githubproject.model.data.repository.repolist.RepoListRepository;
import com.example.admin.githubproject.utils.RepoListContract;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 8/27/2018.
 */

public class RepoListPresenter implements RepoListContract.Presenter {

    private RepoListContract.View view;
    private RepoListRepository repository;

    @Override
    public void addView(RepoListContract.View view) {
        this.view = view;
        instantiateRepository();
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void instantiateRepository() {
        this.repository = RepoListRepository.getInstance();
        repository.addPresenter(this);
    }

    public void repoApiCall(Map<String, String> query){
        repository.repoApiCall(query);
    }

    @Override
    public void setAdapter(List<RepoItem> repoItemList) {
        view.setAdapter(repoItemList);
    }

    @Override
    public void updateAdapter() {
        view.updateAdapter();
    }

    @Override
    public void showError(String errorMsg) {
        view.showError(errorMsg);
    }

    @Override
    public boolean checkNetworkConnectivity() {
        return view.checkNetworkConnectivity();
    }

    @Override
    public Context getViewContext() {
        return view.getViewContext();
    }

}
