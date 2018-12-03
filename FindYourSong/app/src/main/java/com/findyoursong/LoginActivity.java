package com.findyoursong;

import android.app.Dialog;
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

public class LoginActivity extends AppCompatActivity
{
    public static final String CLIENT_ID = "fc315c61f6ed43eb9f117df2eed86f76";
    public static final String CLIENT_SECRET = "310614ccadff4c08be040ffe76c248c9";
    public static final String SCOPES = "user-read-recently-played user-top-read user-library-read user-read-private";
    public static final String CALLBACK = "findyoursong://callback";
    public static final int AUTH_TOKEN_REQUEST_CODE = 0x10;

    TextView usernameView, passwordView;
    Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameView = (TextView)findViewById(R.id.input_username);
        passwordView = (TextView)findViewById(R.id.input_username);

        connectButton = (Button) findViewById(R.id.connectBtn);
        connectButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

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
            Intent intent = new Intent(this, HomePage.class);
            intent.putExtra("token", response.getAccessToken());
            startActivity(intent);
        }
    }
}
