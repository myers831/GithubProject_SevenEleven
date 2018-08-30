package com.example.admin.githubproject.model.data.repository.repolist;

import com.example.admin.githubproject.model.RepoItem;

import java.util.List;

/**
 * Created by Admin on 8/30/2018.
 */

public interface RepoListCallBack {
    public void onRepoItemList(List<RepoItem> repoItemList);

    public void onRepoItem(RepoItem repoItem);

    public void onFailure(String error);
}
