package com.findyoursong;

import android.os.AsyncTask;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class setText extends AsyncTask<Void,Void,String> {
    private OnEventListener<List<String>> mCallBack;
    public Exception mException;
    String phpPath;
    List<TextView> tvs;



    public setText(String phpPath,List<TextView> list,OnEventListener callback){
        this.tvs=list;
        mCallBack = callback;
        this.phpPath=phpPath;
    }

    protected void onPostExecute(String s) {
        ArrayList<String> returnVal=new ArrayList<String>();
        try{
            JSONArray arr = new JSONArray(s);
            for(int i=0;i<5;i++){
                JSONObject tuple=arr.getJSONObject(i);
                String Name=tuple.getString("name");
                String URL=tuple.getString("url");
                returnVal.add(URL);
                tvs.get(i).setText(Name);
            }
        }catch (Exception e){
            e.printStackTrace();
            mException = e;
        }

        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess(returnVal);
            } else {
                mCallBack.onFailure(mException);
            }
        }

    }

    protected String doInBackground(Void... voids) {
        String result="";
        String connstr = phpPath;
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
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

        return result;
    }
}
