package com.findyoursong;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class only_follow_list extends AppCompatActivity {

        LinearLayout Mylayout;
        final Context activity=this;
        String username;
        String anotheruser;
        EditText who_to_remove;
        EditText who_to_add;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_only_follow_list);
            Mylayout = (LinearLayout) findViewById(R.id.viewObj);
            Intent intent = getIntent();
            username = intent.getStringExtra("id");

            getFollowInfo someTask = new getFollowInfo(username,new OnEventListener<List<String>>(){
                public void onSuccess(List<String> following) {
                    int len=following.size()/2;
                    for (int i=0;i<len;i++){
                        anotheruser=following.get(i);
                        TextView tv = new TextView(activity);
                        tv.setText(anotheruser);
                        tv.setTextSize(30);
                        Mylayout.addView(tv);
                    }
                }
                @Override
                public void onFailure(Exception e) {
                }
            });
            someTask.execute();

            ImageView add=(ImageView) findViewById(R.id.add);
            ImageView remove=(ImageView) findViewById(R.id.remove);
            ImageView refresh=(ImageView) findViewById(R.id.refresh);

            //onClick event listener on add image
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog dialog=  new AlertDialog.Builder(only_follow_list.this).create();
                dialog.setTitle("add new friend!");

                who_to_add=new EditText(dialog.getContext());
                who_to_add.setTextSize(25);

                dialog.setView(who_to_add);
                Button add_button = new Button(dialog.getContext());


                dialog.setButton(Dialog.BUTTON_POSITIVE,"add", new  DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String addUser=who_to_add.getText().toString();

                        follow like=new follow(username,addUser);
                        like.execute();
                        dialog.cancel();
                    }
                });


                dialog.show();
            }
        });

            remove.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlertDialog dialog=  new AlertDialog.Builder(only_follow_list.this).create();
                    dialog.setTitle("remove from following list");

                    who_to_remove=new EditText(dialog.getContext());
                    who_to_remove.setTextSize(25);

                    dialog.setView(who_to_remove);
                    Button add_button = new Button(dialog.getContext());


                    dialog.setButton(Dialog.BUTTON_POSITIVE,"remove", new  DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String removeUser=who_to_remove.getText().toString();

                            unfollow unlike=new unfollow(username,removeUser);
                            unlike.execute();
                            dialog.cancel();
                        }
                    });


                    dialog.show();
                }
            });

        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(only_follow_list.this,only_follow_list.class);
                intent.putExtra("id", username);
                startActivity(intent);
            }
        });



        }



}
