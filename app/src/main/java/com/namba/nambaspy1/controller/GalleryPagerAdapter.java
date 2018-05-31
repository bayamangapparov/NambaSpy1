package com.namba.nambaspy1.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.namba.nambaspy1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryPagerAdapter extends PagerAdapter {

    private List<String> imageList;
    private Context mContext;
    private LayoutInflater inflater;

    public GalleryPagerAdapter(Context context, List<String> imageList) {
        mContext = context;
        this.imageList = imageList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup imageLayout = (ViewGroup) inflater.inflate(R.layout.slider_item, container, false);
        ImageView imageView = imageLayout.findViewById(R.id.slidingImageItem);
        Picasso.get()
                .load(imageList.get(position))
                .placeholder(R.drawable.place_image)
                .into(imageView);

        container.addView(imageLayout, position);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
