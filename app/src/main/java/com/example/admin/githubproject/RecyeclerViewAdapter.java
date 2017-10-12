package com.example.admin.githubproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.githubproject.models.Repos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/11/2017.
 */

public class RecyeclerViewAdapter extends RecyclerView.Adapter<RecyeclerViewAdapter.ViewHolder> {

    List<Repos> reposList = new ArrayList<>();
    Context context;

    public RecyeclerViewAdapter(Context context, List<Repos> reposList) {
        this.reposList = reposList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repos repos = reposList.get(position);
        holder.tvRepoName.setText(repos.getName());


    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRepoName;


        public ViewHolder(View itemView) {
            super(itemView);

            tvRepoName = itemView.findViewById(R.id.tvRepoName);


        }
    }
}
