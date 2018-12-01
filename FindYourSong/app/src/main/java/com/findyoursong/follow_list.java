package com.findyoursong;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class follow_list extends AppCompatActivity {

    LinearLayout Mylayout;
    final Context activity=this;
    String username;
    String anotheruser;
    Map<View,Integer> map=new HashMap<View,Integer>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_list);
        Mylayout = (LinearLayout) findViewById(R.id.viewObj);



        getFollowInfo someTask = new getFollowInfo(username,new OnEventListener<List<String>>(){
            public void onSuccess(List<String> following) {
                int len=following.size()/2;
                for (int i=0;i<len;i++){
                    anotheruser=following.get(i);
                    TextView tv = new TextView(activity);
                    tv.setText(anotheruser);
                    tv.setTextSize(30);
                    Mylayout.addView(tv);
                    int score=Integer.valueOf(following.get(len+i));
                    map.put(tv,score);

                    tv.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            String[] val=((TextView) view).getText().toString().split(" ");
                            String name=val[0];
                            Intent intent = new Intent(follow_list.this,ScoreActivity.class);
                            intent.putExtra("user", name);
                            intent.putExtra("score", map.get(view));
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Exception e) {
            }
        });
        someTask.execute();

    }

}
