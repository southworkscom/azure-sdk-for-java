package com.microsoft.azure.botframework.connector.customizations;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.microsoft.rest.credentials.TokenCredentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ConnectorCredentials extends TokenCredentials {
    private String clientId;
    private String clientSecret;

    private OkHttpClient client;
    private Gson gson;

    public ConnectorCredentials(String clientId, String clientSecret) {
        super("Bearer", "");
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        client = new OkHttpClient.Builder().build();
        gson = new Gson();
    }

    @Override
    protected String getToken(Request request) throws IOException {
        Request reqToken = request.newBuilder()
                .url("https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token")
                .post(new FormBody.Builder()
                        .add("grant_type", "client_credentials")
                        .add("client_id", clientId)
                        .add("client_secret", clientSecret)
                        .add("scope", "https://api.botframework.com/.default")
                        .build()).build();
        Response response = client.newCall(reqToken).execute();
        String payload = response.body().string();
        AuthenticationResponse authResponse = gson.fromJson(payload, AuthenticationResponse.class);
        return authResponse.accessToken;
    }

    private class AuthenticationResponse {
        @SerializedName("token_type")
        private String tokenType;
        @SerializedName("expires_in")
        private Integer expiresIn;
        @SerializedName("ext_expires_in")
        private Integer extExpiresIn;
        @SerializedName("access_token")
        private String accessToken;
    }
}
