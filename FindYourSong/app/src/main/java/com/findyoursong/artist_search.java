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

public class artist_search extends AsyncTask<String,Void,String> {
    String res;
    String artistName;
    Context context;
    AlertDialog dialog;
    //initialize
    public artist_search(Context context){
        this.context=context;
    }
    //do something before executing
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
    }


    //what to show on the dialog
    protected void onPostExecute(String s) {
//        dialog.setMessage(s);
        if(s.equals("404")) {
            dialog.setMessage("No such artist");
            dialog.show();
            return;
        }
        String message="Most FIVE Popular Songs BY "+artistName+"\n\n";
        try{
            JSONArray arr = new JSONArray(s);
            for (int i=0;i<5;i++){
                message+="Rank "+(i+1)+" : ";
                message+=arr.getJSONObject(i).getString("track_name");
                message+="\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        dialog.setMessage(message);
        dialog.show();

    }

    protected String doInBackground(String... voids) {
        String result="";
        artistName= voids[0];
        String connstr = "http://findmysong.web.illinois.edu/artist_lookup.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("artist","UTF-8")+"="+URLEncoder.encode(artistName,"UTF-8");

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
