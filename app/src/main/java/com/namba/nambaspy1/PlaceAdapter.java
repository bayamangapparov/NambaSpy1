package com.namba.nambaspy1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public interface ItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }


    private ItemClickListener itemClickListener;
    private List<Place> placeList;
    private LayoutInflater mInflater;


    public PlaceAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_near_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeImageView.setImageResource(place.getImage());
        holder.titleView.setText(place.getTitle());
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return placeList.size();

    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImageView;
        TextView titleView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.placeTitleText);
            placeImageView = itemView.findViewById(R.id.placeImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClickListener.onItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }


}
