package com.example.admin.githubproject;

import com.example.admin.githubproject.models.Repos;
import com.example.admin.githubproject.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Admin on 10/12/2017.
 */

public class RetrofitListHelper {
    public static final String BASE_URL = "https://api.github.com";

    public static Retrofit create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<List<Repos>> getCall(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getResponse();
    }

    public interface RetrofitService{
        @GET("/users/myers831/repos")
        Call<List<Repos>> getResponse();
    }
}