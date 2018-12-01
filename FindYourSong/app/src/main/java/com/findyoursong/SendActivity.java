package com.findyoursong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {
    Button btn;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        btn=(Button) findViewById(R.id.send_button);
        Intent intent = getIntent();
        username = intent.getStringExtra("id");

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText tv=((EditText) findViewById(R.id.send_text));
                String txt=tv.getText().toString();
                String email=((TextView) findViewById(R.id.send_email)).getText().toString();
//                tv.setText(username);


                if(username==null || username.length()==0){
                    tv.setText("invalid user information");
                }else if(txt==null||txt.length()==0){
                    tv.setText("Please leave your message!");

                }else if(!validEmail(email)){

                    tv.setText("invalid email");

                }else{
                    Send send=new Send(username,txt,email,tv);
                    send.execute();
                    tv.setText("Thank you!");


                }

            }
        });


    }
    private boolean validEmail(String s){
        if(s==null|s.length()==0) return false;
        String[] arr=s.split("@");
        if(arr.length!=2) return false;
        String addr=arr[1];
        String[] arr2=addr.split("[.]");
        if(arr2.length!=2) return false;
        return true;
    }

}
