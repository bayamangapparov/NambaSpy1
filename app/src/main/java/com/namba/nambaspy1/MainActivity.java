package com.namba.nambaspy1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);

    }
    
    public void onClick(View view) {
        startActivity(new Intent(this, PlaceActivity.class));
    }
}
