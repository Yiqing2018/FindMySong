package com.findyoursong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    EditText album_input;
    ImageView btn_album_search;

    EditText artist_input;
    ImageView btn_artist_search;


    EditText track_input;
    ImageView btn_track_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        album_input = (EditText) findViewById( R.id.input_album_search);
        btn_album_search=(ImageView) findViewById(R.id.btn_album_search);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = album_input.getText().toString();
                album_search s=new album_search(SearchActivity.this);
                s.execute(album_name);
            }
        });


        artist_input = (EditText) findViewById( R.id.input_artist_search);
        btn_artist_search=(ImageView) findViewById(R.id.btn_artist_search);
        btn_artist_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = artist_input.getText().toString();
                artist_search s=new artist_search(SearchActivity.this);
                s.execute(artist_name);
            }
        });

        track_input = (EditText) findViewById( R.id.input_track_search);
        btn_track_search=(ImageView) findViewById(R.id.btn_track_search);
        btn_track_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String track_name = track_input.getText().toString();
                track_search s=new track_search(SearchActivity.this);
                s.execute(track_name);
            }
        });

    }
}
