package com.example.admin.githubproject.presenter.base;

import com.example.admin.githubproject.view.base.BaseView;

/**
 * Created by Admin on 8/27/2018.
 */

public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
    void instantiateRepository();
}
