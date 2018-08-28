package com.example.admin.githubproject.MainActivity;

import android.util.Log;

import com.example.admin.githubproject.Commons.RetrofitListHelper;
import com.example.admin.githubproject.Models.Item;
import com.example.admin.githubproject.Models.RepoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 8/27/2018.
 */

public class MainActivityRepository implements MainActivityContract.Repository {

    private MainActivityContract.Presenter presenter;
    private static final List<Item> itemList = new ArrayList<>();
    private boolean adapterSet = false;

    private MainActivityRepository(){}

    public static MainActivityRepository getInstance() {
        return new MainActivityRepository();
    }

    @Override
    public void addPresenter(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void removePresenter() {
        this.presenter = null;
    }

    public void repoApiCall(Map<String, String> query){

        RetrofitListHelper.getCall(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RepoList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(@NonNull RepoList repoList) {

                        for(Item i: repoList.getItems()){
                            Log.d(TAG, "onNext: RepoName: "+i.getOwner().getLogin());
                            itemList.add(i);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        if(!adapterSet){
                            presenter.setAdapter(itemList);
                            adapterSet = true;
                        }else{
                            presenter.updateAdapter();
                        }
                        Log.d(TAG, "onComplete: ");
                    }
                });

    }
}
