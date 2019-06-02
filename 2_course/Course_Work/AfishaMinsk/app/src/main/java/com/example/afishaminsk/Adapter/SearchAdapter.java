package com.example.afishaminsk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afishaminsk.Model.Place;
import com.example.afishaminsk.R;
import java.util.List;
import com.example.afishaminsk.MainActivity;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView name, adress, work, description;

    public SearchViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        adress = (TextView) itemView.findViewById(R.id.adress);
        work = (TextView) itemView.findViewById(R.id.work);
        description = (TextView) itemView.findViewById(R.id.description);
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<Place> place;

    public SearchAdapter(Context context, List<Place> place) {
        this.context = context;
        this.place = place;
    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.name.setText(place.get(position).getName());
        holder.adress.setText(place.get(position).getAdress());
        holder.work.setText(place.get(position).getWork());
        holder.description.setText(place.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return place.size();
    }
}
