package com.namba.nambaspy1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

    RecyclerView commentRecyclerView;
    CommentsAdapter commentsAdapter;
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

        initCommentRecyclerView();
        initComments();

    }

    void initCommentRecyclerView() {
        commentRecyclerView = findViewById(R.id.comment_rv);
        commentRecyclerView.setNestedScrollingEnabled(false);
        commentsAdapter = new CommentsAdapter(this);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentsAdapter);

    }

    void initComments() {

        User user = new User("Джонни", "Депп", R.drawable.johny);

        Comment comment = new Comment("Начиная с Android 3.0, в системе появилась возможность создавать всплывающее меню, привязанное к элементу View. Меню реализовано в виде модального окна, которое отображается снизу о", "13.12.2017", 4, user);

        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            comments.add(comment);
        }

        commentsAdapter.setComments(comments);
    }

    void initPhotoRecyclerView(){
        placesPhotoRecyclerView = findViewById(R.id.places_photo_rv);
        photosAdapter = new PlacePhotosAdapter(this);

        placesPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        placesPhotoRecyclerView.setAdapter(photosAdapter);

    }

    void initAndSetPhotos(){
        List<Integer> photos = new ArrayList<>();

        for (int i = 0; i< 4; i++) {
            photos.add(R.drawable.image1);
            photos.add(R.drawable.image2);
            photos.add(R.drawable.image3);
        }

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


    void onClickAddReviewButton(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ReviewFullscreenDialogFragment newFragment = new ReviewFullscreenDialogFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }
}
