package com.example.courseproject.ViewHolder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseproject.R;

public class ShowCommentViewHolder extends RecyclerView.ViewHolder {

    public TextView txtUserPhone, txtComment;
    public RatingBar ratingBar;
    public ShowCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        txtComment = itemView.findViewById(R.id.txtComment);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        txtUserPhone = itemView.findViewById(R.id.txtUserPhone);
    }
}
