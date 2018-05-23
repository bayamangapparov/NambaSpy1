package com.namba.nambaspy1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddPlaceActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        toolbar = findViewById(R.id.add_place_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Добавить заведение");
    }

}
