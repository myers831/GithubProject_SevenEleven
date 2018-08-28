package com.example.admin.githubproject.Commons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.githubproject.R;
import com.example.admin.githubproject.Models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/11/2017.
 */

public class RecyeclerViewAdapter extends RecyclerView.Adapter<RecyeclerViewAdapter.ViewHolder> {

    private List<Item> itemList = new ArrayList<>();
    private final Context context;

    public RecyeclerViewAdapter(Context context, List<Item> reposList) {
        this.itemList = reposList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvRepoName.setText("Repository Nmae: " + item.getFullName());
        holder.tvRepoDescription.setText("Description: " + item.getDescription());
        holder.tvRepoStars.setText("Stars: " + String.valueOf(item.getStargazersCount()));
        holder.tvRepoCreator.setText("Owner: " + item.getOwner().getLogin());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvRepoName;
        final TextView tvRepoDescription;
        final TextView tvRepoStars;
        final TextView tvRepoCreator;


        public ViewHolder(View itemView) {
            super(itemView);

            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoDescription = itemView.findViewById(R.id.tvRepoDescription);
            tvRepoStars = itemView.findViewById(R.id.tvRepoStars);
            tvRepoCreator = itemView.findViewById(R.id.tvRepoCreator);


        }
    }
}
