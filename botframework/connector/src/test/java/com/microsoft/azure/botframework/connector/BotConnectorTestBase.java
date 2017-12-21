package com.microsoft.azure.botframework.connector;

import com.microsoft.azure.botframework.connector.implementation.BotConnectorImpl;
import com.microsoft.rest.RestClient;

public class BotConnectorTestBase extends TestBase {
    protected BotConnectorImpl connector;

    public BotConnectorTestBase() {
        super(RunCondition.BOTH);
    }

    public BotConnectorTestBase(RunCondition runCondition) {
        super(runCondition);
    }

    @Override
    protected void initializeClients(RestClient restClient, String defaultSubscription, String domain) {
        connector = new BotConnectorImpl(restClient);
    }

    @Override
    protected void cleanUpResources() {
    }
}