package com.example.courseproject.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseproject.Interface.ItemClickListener;
import com.example.courseproject.R;

public class FavoritesViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView place_name;
    public ImageView place_image,fav_image;
    private ItemClickListener itemClickListener;
    public RelativeLayout view_background,view_foreground;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FavoritesViewHolder(@NonNull View itemView) {
        super(itemView);
        place_name = itemView.findViewById(R.id.place_name);
        place_image = itemView.findViewById(R.id.place_image);
        fav_image = itemView.findViewById(R.id.fav);
        itemView.setOnClickListener(this);

        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

