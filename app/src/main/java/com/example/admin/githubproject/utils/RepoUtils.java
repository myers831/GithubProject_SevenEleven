package com.example.admin.githubproject.utils;

import com.example.admin.githubproject.model.Item;
import com.example.admin.githubproject.model.RepoItem;

/**
 * Created by Admin on 8/29/2018.
 */

public class RepoUtils {

    public static String getRepoName(RepoItem repoItem){
        return "Repository Name: " + repoItem.getRepoName();
    }

    public static String getRepoDescription(RepoItem repoItem){
        return "Description: " + repoItem.getRepoDescription();
    }

    public static String getRepoStarsCount(RepoItem repoItem){
        return "Stars: " + repoItem.getRepoStarCount();
    }

    public static String getRepoOwner(RepoItem repoItem){
        return "Owner: " + repoItem.getRepoOwner();
    }
}
