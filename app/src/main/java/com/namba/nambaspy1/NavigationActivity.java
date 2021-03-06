package com.namba.nambaspy1;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

private  final String TAG = NavigationActivity.class.getSimpleName();

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    List<Category>categoryList;

    //Near place RecycleView
    private RecyclerView nearPlaceRecyclerView;
    private PlaceAdapter placeAdapter;
    List<Place> placeList;



    //Counter in drawer menu
    TextView myComment, myPicture;

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

        //Counter in Drawer menu

        myComment=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_comment_marking));

        myPicture = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_images));

        initializeCountDrawer();
    }

    void initCategoryRecycleView(){
        categoryRecyclerView = findViewById(R.id.recycler_horizantal);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        categoryAdapter =  new CategoryAdapter(this);
        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setSubCategoryItemClickListener(new CategoryAdapter.SubCategoryItemClickListener() {
            @Override
            public void subOnItemClick(int position) {

                startActivity(new Intent(getApplicationContext(), SubCategoryActivity.class));
            }

            @Override
            public void subonItemLongClick(int position) {

            }

        });
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
        nearPlaceRecyclerView = findViewById(R.id.near_recycler_view);
        nearPlaceRecyclerView.setNestedScrollingEnabled(false);
        nearPlaceRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        placeAdapter = new PlaceAdapter(this);
        nearPlaceRecyclerView.setAdapter(placeAdapter);


        placeAdapter.setItemClickListener(new PlaceAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                startActivity(new Intent(getApplicationContext(), PlaceActivity.class));
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    void initNearPlaceList(){
        placeList = new ArrayList<>();
        placeList.add(new Place("Фаиза", R.drawable.chay_ic));
        placeList.add(new Place("Таксым", R.drawable.chay_ic));
        placeList.add(new Place("Обед.кг", R.drawable.restaurant));
        placeList.add(new Place("Фаиза", R.drawable.chay_ic));
        placeList.add(new Place("Таксым", R.drawable.chay_ic));
        placeList.add(new Place("Обед.кг", R.drawable.restaurant));


        placeAdapter.setPlaceList(placeList);
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
            startActivity(new Intent(getApplicationContext(), AddPlaceActivity. class));
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

    private void initializeCountDrawer(){
        //Gravity property aligns the text
        myComment.setGravity(Gravity.CENTER_VERTICAL);
        myComment.setTypeface(null, Typeface.BOLD);
        myComment.setTextColor(getResources().getColor(R.color.Black));
        myComment.setText("99+");
        myPicture.setGravity(Gravity.CENTER_VERTICAL);
        myPicture.setTypeface(null,Typeface.BOLD);
        myPicture.setTextColor(getResources().getColor(R.color.Black));
//count is added
        myPicture.setText("7");
    }

}
