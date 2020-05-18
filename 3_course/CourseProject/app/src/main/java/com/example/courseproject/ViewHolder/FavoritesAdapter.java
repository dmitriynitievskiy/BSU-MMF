package com.example.courseproject.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseproject.Interface.ItemClickListener;
import com.example.courseproject.Model.Favorites;
import com.example.courseproject.Model.Place;
import com.example.courseproject.PlaceDetail;
import com.example.courseproject.PlacesList;
import com.example.courseproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private Context context;
    private List<Favorites> favoritesList;

    public FavoritesAdapter(Context context, List<Favorites> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.favorites_item,parent,false);
        return new FavoritesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder viewHolder, int position) {
        viewHolder.place_name.setText(favoritesList.get(position).getPlaceName());
        Picasso.with(context).load(favoritesList.get(position).getPlaceImage())
                .into(viewHolder.place_image);


        final Favorites local = favoritesList.get(position);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent placeDetail = new Intent(context, PlaceDetail.class);
                placeDetail.putExtra("PlaceId",favoritesList.get(position).getPlaceId());
                context.startActivity(placeDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public void removeItem(int position) {
        favoritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Favorites item, int position) {
        favoritesList.add(position,item);
        notifyItemInserted(position);
    }

    public Favorites getItem(int position) {
        return favoritesList.get(position);
    }
}
