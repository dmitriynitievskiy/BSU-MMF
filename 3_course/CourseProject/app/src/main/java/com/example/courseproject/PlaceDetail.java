package com.example.courseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseproject.Common.Common;
import com.example.courseproject.Database.Database;
import com.example.courseproject.Model.Place;
import com.example.courseproject.Model.Rating;

import com.example.courseproject.ViewHolder.PlaceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PlaceDetail extends AppCompatActivity implements RatingDialogListener {

    TextView place_name,place_description;
    ImageView place_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnRating;
    String placeId = "";
    FirebaseDatabase database;
    DatabaseReference places;
    DatabaseReference ratingTbl;
    RatingBar ratingBar;
    Button btnShowComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);



        database = FirebaseDatabase.getInstance();
        places = database.getReference("Place");
        ratingTbl = database.getReference("Rating");
        btnRating = findViewById(R.id.btn_rating);
        ratingBar = findViewById(R.id.ratingBar);
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });
        place_name = findViewById(R.id.place_name);
        place_description = findViewById(R.id.place_description);
        place_image = findViewById(R.id.img_place);

        collapsingToolbarLayout = findViewById(R.id.collapsing);

        if(getIntent() != null)
            placeId = getIntent().getStringExtra("PlaceId");
        if(!placeId.isEmpty()) {
            getDetailPlace(placeId);
            getRatingPlace(placeId);
        }

        btnShowComment = findViewById(R.id.btnShowComment);
        btnShowComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetail.this,ShowComment.class);
                intent.putExtra("PlaceId",placeId);
                startActivity(intent);
            }
        });
    }



    private void getRatingPlace(String placeId) {
        Query placeRating = ratingTbl.orderByChild("placeId").equalTo(placeId);
        placeRating.addValueEventListener(new ValueEventListener() {
            int count = 0, sum = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Rating item = postSnapshot.getValue(Rating.class);
                    sum += Integer.parseInt(item.getRateValue());
                    count++;
                }
                if(count != 0) {
                    float average = sum/count;
                    ratingBar.setRating(average);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                .setDefaultRating(1)
                .setTitle("Rate this place")
                .setDescription("Please, select some stars and give your feedback")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please, write your comment here...")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(PlaceDetail.this)
                .show();
    }

    private void getDetailPlace(String placeId) {
        places.child(placeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Place place = dataSnapshot.getValue(Place.class);
                Picasso.with(getBaseContext()).load(place.getImage()).into(place_image);
                collapsingToolbarLayout.setTitle(place.getName());
                place_name.setText(place.getName());
                place_description.setText(place.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int value, @NotNull String comments) {
        final Rating rating = new Rating(Common.currentUser.getPhone(),
                placeId,
                String.valueOf(value),
                comments);
        ratingTbl.push()
                .setValue(rating)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PlaceDetail.this,"Thank you for submit rating !!!",Toast.LENGTH_SHORT).show();
                    }
                });
        /*
        ratingTbl.child(Common.currentUser.getPhone()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(Common.currentUser.getPhone()).exists()){
                    ratingTbl.child(Common.currentUser.getPhone()).removeValue();
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                else{
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                Toast.makeText(PlaceDetail.this, "Thank you for submit rating !!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
