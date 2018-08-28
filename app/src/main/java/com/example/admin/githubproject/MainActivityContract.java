package com.example.admin.githubproject;

import com.example.admin.githubproject.models.Item;

import java.util.List;

/**
 * Created by Admin on 8/27/2018.
 */

public interface MainActivityContract {
    interface View extends BaseView{
        void setAdapter(List<Item> reposList);
    }

    interface Presenter extends BasePresenter<View>{
        void setAdapter(List<Item> reposList);
    }

    interface Repository extends BaseRepository<Presenter>{

    }
}
