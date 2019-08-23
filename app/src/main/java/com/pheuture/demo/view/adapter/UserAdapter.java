package com.pheuture.demo.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pheuture.demo.R;
import com.pheuture.demo.databinding.UserListRowBinding;
import com.pheuture.demo.model.User;
import com.pheuture.demo.viewmodel.UserViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by neeraj on 9/20/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> users;
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
        this.users = Collections.emptyList();
    }

    //update latest data from observer
    public void setData(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserListRowBinding userListRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_list_row, parent, false);
        return new UserViewHolder(userListRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.onBind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends ViewHolder {
        UserListRowBinding userListRowBinding;

        public UserViewHolder(UserListRowBinding userListRowBinding) {
            super(userListRowBinding.getRoot());
            this.userListRowBinding = userListRowBinding;
        }

        public void onBind(User user) {
            userListRowBinding.setUserViewModel(new UserViewModel(user));
        }
    }
}
