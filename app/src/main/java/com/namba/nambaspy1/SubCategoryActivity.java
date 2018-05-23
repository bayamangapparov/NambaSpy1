package com.namba.nambaspy1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    private RecyclerView subCategoryRecyclerView;
    private CategoryAdapter subCategoryAdapter;
    List<Category> subCategoryList;

    private final String TAG = SubCategoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        Toolbar toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
       });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
