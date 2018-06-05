/*
package com.namba.nambaspy1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlacePicturesGalleryAdapter extends RecyclerView.Adapter<PlacePicturesGalleryAdapter.MyViewHolder> {

    public interface OnItemClickListener{

        void onItemClick(int position);
        void onItemLongClick(int position);
    }


    private OnItemClickListener itemClickListener;
    private LayoutInflater mInflater;

    List<String> links;

    public PlacePicturesGalleryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setLinks(List<String> links) {
        this.links = links;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.single_image_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get()
                .load(links.get(position))
                .resize(128, 128)
                .centerCrop()
                .placeholder(R.drawable.johny)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return links.size();
    }


    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.mini_image);

            imageView.setOnClickListener(new View.OnClickListener() {
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
*/
