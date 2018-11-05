package com.example.yjh.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Oriange", R.drawable.orange),
            new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear),
            new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple),
            new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Mango", R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        FloatingActionButton fab = findViewById(R.id.fab);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(this);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = findViewById(R.id.swipe_refush);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refushFruits();
            }
        });
    }

    private void refushFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i=0; i<50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                break;
            case R.id.delete:
                break;
            case R.id.settings:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
//                Toast.makeText(MainActivity.this, "fab", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "删除数据", Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "数据恢复",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_call:
                Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_friends:
                Toast.makeText(MainActivity.this, "friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_location:
                Toast.makeText(MainActivity.this, "location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mail:
                Toast.makeText(MainActivity.this, "mail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_task:
                Toast.makeText(MainActivity.this, "task", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }
}
