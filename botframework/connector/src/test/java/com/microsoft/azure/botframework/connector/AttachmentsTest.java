package com.microsoft.azure.botframework.connector;

import com.google.common.io.BaseEncoding;
import com.microsoft.azure.botframework.connector.implementation.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;

public class AttachmentsTest extends BotConnectorTestBase {

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
            return new byte[0];
        }
    }
}
