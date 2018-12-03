package com.findyoursong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TopAlbum extends AppCompatActivity {
    List<TextView> tvs=new ArrayList<TextView>();
    List<ImageView> mvs=new ArrayList<ImageView>();
    TextView test;

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

        ImageView mv1 = (ImageView) findViewById(R.id.image1);
        ImageView mv2 = (ImageView) findViewById(R.id.image2);
        ImageView mv3 = (ImageView) findViewById(R.id.image3);
        ImageView mv4 = (ImageView) findViewById(R.id.image4);
        ImageView mv5 = (ImageView) findViewById(R.id.image5);
        mvs.add(mv1);
        mvs.add(mv2);
        mvs.add(mv3);
        mvs.add(mv4);
        mvs.add(mv5);
        String phpPath = "http://findmysong.web.illinois.edu/top_album.php";
        setText someTask = new setText(phpPath, tvs, new OnEventListener<List<String>>() {
            public void onSuccess(List<String> result) {
                setImage image = new setImage(tvs, mvs, result);
                image.execute();
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
        someTask.execute();

        TextView btn_album_search = (TextView) findViewById(R.id.text1);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = ((TextView) findViewById(R.id.text1)).getText().toString();
                album_search s = new album_search(TopAlbum.this);
                s.execute(album_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text2);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = ((TextView) findViewById(R.id.text2)).getText().toString();
                album_search s = new album_search(TopAlbum.this);
                s.execute(album_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text3);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = ((TextView) findViewById(R.id.text3)).getText().toString();
                album_search s = new album_search(TopAlbum.this);
                s.execute(album_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text4);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = ((TextView) findViewById(R.id.text4)).getText().toString();
                album_search s = new album_search(TopAlbum.this);
                s.execute(album_name);
            }
        });
        btn_album_search = (TextView) findViewById(R.id.text5);
        btn_album_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String album_name = ((TextView) findViewById(R.id.text5)).getText().toString();
                album_search s = new album_search(TopAlbum.this);
                s.execute(album_name);
            }
        });
    }
}
