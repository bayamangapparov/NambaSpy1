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

/**
 * Created by bayaman on 5/21/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private LayoutInflater inflater;
    private List<Category> categoryList;


    public  CategoryAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycle_category_item,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(itemView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.imageView.setImageResource(category.getImage());
        holder.titleTextView.setText(category.getTitle());
    }


    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        public CategoryViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageCategory);
            titleTextView = itemView.findViewById(R.id.titleCategory);


        }
    }
}
