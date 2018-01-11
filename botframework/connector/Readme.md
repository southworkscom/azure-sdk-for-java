# Bot Framework Connector SDK for Java

This project provides a Java package that makes it easy to manage Bot Framework Connector.

## Pre-requisites

- Java Developer Kit (JDK), v 1.7 or later
- Maven

## How to Use

### Client creation (with authentication), conversation initialization and activity send to user as an example

 ```java
  String appId = "<your-app-id>";
  String appPassword = "<your-app-password>";
  
  ConnectorCredentials credentials = new ConnectorCredentials(appId, appPassword);
  
  BotConnectorImpl client = new BotConnectorImpl("https://slack.botframework.com", credentials);
  
  ChannelAccountInner bot = new ChannelAccountInner().withId("<bot-id>");
  ChannelAccountInner user = new ChannelAccountInner().withId("<user-id>");

  ConversationParametersInner conversation = new ConversationParametersInner()
        .withBot(bot)
        .withMembers(Collections.singletonList(user))
        .withIsGroup(false);

  ConversationResourceResponseInner result = client.conversations().createConversation(conversation);
        
  ActivityInner activity = new ActivityInner()
        .withType(ActivityType.MESSAGE)
        .withFrom(bot)
        .withRecipient(user)
        .withText("this a message from Bot Connector SDK");

  ResourceResponseInner response = client.conversations().sendToConversation(result.id(), activity);
 ```

### API Documentation
 
 For API Documentation, please see our [API reference](https://docs.microsoft.com/en-us/Bot-Framework/rest-api/bot-framework-rest-connector-api-reference).
 
## Useful links

- [Microsoft Azure SDK for Java - All-up](https://github.com/WindowsAzure/azure-sdk-for-java)
- [Bot Framework - Key concepts](https://docs.microsoft.com/en-us/Bot-Framework/rest-api/bot-framework-rest-connector-concepts)