package com.namba.nambaspy1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    List<Category>categoryList;

    //Near place RecycleView
    private RecyclerView nearPlaceRecycleView;
    private NearPlaceAdapter nearPlaceAdapter;
    List<NearPlace> nearPlaceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //RecycleView category
        initCategoryRecycleView();
        initCategoryList();


        //RecyclerView nearPlace
        initPlaceRecyclerView();
        initNearPlaceList();



        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        EditText searchEditText = (EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.Black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.Black));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    void initCategoryRecycleView(){
        categoryRecyclerView = findViewById(R.id.recycler_horizantal);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        categoryAdapter =  new CategoryAdapter(this);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    void initCategoryList() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Кафе", R.drawable.cafe_ic));
        categoryList.add(new Category("Бары", R.drawable.chay_ic));
        categoryList.add(new Category("Рестораны", R.drawable.restaurant));
        categoryList.add(new Category("Кофейни", R.drawable.chay_ic));
        categoryList.add(new Category("Фастфуд", R.drawable.fastfood_ic));
        categoryList.add(new Category("Столовые", R.drawable.chay_ic));
        categoryList.add(new Category("Чайхана", R.drawable.chay_ic));
        categoryList.add(new Category("Другие", R.drawable.enather_ic));

        categoryAdapter.setCategoryList(categoryList);
    }

    void initPlaceRecyclerView(){
        nearPlaceRecycleView = findViewById(R.id.near_recycler_view);
        nearPlaceRecycleView.setLayoutManager(new GridLayoutManager(this, 1));
        nearPlaceAdapter = new NearPlaceAdapter(this);
        nearPlaceRecycleView.setAdapter(nearPlaceAdapter);

        nearPlaceAdapter.setItemClickListner(new NearPlaceAdapter.ItemClickListner() {
            @Override
            public void onItemClick(int position) {

                startActivity(new Intent(getApplicationContext(), SubCategoryActivity.class));
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    void initNearPlaceList(){
        nearPlaceList = new ArrayList<>();
        nearPlaceList.add(new NearPlace("Ормон хан", R.drawable.chay_ic));
        nearPlaceList.add(new NearPlace("Гурман", R.drawable.fastfood_ic));
        nearPlaceList.add(new NearPlace("Фаиза", R.drawable.chay_ic));
        nearPlaceList.add(new NearPlace("Таксым", R.drawable.chay_ic));
        nearPlaceList.add(new NearPlace("Обед.кг", R.drawable.restaurant));
        nearPlaceList.add(new NearPlace("Ормон хан", R.drawable.chay_ic));

        nearPlaceAdapter.setNearPlaceList(nearPlaceList);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.write_comment) {
            // Handle the camera action
        } else if (id == R.id.add_place) {

        } else if (id == R.id.lang_interface) {

        } else if (id == R.id.menu_setting) {

        }else if (id == R.id.nav_logout){

        }else if(id == R.id.nav_comment_marking){

        }else if(id == R.id.nav_images){
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
