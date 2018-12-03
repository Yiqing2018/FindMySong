package com.findyoursong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TopArtists extends AppCompatActivity {
    List<TextView> tvs=new ArrayList<TextView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_track);
        TextView tv1 = (TextView) findViewById(R.id.text1);
        TextView tv2 = (TextView) findViewById(R.id.text2);
        TextView tv3 = (TextView) findViewById(R.id.text3);
        TextView tv4 = (TextView) findViewById(R.id.text4);
        TextView tv5 = (TextView) findViewById(R.id.text5);
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
        tvs.add(tv4);
        tvs.add(tv5);

        String phpPath = "http://findmysong.web.illinois.edu/top_artist.php";
        setText someTask = new setText(phpPath, tvs, new OnEventListener<List<String>>() {
            public void onSuccess(List<String> result) {
            }
            public void onFailure(Exception e) {
            }
        });
        someTask.execute();

        TextView btn_album_search = (TextView) findViewById(R.id.text1);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = ((TextView) findViewById(R.id.text1)).getText().toString();
                artist_search s = new artist_search(TopArtists.this);
                s.execute(artist_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text2);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = ((TextView) findViewById(R.id.text2)).getText().toString();
                artist_search s = new artist_search(TopArtists.this);
                s.execute(artist_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text3);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = ((TextView) findViewById(R.id.text3)).getText().toString();
                artist_search s = new artist_search(TopArtists.this);
                s.execute(artist_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text4);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = ((TextView) findViewById(R.id.text4)).getText().toString();
                artist_search s = new artist_search(TopArtists.this);
                s.execute(artist_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text5);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String artist_name = ((TextView) findViewById(R.id.text5)).getText().toString();
                artist_search s = new artist_search(TopArtists.this);
                s.execute(artist_name);
            }
        });
    }
}
