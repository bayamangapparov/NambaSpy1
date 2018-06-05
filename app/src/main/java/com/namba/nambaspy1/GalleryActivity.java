package com.namba.nambaspy1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.namba.nambaspy1.controller.GalleryPagerAdapter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    private Animator mCurrentAnimator;

    private int mShortAnimationDuration;
    LinearLayout linearLayout;
    Rect startBounds;
    float startScaleFinal;
    View currentView;

    ViewPager viewPager;
    GalleryPagerAdapter pagerAdapter;
    List<String> imagesList;

    RecyclerView recyclerView;
    PlacePicturesGalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);




        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);


        initImageRecyclerView();
        initImages();


        linearLayout = findViewById(R.id.layou);

        viewPager = findViewById(R.id.imageViewPager);
        pagerAdapter = new GalleryPagerAdapter(this, imagesList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(20);

    }

    void initImageRecyclerView() {
        recyclerView = findViewById(R.id.place_image_gallery_rv);
        adapter = new PlacePicturesGalleryAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

    }

    void initImages() {
        List<String> images = new ArrayList<>();
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjiJTCa8t9vt147l2uIt5ch7QU9qpalIybqRuzTmN-7B9uC9Bi");
        images.add("http://cdn.trinixy.ru/pics3/20080620/homyak_45.jpg");
        images.add("https://u.tfstatic.com/restaurant_photos/542/34542/169/612/cafe-de-nice-restaurant-ad8b3.jpg");
        images.add("https://media-cdn.tripadvisor.com/media/photo-s/04/8e/45/77/ametist-cafe-restaurant.jpg");

        adapter.setLinks(images);
        imagesList = images;
    }

    private void zoomImageFromThumb(final View thumbView) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        linearLayout.setVisibility(View.VISIBLE);
        startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);

        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }




        viewPager.setPivotX(0f);
        viewPager.setPivotY(0f);


        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(viewPager, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(viewPager, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(viewPager, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(viewPager,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;


        startScaleFinal = startScale;

    }


    void hideExtendedImageWithAnimation() {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        currentView = recyclerView.getChildAt(viewPager.getCurrentItem());

        currentView.getGlobalVisibleRect(startBounds);

        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }


        currentView.setAlpha(0f);


        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator
                .ofFloat(viewPager, View.X, startBounds.left))
                .with(ObjectAnimator
                        .ofFloat(viewPager,
                                View.Y, startBounds.top))
                .with(ObjectAnimator
                        .ofFloat(viewPager,
                                View.SCALE_X, startScaleFinal))
                .with(ObjectAnimator
                        .ofFloat(viewPager,
                                View.SCALE_Y, startScaleFinal));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                currentView.setAlpha(1f);
                linearLayout.setVisibility(View.GONE);
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentView.setAlpha(1f);
                linearLayout.setVisibility(View.GONE);
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;
    }
    @Override
    public void onBackPressed() {

/*        if (expandedImageView.getVisibility() == View.VISIBLE) {
            hideExtendedImageWithAnimation();

        } else {
            super.onBackPressed();
        }*/

        if (linearLayout.getVisibility() == View.VISIBLE) {
            hideExtendedImageWithAnimation();
        } else {
            super.onBackPressed();
        }
    }

    public class PlacePicturesGalleryAdapter extends RecyclerView.Adapter<PlacePicturesGalleryAdapter.MyViewHolder> {



        private LayoutInflater mInflater;

        List<String> links;

        public PlacePicturesGalleryAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public void setLinks(List<String> links) {
            this.links = links;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PlacePicturesGalleryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.single_image_item, parent, false);
            return new PlacePicturesGalleryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull PlacePicturesGalleryAdapter.MyViewHolder holder, int position) {

            Picasso.get()
                    .load(links.get(position))
                    .resize(128, 128)
                    .centerCrop()
                    .placeholder(R.drawable.johny)
                    .into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return links.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.mini_image);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(getAdapterPosition(), false);
                        zoomImageFromThumb(v);
                        currentView = v;
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }
                });
            }
        }
    }



}
