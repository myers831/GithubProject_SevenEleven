package com.example.admin.githubproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    public static final String PROFILE_FRAG_TAG = "profileFragTag";
    public static final String REPO_FRAG_TAG = "repoFragTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile profile = Profile.newInstance("first", "second");
        getSupportFragmentManager().beginTransaction().add(R.id.fl1, profile, PROFILE_FRAG_TAG).commit();

        Repo repo = Repo.newInstance("first", "second");
        getSupportFragmentManager().beginTransaction().add(R.id.fl2, repo, REPO_FRAG_TAG).commit();

    }
}
