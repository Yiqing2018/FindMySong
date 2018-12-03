package com.findyoursong;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    String username;


    TextView topTrack;
    TextView topAlbum;
    TextView topArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("id");


        topTrack= (TextView) findViewById(R.id.top_track);
        topTrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TopTrack.class);
                startActivity(intent);
            }
        });

        topAlbum= (TextView) findViewById(R.id.top_album);
        topAlbum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TopAlbum.class);
                startActivity(intent);
            }
        });

        topArtist= (TextView) findViewById(R.id.top_artists);
        topArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TopArtists.class);
                startActivity(intent);
            }
        });

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


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.search) {
            Intent intent = new Intent(HomePage.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.score) {



            Intent intent = new Intent(HomePage.this,follow_list.class);
            intent.putExtra("id", username);
            startActivity(intent);


        } else if (id == R.id.follow) {

            Intent intent = new Intent(HomePage.this,only_follow_list.class);
            intent.putExtra("id", username);
            startActivity(intent);


        } else if (id == R.id.rec) {

            Intent intent = new Intent(HomePage.this,FindYourSong.class);
            intent.putExtra("id", username);
            startActivity(intent);

        } else if (id == R.id.send) {
            Intent intent = new Intent(HomePage.this,SendActivity.class);            intent.putExtra("id", username);
            intent.putExtra("id", username);
            startActivity(intent);

        } else if (id == R.id.contact) {
            Intent intent = new Intent(HomePage.this, ContactActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
