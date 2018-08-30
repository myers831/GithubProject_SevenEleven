package com.example.admin.githubproject.model.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.githubproject.model.RepoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/30/2018.
 */

public class LocalDataSource extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RepoItem.db";

    public static final String TABLE_NAME = "Repos";
    public static final String COLUMN_REPO_NAME = "Name";
    public static final String COLUMN_REPO_DESCRIPTION = "Description";
    public static final String COLUMN_REPO_STARS = "Stars";
    public static final String COLUMN_REPO_OWNER = "Owner";
    public static final String ID = "id";


    public LocalDataSource(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + COLUMN_REPO_NAME + " TEXT, " + COLUMN_REPO_DESCRIPTION + " TEXT, " + COLUMN_REPO_STARS + " TEXT, " + COLUMN_REPO_OWNER + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteRepoItemList(){
        this.getWritableDatabase().execSQL("delete from "+ TABLE_NAME);
    }

    public void saveRepoItem(RepoItem repoItem){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_REPO_NAME, repoItem.getRepoName());
        contentValues.put(COLUMN_REPO_DESCRIPTION, repoItem.getRepoDescription());
        contentValues.put(COLUMN_REPO_STARS, repoItem.getRepoStarCount());
        contentValues.put(COLUMN_REPO_OWNER, repoItem.getRepoOwner());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public RepoItem getRepoItem(int id){

        RepoItem repo = null;

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * from " + TABLE_NAME + " WHERE id=" + id;

        Cursor cursor = database.rawQuery(QUERY, null);

        if(cursor.moveToFirst()) {
            do {
                repo = new RepoItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return repo;
    }

    public List<RepoItem> getRepoItemList(){
        List<RepoItem> repoList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * from " + TABLE_NAME;

        Cursor cursor = database.rawQuery(QUERY, null);

        if(cursor.moveToFirst()){
            do{
                RepoItem repo = new RepoItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                repoList.add(repo);
            }while (cursor.moveToNext());
        }

        return repoList;
    }
}
