package com.namba.nambaspy1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    NearPlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);

        //circle image
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.depp);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView) findViewById(R.id.imageAvatar);
        circularImageView.setImageBitmap(circularBitmap);

    }




}
