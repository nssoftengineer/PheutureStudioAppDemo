package com.pheuture.demo.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
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

public class SlidingAdapter extends PagerAdapter {

    List<User> users;
    private LayoutInflater inflater;
    private Context context;

    public SlidingAdapter(Context context,List<User> users) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.pager_image_item, container, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.imageViewLogo);
        Picasso.get().load(users.get(position).getProfileImg()).into(imageView);
        container.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
