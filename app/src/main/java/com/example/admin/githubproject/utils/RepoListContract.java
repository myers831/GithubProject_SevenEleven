package com.example.admin.githubproject.utils;

import android.content.Context;

import com.example.admin.githubproject.model.RepoItem;
import com.example.admin.githubproject.presenter.base.BasePresenter;
import com.example.admin.githubproject.model.data.repository.base.BaseRepository;
import com.example.admin.githubproject.view.base.BaseView;

import java.util.List;

/**
 * Created by Admin on 8/27/2018.
 */

public interface RepoListContract {
    interface View extends BaseView {
        void setAdapter(List<RepoItem> reposList);
        void updateAdapter();
        void showError(String errorMsg);
        boolean checkNetworkConnectivity();
        Context getViewContext();
    }

    interface Presenter extends BasePresenter<View> {
        void setAdapter(List<RepoItem> reposList);
        void updateAdapter();
        void showError(String errorMsg);
        boolean checkNetworkConnectivity();
        Context getViewContext();
    }

    interface Repository extends BaseRepository<Presenter> {

    }
}
