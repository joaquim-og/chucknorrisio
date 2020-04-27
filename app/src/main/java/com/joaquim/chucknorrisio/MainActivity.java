package com.joaquim.chucknorrisio;

import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.AppBarLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.joaquim.chucknorrisio.model.CategoryItem;
import com.xwray.groupie.GroupAdapter;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView rv = findViewById(R.id.rv_main);
        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        populateItems();
    }

    //fake data to check if layout is working property
    private void populateItems() {
        List<CategoryItem> items = new ArrayList<>();

        items.add(new CategoryItem("Cat1", 0xFF00FFFF));
        items.add(new CategoryItem("Cat2", 0x0F00FFFF));
        items.add(new CategoryItem("Cat3", 0xFFF0FFFF));
        items.add(new CategoryItem("Cat4", 0xFF000FFF));
        items.add(new CategoryItem("Cat5", 0xFF00FF0F));
        items.add(new CategoryItem("Cat6", 0xFF00FFF0));

        adapter.addAll(items);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
