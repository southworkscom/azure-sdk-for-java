package com.microsoft.azure.botframework.connector;

import com.microsoft.azure.botframework.connector.implementation.BotConnectorImpl;
import com.microsoft.azure.botframework.connector.implementation.ChannelAccountInner;
import com.microsoft.rest.RestClient;

public class BotConnectorTestBase extends TestBase {
    protected BotConnectorImpl connector;
    protected ChannelAccountInner bot;
    protected ChannelAccountInner user;

    public BotConnectorTestBase() {
        super(RunCondition.BOTH);
    }

    public BotConnectorTestBase(RunCondition runCondition) {
        super(runCondition);
    }

    @Override
    protected void initializeClients(RestClient restClient, String botId, String userId) {
        connector = new BotConnectorImpl(restClient);
        bot = new ChannelAccountInner().withId(botId);
        user = new ChannelAccountInner().withId(userId);
    }

    @Override
    protected void cleanUpResources() {
    }
}