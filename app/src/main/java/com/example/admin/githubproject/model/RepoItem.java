package com.example.admin.githubproject.model;

/**
 * Created by Admin on 8/30/2018.
 */

public class RepoItem {
    private String RepoName;
    private String RepoDescription;
    private String RepoStarCount;
    private String RepoOwner;

    public RepoItem(String repoName, String repoDescription, String repoStarCount, String repoOwner) {
        RepoName = repoName;
        RepoDescription = repoDescription;
        RepoStarCount = repoStarCount;
        RepoOwner = repoOwner;
    }

    public String getRepoName() {
        return RepoName;
    }

    public void setRepoName(String repoName) {
        RepoName = repoName;
    }

    public String getRepoDescription() {
        return RepoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        RepoDescription = repoDescription;
    }

    public String getRepoStarCount() {
        return RepoStarCount;
    }

    public void setRepoStarCount(String repoStarCount) {
        RepoStarCount = repoStarCount;
    }

    public String getRepoOwner() {
        return RepoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        RepoOwner = repoOwner;
    }
}
