package com.example.admin.githubproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.githubproject.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/11/2017.
 */

public class RecyeclerViewAdapter extends RecyclerView.Adapter<RecyeclerViewAdapter.ViewHolder> {

    List<Item> itemList = new ArrayList<>();
    Context context;

    public RecyeclerViewAdapter(Context context, List<Item> reposList) {
        this.itemList = reposList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvRepoName.setText(item.getFullName());
        holder.tvRepoDescription.setText(item.getDescription());
        holder.tvRepoStars.setText(String.valueOf(item.getStargazersCount()));
        holder.tvRepoCreator.setText(item.getOwner().getLogin());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRepoName, tvRepoDescription, tvRepoStars, tvRepoCreator;


        public ViewHolder(View itemView) {
            super(itemView);

            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoDescription = itemView.findViewById(R.id.tvRepoDescription);
            tvRepoStars = itemView.findViewById(R.id.tvRepoStars);
            tvRepoName = itemView.findViewById(R.id.tvRepoName);


        }
    }
}
