package com.example.admin.githubproject.MainActivity;

import com.example.admin.githubproject.Models.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 8/27/2018.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private MainActivityRepository repository;

    @Override
    public void addView(MainActivityContract.View view) {
        this.view = view;
        instantiateRepository();
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void instantiateRepository() {
        this.repository = MainActivityRepository.getInstance();
        repository.addPresenter(this);
    }

    public void repoApiCall(Map<String, String> query){
        repository.repoApiCall(query);
    }

    @Override
    public void setAdapter(List<Item> itemList) {
        view.setAdapter(itemList);
    }

    @Override
    public void updateAdapter() {
        view.updateAdapter();
    }
}
