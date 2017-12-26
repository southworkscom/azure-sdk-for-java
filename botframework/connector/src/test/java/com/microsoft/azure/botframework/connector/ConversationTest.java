package com.microsoft.azure.botframework.connector;

import com.google.common.io.BaseEncoding;
import com.microsoft.azure.botframework.connector.implementation.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
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
    public void SendCardToConversation() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withName("activity")
                .withText("TEST Send Card to Conversation")
                .withAttachments(Arrays.asList(
                        new Attachment()
                            .withContentType("application/vnd.microsoft.card.hero")
                            .withContent(new HeroCard()
                                .withTitle("A static image")
                                .withSubtitle("JPEG image")
                                .withImages(Collections.singletonList(new CardImage()
                                        .withUrl("https://docs.microsoft.com/en-us/bot-framework/media/designing-bots/core/dialogs-screens.png")))),
                        new Attachment()
                                .withContentType("application/vnd.microsoft.card.hero")
                                .withContent(new HeroCard()
                                        .withTitle("An animation")
                                        .withSubtitle("GIF image")
                                        .withImages(Collections.singletonList(new CardImage()
                                                .withUrl("http://i.giphy.com/Ki55RUbOV5njy.gif"))))

                        ));

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

    @Test
    public void UpdateActivity() {

        ActivityInner activity = new ActivityInner()
                .withType(ActivityType.MESSAGE)
                .withRecipient(user)
                .withFrom(bot)
                .withText("TEST Send to Conversation");

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner response = connector.conversations().sendToConversation(conversation.id(), activity);

        ActivityInner update = activity.withId(response.id())
                .withText("TEST Update Activity");

        ResourceResponseInner updateResponse = connector.conversations().updateActivity(conversation.id(), response.id(), update);

        Assert.assertNotNull(updateResponse.id());
    }

    @Test
    public void UploadAttachment() {

        AttachmentDataInner attachment = new AttachmentDataInner()
                .withName("bot-framework.png")
                .withType("image/png")
                .withOriginalBase64(encodeToBase64(new File(getClass().getClassLoader().getResource("bot-framework.png").getFile())));

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner response = connector.conversations().uploadAttachment(conversation.id(), attachment);

        Assert.assertNotNull(response.id());
    }

    @Test
    public void GetAttachmentInfo() {

        AttachmentDataInner attachment = new AttachmentDataInner()
                .withName("bot-framework.png")
                .withType("image/png")
                .withOriginalBase64(encodeToBase64(new File(getClass().getClassLoader().getResource("bot-framework.png").getFile())));

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner attachmentResponse = connector.conversations().uploadAttachment(conversation.id(), attachment);

        AttachmentInfoInner response = connector.attachments().getAttachmentInfo(attachmentResponse.id());

        Assert.assertEquals(attachment.name(), response.name());
    }

    @Test
    public void GetAttachment() {

        byte[] attachmentPayload = encodeToBase64(new File(getClass().getClassLoader().getResource("bot_icon.png").getFile()));

        AttachmentDataInner attachment = new AttachmentDataInner()
                .withName("bot_icon.png")
                .withType("image/png")
                .withOriginalBase64(attachmentPayload);

        ConversationParametersInner createMessage = new ConversationParametersInner()
                .withMembers(Collections.singletonList(user))
                .withBot(bot);

        ConversationResourceResponseInner conversation = connector.conversations().createConversation(createMessage);

        ResourceResponseInner attachmentResponse = connector.conversations().uploadAttachment(conversation.id(), attachment);

        AttachmentInfoInner attachmentInfo = connector.attachments().getAttachmentInfo(attachmentResponse.id());

        for (AttachmentView attView : attachmentInfo.views()) {
            byte[] retrievedAttachment = connector.attachments().getAttachment(attachmentResponse.id(), attView.viewId());

            Assert.assertEquals(BaseEncoding.base64().encode(attachmentPayload), BaseEncoding.base64().encode(retrievedAttachment));
        }
    }

    private byte[] encodeToBase64(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] result = new byte[(int)file.length()];
            int size = fis.read(result);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
