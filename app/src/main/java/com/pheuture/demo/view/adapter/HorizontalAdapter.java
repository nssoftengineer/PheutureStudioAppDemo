package com.pheuture.demo.view.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pheuture.demo.R;
import com.pheuture.demo.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by neeraj on 9/20/2018.
 */


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private List<User> userList;


    public HorizontalAdapter(List<User> userList) {
        this.userList=userList;
    }

    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horizontal, viewGroup, false);
        return new HorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(userList.get(position).getProfileImg()).into(holder.imageViewLogo);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewLogo;

        ViewHolder(final View itemView) {
            super(itemView);
            this.imageViewLogo = (ImageView) itemView.findViewById(R.id.imageViewLogo);
        }
    }
}