package com.example.admin.githubproject.MainActivity;

import com.example.admin.githubproject.BaseComponents.BasePresenter;
import com.example.admin.githubproject.BaseComponents.BaseRepository;
import com.example.admin.githubproject.BaseComponents.BaseView;
import com.example.admin.githubproject.Models.Item;

import java.util.List;

/**
 * Created by Admin on 8/27/2018.
 */

interface MainActivityContract {
    interface View extends BaseView {
        void setAdapter(List<Item> reposList);
        void updateAdapter();
    }

    interface Presenter extends BasePresenter<View> {
        void setAdapter(List<Item> reposList);
        void updateAdapter();
    }

    interface Repository extends BaseRepository<Presenter> {

    }
}
