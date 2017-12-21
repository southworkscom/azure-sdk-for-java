package com.microsoft.azure.botframework.connector;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.botframework.connector.customizations.ConnectorCredentials;
import com.microsoft.azure.botframework.connector.implementation.*;
import com.microsoft.azure.credentials.AzureTokenCredentials;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.ServiceResponseBuilder;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import com.microsoft.rest.credentials.TokenCredentials;
import com.microsoft.rest.serializer.JacksonAdapter;
import okhttp3.*;
import okio.BufferedSink;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

class AuthenticationResponse {
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public Integer expiresIn;
    @SerializedName("ext_expires_in")
    public Integer extExpiresIn;
    @SerializedName("access_token")
    public String accessToken;
}

public class BotConnectorTest {

    @Test
    public  void CreateConversation() throws Exception {
        String clientId = "a4e3b5f5-4d52-4378-a1f8-7c177fb5eb68";
        String clientSecret = "ujnV6~{cvpcECDEOJ6019_|";

        String baseUrl = "https://smba.trafficmanager.net/apis/";

        ServiceClientCredentials credentials = new ConnectorCredentials(clientId, clientSecret);
        BotConnectorImpl connector = new BotConnectorImpl(baseUrl, credentials);

        String botId = "28:a4e3b5f5-4d52-4378-a1f8-7c177fb5eb68";
        String userId = "29:1wbew5yiGBFqsT1I4OKnKn6Us4BLp9E7R8ahZFVFbpCw";

        ChannelAccountInner bot = new ChannelAccountInner().withId(botId);
        ChannelAccountInner user = new ChannelAccountInner().withId(userId);

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withChannelId("skype")
                .withFrom(bot)
                .withRecipient(user)
                .withText("This is a new message from JAVA");

        ConversationParametersInner params = new ConversationParametersInner()
                .withBot(bot)
                .withMembers(Collections.singletonList(user))
                .withActivity(activity);

        connector.conversations().createConversation(params);
    }
}
