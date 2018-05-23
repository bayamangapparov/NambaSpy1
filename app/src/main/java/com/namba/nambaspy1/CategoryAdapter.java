package com.namba.nambaspy1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public interface SubCategoryItemClickListener{
        void subOnItemClick(int position);
        void subonItemLongClick(int position);
    }

    private SubCategoryItemClickListener subCategoryItemClickListener;
    private LayoutInflater inflater;
    private List<Category> categoryList;


    public  CategoryAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycle_category_item,parent,false);
        return new CategoryViewHolder(itemView);
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

    public void setSubCategoryItemClickListener(SubCategoryItemClickListener subCategoryItemClickListener) {
        this.subCategoryItemClickListener = subCategoryItemClickListener;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        public CategoryViewHolder(final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageCategory);
            titleTextView = itemView.findViewById(R.id.titleCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subCategoryItemClickListener.subOnItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    subCategoryItemClickListener.subonItemLongClick(getAdapterPosition());
                    return false;
                }
            });

        }
    }
}
