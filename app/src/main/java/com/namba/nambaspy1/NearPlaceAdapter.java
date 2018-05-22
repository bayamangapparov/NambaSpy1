package com.namba.nambaspy1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class NearPlaceAdapter extends RecyclerView.Adapter<NearPlaceAdapter.NearPlaceViewHolder> {

    private List<NearPlace> nearPlaceList;
    private LayoutInflater mInflater;


    public NearPlaceAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public NearPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_near_item, parent, false);
        NearPlaceViewHolder nearPlaceViewHolder = new NearPlaceViewHolder(itemView);

        return nearPlaceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearPlaceViewHolder holder, int position) {
        NearPlace nearPlace = nearPlaceList.get(position);
        holder.placeImageView.setImageResource(nearPlace.getImage());
        holder.titleView.setText(nearPlace.getTitle());
    }

    public void setNearPlaceList(List<NearPlace> nearPlaceList) {
        this.nearPlaceList = nearPlaceList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return nearPlaceList.size();

    }


    public class NearPlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImageView;
        TextView  titleView;

    public   NearPlaceViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.placeTitleText);
            placeImageView = itemView.findViewById(R.id.placeImage);
        }
    }


}
