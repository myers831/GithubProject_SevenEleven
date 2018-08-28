package com.example.admin.githubproject.Commons;

import android.util.Log;

import com.example.admin.githubproject.Models.RepoList;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.QueryMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 10/12/2017.
 */

public class RetrofitListHelper {
    private static final String BASE_URL = "https://api.github.com";

    private static Retrofit create(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static public Observable<RepoList> getCall(Map<String, String> query){
        Retrofit retrofit = create();
        RequestService service = retrofit.create(RequestService.class);
        return service.responseService(query);
    }

    public interface RequestService{

        @GET("/search/repositories")
        Observable<RepoList> responseService(@QueryMap Map<String, String> query);
    }
}