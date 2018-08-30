package com.example.admin.githubproject.model.data.repository.base;

import com.example.admin.githubproject.presenter.base.BasePresenter;

/**
 * Created by Admin on 8/27/2018.
 */

public interface BaseRepository<P extends BasePresenter> {
    void addPresenter(P presenter);
    void removePresenter();
}
