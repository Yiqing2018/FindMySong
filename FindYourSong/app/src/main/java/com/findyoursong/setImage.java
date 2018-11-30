package com.findyoursong;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class setImage extends AsyncTask<Void, Void, Void> {

    List<ImageView> mvs;
    List<TextView> tvs;
    List<String> urls;

    public setImage(List<TextView> tvs, List<ImageView> mvs, List<String> urls){
        this.mvs=mvs;
        this.tvs=tvs;
        this.urls=urls;
    }

    protected Void doInBackground(Void... values) {
        for(int i=0;i<5;i++){
            String name=tvs.get(i).getText().toString();
            try{
                mvs.get(i).setImageBitmap(getBitmap(urls.get(i)));
            }catch (Exception e){
                tvs.get(i).setText(e.getMessage());
            }finally {
                tvs.get(i).setText(name);
            }
        }

        return null;
    }

    public static Bitmap getBitmap(String path) throws IOException{

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }
}
