package com.microsoft.azure.botframework.connector;

import com.microsoft.azure.botframework.connector.customizations.ConnectorCredentials;
import com.microsoft.azure.botframework.connector.implementation.*;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import org.junit.Test;

import java.util.Collections;

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
