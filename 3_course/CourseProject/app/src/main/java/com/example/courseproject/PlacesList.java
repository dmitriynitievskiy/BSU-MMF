package com.example.courseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.courseproject.Common.Common;
import com.example.courseproject.Database.Database;
import com.example.courseproject.Interface.ItemClickListener;
import com.example.courseproject.Model.Category;
import com.example.courseproject.Model.Favorites;
import com.example.courseproject.Model.Place;
import com.example.courseproject.ViewHolder.MenuViewHolder;
import com.example.courseproject.ViewHolder.PlaceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlacesList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference placeList;
    String categoryId = "";
    FirebaseRecyclerAdapter<Place, PlaceViewHolder> adapter;
    FirebaseRecyclerAdapter<Place, PlaceViewHolder> searchAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;
    Database localDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        database = FirebaseDatabase.getInstance();
        placeList = database.getReference("Place");

        localDB = new Database(this);

        recyclerView = findViewById(R.id.recycler_place);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null) {
            loadListPlaces(categoryId);
        }

        materialSearchBar = findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter your place");
        loadSuggest();
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<String>();
                for(String search:suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void startSearch(CharSequence text) {
        Query searchByName = placeList.orderByChild("Name").equalTo(text.toString());
        FirebaseRecyclerOptions<Place> options = new FirebaseRecyclerOptions.Builder<Place>()
                .setQuery(searchByName,Place.class)
                .build();
        searchAdapter = new FirebaseRecyclerAdapter<Place, PlaceViewHolder>(options) {

            @NonNull
            @Override
            public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.place_item,parent,false);
                return new PlaceViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i, @NonNull Place place) {
                placeViewHolder.place_name.setText(place.getName());
                Picasso.with(getBaseContext()).load(place.getImage())
                        .into(placeViewHolder.place_image);
                final Place clickItem = place;
                placeViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent placeDetail = new Intent(PlacesList.this,PlaceDetail.class);
                        placeDetail.putExtra("PlaceId", searchAdapter.getRef(position).getKey());
                        startActivity(placeDetail);
                    }
                });

            }
        };
        searchAdapter.startListening();
        recyclerView.setAdapter(searchAdapter);
    }

    private void loadSuggest() {
        placeList.orderByChild("MenuID").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Place item = postSnapshot.getValue(Place.class);
                    suggestList.add(item.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadListPlaces(String categoryId) {
        Query searchByCategory = placeList.orderByChild("MenuID").equalTo(categoryId);
        FirebaseRecyclerOptions<Place> options = new FirebaseRecyclerOptions.Builder<Place>()
                .setQuery(searchByCategory,Place.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Place, PlaceViewHolder>(options) {

            @NonNull
            @Override
            public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.place_item,parent,false);
                return new PlaceViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(@NonNull final PlaceViewHolder placeViewHolder, final int i, @NonNull final Place place) {
                placeViewHolder.place_name.setText(place.getName());
                Picasso.with(getBaseContext()).load(place.getImage())
                        .into(placeViewHolder.place_image);

                if(localDB.isFavorite(adapter.getRef(i).getKey(),Common.currentUser.getPhone()))
                    placeViewHolder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);

                placeViewHolder.fav_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Favorites favorites = new Favorites();
                        favorites.setPlaceId(adapter.getRef(i).getKey());
                        favorites.setPlaceName(place.getName());
                        favorites.setPlaceDescription(place.getDescription());
                        favorites.setPlaceImage(place.getImage());
                        favorites.setPlaceMenuId(place.getMenuID());
                        favorites.setUserPhone(Common.currentUser.getPhone());

                        if(!localDB.isFavorite(adapter.getRef(i).getKey(),Common.currentUser.getPhone())){
                            localDB.addToFavorites(favorites);
                            placeViewHolder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);
                            Toast.makeText(PlacesList.this,""+place.getName()+" was added to Favorites",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            localDB.removeFromFavorites(adapter.getRef(i).getKey(),Common.currentUser.getPhone());
                            placeViewHolder.fav_image.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            Toast.makeText(PlacesList.this,""+place.getName()+" was remove from Favorites",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final Place clickItem = place;
                placeViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent placeDetail = new Intent(PlacesList.this,PlaceDetail.class);
                        placeDetail.putExtra("PlaceId",adapter.getRef(position).getKey());
                        startActivity(placeDetail);
                    }
                });

            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }


}

