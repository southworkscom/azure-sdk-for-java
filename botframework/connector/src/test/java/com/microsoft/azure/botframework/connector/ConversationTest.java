package com.microsoft.azure.botframework.connector;

import com.microsoft.azure.botframework.connector.implementation.ActivityInner;
import com.microsoft.azure.botframework.connector.implementation.ChannelAccountInner;
import com.microsoft.azure.botframework.connector.implementation.ConversationParametersInner;
import com.microsoft.azure.botframework.connector.implementation.ConversationResourceResponseInner;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class ConversationTest extends  BotConnectorTestBase {
    @Test
    public void CreateConversation() {

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

        ConversationResourceResponseInner result = connector.conversations().createConversation(params);

        Assert.assertNotNull(result.serviceUrl());
    }
}
