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

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>{

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        CircleImageView profileImageView;
        TextView commentTextView;
        TextView commentDateTextView;
        TextView profileNameTextView;

        public CommentViewHolder(View itemView) {
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profileImageViewInComment);
            profileNameTextView = itemView.findViewById(R.id.profile_name);
            commentDateTextView = itemView.findViewById(R.id.comment_date);
            commentTextView = itemView.findViewById(R.id.commentTextView);
        }
    }


    List<Comment> comments;
    LayoutInflater inflater;

    CommentsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.profileImageView.setImageResource(comments.get(position).getUser().getAvatar());
        holder.profileNameTextView.setText(comments.get(position).getUser().getFirstname() +  " " + comments.get(position).getUser().getLastname());
        holder.commentDateTextView.setText(comments.get(position).getDate());
        holder.commentTextView.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }
}
