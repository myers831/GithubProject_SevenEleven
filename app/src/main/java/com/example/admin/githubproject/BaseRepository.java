package com.example.admin.githubproject;

/**
 * Created by Admin on 8/27/2018.
 */

public interface BaseRepository<P extends BasePresenter> {
    void addPresenter(P presenter);
    void removePresenter();
}
