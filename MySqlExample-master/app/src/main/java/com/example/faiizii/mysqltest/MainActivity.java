package com.example.faiizii.mysqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView album_output;
    EditText album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        album = (EditText) findViewById( R.id.album_input);
        album_output = (TextView) findViewById( R.id.album_output);
    }

    public void albumBtn(View view){

        String album_name = album.getText().toString();
        background bg=new background(this);
        bg.execute(album_name);


    }
}
