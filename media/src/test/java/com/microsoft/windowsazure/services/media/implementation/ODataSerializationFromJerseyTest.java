/**
 * Copyright Microsoft Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.windowsazure.services.media.implementation;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientProperties;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.microsoft.windowsazure.core.UserAgentFilter;
import com.microsoft.windowsazure.core.utils.DefaultDateFactory;
import com.microsoft.windowsazure.services.media.IntegrationTestBase;
import com.microsoft.windowsazure.services.media.MediaConfiguration;
import com.microsoft.windowsazure.services.media.MediaContract;
import com.microsoft.windowsazure.services.media.implementation.content.AssetType;
import com.microsoft.windowsazure.services.media.models.Asset;
import com.microsoft.windowsazure.services.media.models.AssetInfo;

public class ODataSerializationFromJerseyTest extends IntegrationTestBase {

    @Test
    @Ignore
    public void canBuildJerseyClientToCreateAnAssetWhichIsProperlyDeserialized()
            throws Exception {
        // Build a jersey client object by hand; this is working up to the
        // full integration into the media services rest proxy, but we
        // need to go step by step to begin.
        JacksonJsonProvider jacksonJsonProvider = 
                new JacksonJaxbJsonProvider()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Client c = ClientBuilder.newBuilder()
                .property(ClientProperties.FOLLOW_REDIRECTS, false)
                .register(jacksonJsonProvider)
                .register(new ODataEntityProvider())
                .build();

        // c.addFilter(new LoggingFilter(System.out));
        c.register(new RedirectFilter(createLocationManager()));
        c.register(new OAuthFilter(createTokenManager()));
        c.register(new VersionHeadersFilter());

        WebTarget assetResource = c.target("Assets");

        ODataAtomMarshaller m = new ODataAtomMarshaller();
        AssetType requestData = new AssetType();
        requestData.setName(testAssetPrefix + "firstTestAsset");
        requestData.setAlternateId("some external id");

        AssetInfo newAsset = assetResource.request(MediaType.APPLICATION_ATOM_XML)
                .accept(MediaType.APPLICATION_ATOM_XML)
                .post(Entity.xml(m.marshalEntry(requestData)), AssetInfo.class);

        Assert.assertNotNull(newAsset);
        Assert.assertEquals(testAssetPrefix + "firstTestAsset",
                newAsset.getName());
        Assert.assertEquals("some external id", newAsset.getAlternateId());
    }

    private OAuthContract createOAuthContract() {
        return new OAuthRestProxy(ClientBuilder.newClient(), new UserAgentFilter());
    }

    private OAuthTokenManager createTokenManager() throws URISyntaxException {
        return new OAuthTokenManager(
                createOAuthContract(),
                new DefaultDateFactory(),
                (String) config.getProperty(MediaConfiguration.OAUTH_URI),
                (String) config.getProperty(MediaConfiguration.OAUTH_CLIENT_ID),
                (String) config
                        .getProperty(MediaConfiguration.OAUTH_CLIENT_SECRET),
                (String) config.getProperty(MediaConfiguration.OAUTH_SCOPE));
    }

    private ResourceLocationManager createLocationManager()
            throws URISyntaxException {
        return new ResourceLocationManager(
                (String) config.getProperty(MediaConfiguration.URI));
    }

    @Test
    public void canCreateAssetThroughMediaServiceAPI() throws Exception {
        MediaContract client = createService();
        AssetInfo newAsset = client.create(Asset.create().setName(
                testAssetPrefix + "secondTestAsset"));

        Assert.assertEquals(testAssetPrefix + "secondTestAsset",
                newAsset.getName());
    }

    @Test
    public void canRetrieveListOfAssets() throws Exception {
        MediaContract client = createService();
        List<AssetInfo> assets = client.list(Asset.list());

        Assert.assertNotNull(assets);
    }

    private MediaContract createService() {
        return config.create(MediaContract.class);
    }
}
