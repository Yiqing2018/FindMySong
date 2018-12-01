package com.findyoursong;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    int stopVal=50;
    int speed=60;
    TextView comment;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progressbar);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("score", 0);
        String strValue=mIntent.getStringExtra("user");
        stopVal=intValue;
        TextView title=(TextView) findViewById(R.id.withWho) ;
        title.setText("With "+ strValue);
        comment=(TextView) findViewById(R.id.comment) ;
        if(intValue<30){
            str="OMG You guys can't be friends!";
        }else if(intValue<40){
            str="Really Friends?";
        }else if(intValue<50){
            str="Hang Out More!";
        }else if(intValue<60){
            str="Not Bad";
        }else if(intValue<70){
            str="Nice Score";
        }else if(intValue<80){
            str="Above Average";
        }else if(intValue<90){
            str="Real Friends";
        }else{
            str="Go get married!";
        }


        txtProgress = (TextView) findViewById(R.id.percentage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= stopVal) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
                comment.setText(str);

            }
        });
        th.start();




    }
}



