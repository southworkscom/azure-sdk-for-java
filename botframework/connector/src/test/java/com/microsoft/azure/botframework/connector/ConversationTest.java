package com.microsoft.azure.botframework.connector;

import com.microsoft.azure.botframework.connector.implementation.*;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ConversationTest extends  BotConnectorTestBase {

    @Test
    public void CreateConversation() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withText("TEST Create Conversation");

        ConversationParametersInner params = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot)
                .withActivity(activity);

        ConversationResourceResponseInner result = connector.conversations().createConversation(params);

        Assert.assertNotNull(result.activityId());
    }

    @Test
    public void GetConversationMembers() {

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        List<ChannelAccountInner> members = connector.conversations().getConversationMembers(conversation.id());

        boolean hasUser = false;

        for (ChannelAccountInner member : members) {
            hasUser = member.id().equals(user.id());
            if (hasUser) break;
        }

        Assert.assertTrue(hasUser);
    }

    @Test
    public void SendToConversation() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withName("activity")
                .withText("TEST Send to Conversation");

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner response = connector.conversations().sendToConversation(conversation.id(), activity);

        Assert.assertNotNull(response.id());
    }

    @Test
    public void GetActivityMembers() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withText("TEST Get Activity Members");

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot)
                .withActivity(activity);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        List<ChannelAccountInner> members = connector.conversations().getActivityMembers(conversation.id(), conversation.activityId());

        boolean hasUser = false;

        for (ChannelAccountInner member : members) {
            hasUser = member.id().equals(user.id());
            if (hasUser) break;
        }

        Assert.assertTrue(hasUser);
    }

    @Test
    public void ReplyToActivity() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withText("TEST Send to Conversation");

        ActivityInner reply = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withText("TEST Reply to Activity");

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner response = connector.conversations().sendToConversation(conversation.id(), activity);

        ResourceResponseInner replyResponse = connector.conversations().replyToActivity(conversation.id(), response.id(), reply);

        Assert.assertNotNull(replyResponse.id());
    }

    @Test
    public void DeleteActivity() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withText("TEST Delete Activity");

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot)
                .withActivity(activity);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        connector.conversations().deleteActivity(conversation.id(), conversation.activityId());

        Assert.assertNotNull(conversation.activityId());
    }
}
