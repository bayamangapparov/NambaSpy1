package com.namba.nambaspy1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceActivity extends AppCompatActivity {


    RecyclerView placesPhotoRecyclerView;
    PlacePhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = findViewById(R.id.place_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });




        final RatingBar defaultRatingBar = findViewById(R.id.ratingBar);

        defaultRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                Toast.makeText(PlaceActivity.this, "рейтинг: " + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });

        initPhotoRecyclerView();
        initAndSetPhotos();

    }

    void initPhotoRecyclerView(){
        placesPhotoRecyclerView = findViewById(R.id.places_photo_rv);
        photosAdapter = new PlacePhotosAdapter(this);

        placesPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        placesPhotoRecyclerView.setAdapter(photosAdapter);

    }

    void initAndSetPhotos(){
        List<Integer> photos = new ArrayList<>();

        for (int i = 0; i< 10; i++)
        photos.add(R.drawable.ic_action_del);

        photosAdapter.setPhotos(photos);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
