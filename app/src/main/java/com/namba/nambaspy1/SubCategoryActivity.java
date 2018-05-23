package com.namba.nambaspy1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    private RecyclerView subCategoryRecyclerView;
    private PlaceAdapter subCategoryAdapter;
    List<Place> placeList;

    private final String TAG = SubCategoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        //Calling methods
        initSubCategoryRecyclerView();
        initSubCategoryList();


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

    void initSubCategoryRecyclerView(){
        subCategoryRecyclerView = findViewById(R.id.subCategory_recycler);
        subCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        subCategoryAdapter = new PlaceAdapter(this);
        subCategoryRecyclerView.setAdapter(subCategoryAdapter);

        subCategoryAdapter.setItemClickListener(new PlaceAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getApplicationContext(), PlaceActivity.class));
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    void initSubCategoryList(){

        placeList = new ArrayList<>();
        placeList.add(new Place("Ормон хан", R.drawable.chay_ic));
        placeList.add(new Place("Гурман", R.drawable.fastfood_ic));
        placeList.add(new Place("Фаиза", R.drawable.chay_ic));
        placeList.add(new Place("Таксым", R.drawable.chay_ic));
        placeList.add(new Place("Обед.кг", R.drawable.restaurant));
        placeList.add(new Place("Ормон хан", R.drawable.chay_ic));
        placeList.add(new Place("Фаиза", R.drawable.chay_ic));
        placeList.add(new Place("Таксым", R.drawable.chay_ic));
        placeList.add(new Place("Обед.кг", R.drawable.restaurant));
        placeList.add(new Place("Фаиза", R.drawable.chay_ic));
        placeList.add(new Place("Таксым", R.drawable.chay_ic));
        placeList.add(new Place("Обед.кг", R.drawable.restaurant));


        subCategoryAdapter.setPlaceList(placeList);

    }

}
