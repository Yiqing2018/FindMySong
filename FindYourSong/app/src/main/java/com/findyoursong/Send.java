package com.findyoursong;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Send extends AsyncTask<Void,Void,Void> {
    String username="";
    String txt="";
    String email="";
    TextView tv;
    public Send(String username, String txt, String email, TextView tv){
        this.username=username;
        this.txt=txt;
        this.email=email;
        this.tv=tv;

    }
    protected Void doInBackground(Void...voids){
        String connstr = "http://findmysong.web.illinois.edu/feedback.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("username","UTF-8") +"="+URLEncoder.encode(username,"UTF-8")
                    +"&"+URLEncoder.encode("user_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                    +"&"+URLEncoder.encode("user_feedback","UTF-8")+"="+URLEncoder.encode(txt,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            reader.close();
            ips.close();
            http.disconnect();

        } catch (Exception e) {
        }
        return null;

    }

}
