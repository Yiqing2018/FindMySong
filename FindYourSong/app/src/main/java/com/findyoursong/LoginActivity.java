package com.findyoursong;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity
{
    public static final String CLIENT_ID = "fc315c61f6ed43eb9f117df2eed86f76";
    public static final String CLIENT_SECRET = "310614ccadff4c08be040ffe76c248c9";
    public static final String SCOPES = "user-read-recently-played user-top-read user-library-read user-read-private";
    public static final String CALLBACK = "findyoursong://callback";
    public static final int AUTH_TOKEN_REQUEST_CODE = 0x10;

    private final OkHttpClient CLIENT = new OkHttpClient();

    TextView resultsView;
    String username;
    String user_id;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;

        resultsView = (TextView)findViewById(R.id.resultsView);

        final AuthenticationRequest request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN);
        AuthenticationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request);
    }

    private AuthenticationRequest getAuthenticationRequest(AuthenticationResponse.Type type)
    {
        return new AuthenticationRequest.Builder(CLIENT_ID, type, CALLBACK)
                .setShowDialog(false)
                .setScopes(SCOPES.split(" "))
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

        if (AUTH_TOKEN_REQUEST_CODE == requestCode)
        {
//            Intent intent = new Intent(this, HomePage.class);
//            intent.putExtra("token", response.getAccessToken());
//            startActivity(intent);

            getProfile(response.getAccessToken());
            getRecentTracks(response.getAccessToken());

        }
    }

    private void getProfile(String token)
    {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization","Bearer " + token)
                .build();

        Call call = CLIENT.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
//                updateResults(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    final JSONObject jsonObject = new JSONObject(response.body().string());
//                    updateResults("user_id"+"\n"+jsonObject.getString("id")+"\n"+
//                            "display_name: " + jsonObject.getString("display_name"));
                    user_id=jsonObject.getString("id");
                    username=jsonObject.getString("display_name");
                    insertUser insert=new insertUser(user_id,username);
                    insert.execute();
                }
                catch (JSONException e)
                {
//                    updateResults("Failed to parse data: " + e);
                }
            }
        });
    }

    private void getRecentTracks(String token)
    {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me/player/recently-played")
                .addHeader("Authorization","Bearer " + token)
                .build();

        Call call = CLIENT.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
//                updateResults(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    final JSONObject jsonObject = new JSONObject(response.body().string());


                    JSONArray arr=jsonObject.getJSONArray("items");
                    for(int i=0;i<arr.length();i++){
                        JSONObject obj=arr.getJSONObject(i);
                        JSONObject track=obj.getJSONObject("track");
                        String track_id=track.getString("id");
                        insertListen insert=new insertListen(user_id,track_id);
                        insert.execute();

//                        updateResults(tmp+"\n");
                    }
//                    JSONObject obj=arr.getJSONObject(0);
//
//                    JSONObject track=obj.getJSONObject("track");
//                    String tmp=track.getString("id");
//
//                    String message=obj.toString(2);
//
//                    updateResults(tmp+"\n");
                    Intent intent = new Intent(context, HomePage.class);
                    intent.putExtra("id", username);
                    startActivity(intent);

                }
                catch (JSONException e)
                {
//                    updateResults("Failed to parse data: " + e);
                }
            }
        });
    }

//    private void updateResults(final String s)
//    {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                resultsView.append(s + "\n");
//            }
//        });
//    }
}
