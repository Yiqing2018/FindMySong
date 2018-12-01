package com.findyoursong;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

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
import java.util.ArrayList;

public class Song extends AsyncTask<Void,Void,String> {
    String username;
    TextView view;

    public Song(String username, TextView view){
        this.username=username;
        this.view=view;
    }

    protected String doInBackground(Void...voids){
        String result="";

        String connstr = "http://findmysong.web.illinois.edu/recom.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");

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

        }catch (Exception e){

        }

        ArrayList<String> songs=new ArrayList<String>();
        try{
            JSONArray arr = new JSONArray(result);
            for(int i=0;i<arr.length();i++){
                JSONObject tuple=arr.getJSONObject(i);
                String Name=tuple.getString("name");
                songs.add(Name);
            }
        }catch (Exception e){

        }
        int index=(int)(Math.random()*songs.size());
        return songs.get(index);
    }

    protected void onPostExecute(String s) {
        view.setText(s);

    }

    }
