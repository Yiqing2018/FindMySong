package com.findyoursong;

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
import java.util.ArrayList;
import java.util.List;

public class getFollowInfo extends AsyncTask<Void,Void,ArrayList<String>> {
    private OnEventListener<List<String>> mCallBack;
    public Exception mException;
    String username;

    public getFollowInfo(String username,OnEventListener callback){
        mCallBack = callback;
        this.username=username;
    }

    protected void onPostExecute(ArrayList<String> returnVal) {

        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess(returnVal);
            } else {
                mCallBack.onFailure(mException);
            }
        }

    }

    protected ArrayList<String> doInBackground(Void... voids) {
        String result="";
        String connstr = "http://findmysong.web.illinois.edu/following.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");

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
            result = e.getMessage();
        }


        ArrayList<String> returnVal=new ArrayList<String>();

        try{
            JSONArray arr = new JSONArray(result);
            for(int i=0;i<arr.length();i++){
                JSONObject tuple=arr.getJSONObject(i);
                String Name=tuple.getString("name");
                returnVal.add(Name);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }

        int followSize=returnVal.size();
        int[] userScore=new int[followSize];

        for(int i=0;i<followSize;i++){
            String anotherUser=returnVal.get(i);


            ArrayList<String> urls=new  ArrayList<String>();
            ArrayList<Integer> scores=new  ArrayList<Integer>();
            scores.add(10);
            scores.add(8);
            scores.add(6);
            urls.add("http://findmysong.web.illinois.edu/sameTrack.php");
            urls.add("http://findmysong.web.illinois.edu/sameAlbum.php");
            urls.add("http://findmysong.web.illinois.edu/sameArtist.php");

            for(int j=0;j<3;j++){
                connstr = urls.get(j);
                result="";
                try {
                    URL url = new URL(connstr);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    http.setDoInput(true);
                    http.setDoOutput(true);

                    OutputStream ops = http.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                    String data = URLEncoder.encode("user1","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")
                            +"&"+
                    URLEncoder.encode("user2","UTF-8")+"="+URLEncoder.encode(anotherUser,"UTF-8");

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
                    result = e.getMessage();
                }

                try{
                    JSONArray arr = new JSONArray(result);
                    JSONObject tuple=arr.getJSONObject(0);
                    String count=tuple.getString("count");
                    int score=Integer.valueOf(count)*scores.get(j);
                    userScore[i]+=score;
                }catch(Exception e){
                    System.out.println(e.toString());
                }

            }



        }

        for(int fenshu:userScore) returnVal.add(String.valueOf(fenshu));


        return returnVal;
    }
}
