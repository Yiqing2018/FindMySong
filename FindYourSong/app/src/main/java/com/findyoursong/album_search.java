package com.findyoursong;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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

public class album_search extends AsyncTask<String,Void,String> {
    String res;
    String albumName;
    Context context;
    AlertDialog dialog;
    //initialize
    public album_search(Context context){
        this.context=context;
    }
    //do something before executing
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
    }

    private String parseJSONWithJSONObject(String jsonData,String key) {
        String res = "";
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String val = jsonObject.getString(key);
            res=val;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //what to show on the dialog
    protected void onPostExecute(String s) {
//        dialog.setMessage(s);
        if(s.equals("404")) {
            dialog.setMessage("No such album");
            dialog.show();
            return;
        }
        String name=parseJSONWithJSONObject(s,"artist_name");
        String album=parseJSONWithJSONObject(s,"album_name");
        String yr=parseJSONWithJSONObject(s,"release_year");
        String genre=parseJSONWithJSONObject(s,"genre_name");

        String alert1 = "Album Name: "+album;
        String alert2 = "Genre: "+genre;
        String alert3 ="Artist Name: "+name;
        String alert4 = "Release year: "+yr;
        dialog.setMessage(alert1 +"\n\n"+ alert2 +"\n\n"+ alert3+"\n\n"+ alert4);
        dialog.show();

    }

    protected String doInBackground(String... voids) {
        String result="";
        albumName= voids[0];
        String connstr = "http://findmysong.web.illinois.edu/album_lookup.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("album","UTF-8")+"="+URLEncoder.encode(albumName,"UTF-8");

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
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }

        res=result;
        return result;
    }
}
