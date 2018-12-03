package com.findyoursong;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class insertListen extends AsyncTask<Void,Void,Void> {
    String user_id; //follower_id
    String track_id; //followed_id
    public insertListen(String user_id,String track_id){
        this.user_id=user_id;
        this.track_id=track_id;
    }
    protected Void doInBackground(Void...voids){
        String connstr = "http://findmysong.web.illinois.edu/insertListen.php";
        String result="";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"
                    +URLEncoder.encode("track_id","UTF-8")+"="+URLEncoder.encode(track_id,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();

            http.disconnect();

        } catch (Exception e) {
            System.out.println(e.toString());
        }



        return null;
    }

}
