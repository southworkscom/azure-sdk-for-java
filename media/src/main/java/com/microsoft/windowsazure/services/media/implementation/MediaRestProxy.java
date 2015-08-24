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

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;

import com.microsoft.windowsazure.core.UserAgentFilter;
import com.microsoft.windowsazure.core.pipeline.jersey.ClientConfigSettings;
import com.microsoft.windowsazure.services.media.MediaContract;
import com.microsoft.windowsazure.services.media.entityoperations.EntityProxyData;
import com.microsoft.windowsazure.services.media.entityoperations.EntityRestProxy;

/**
 * The Class MediaRestProxy.
 */
public class MediaRestProxy extends EntityRestProxy implements MediaContract {
    /** The redirect filter. */
    private SetMediaUriFilter mediaUriFilter;
    @SuppressWarnings("unused")
    private final ClientConfigSettings clientConfigSettings;

    /**
     * Instantiates a new media rest proxy.
     * 
     * @param channel
     *            the channel
     * @param authFilter
     *            the auth filter
     * @param redirectFilter
     *            the redirect filter
     * @param versionHeadersFilter
     *            the version headers filter
     * @param userAgentFilter
     *            the user agent filter
     * @param clientConfigSettings
     *            Currently configured HTTP client settings
     * 
     */
    @Inject
    public MediaRestProxy(Client channel, OAuthFilter authFilter,
            RedirectFilter redirectFilter,
            SetMediaUriFilter mediaUriFilter,
            VersionHeadersFilter versionHeadersFilter,
            UserAgentFilter userAgentFilter,
            ClientConfigSettings clientConfigSettings) {
        super(channel, new ClientRequestFilter[0], new ClientResponseFilter[0]);

        this.clientConfigSettings = clientConfigSettings;
        this.mediaUriFilter = mediaUriFilter;
        channel.register(redirectFilter);        
        channel.register(mediaUriFilter);
        channel.register(authFilter);
        channel.register(versionHeadersFilter);
        channel.register(userAgentFilter);
    }

    /**
     * Instantiates a new media rest proxy.
     * 
     * @param channel
     *            the channel
     * @param filters
     *            the filters
     * @param clientConfigSettings
     *            currently configured HTTP client settings
     */
    private MediaRestProxy(Client channel, ClientRequestFilter[] requestFilters,
            ClientResponseFilter[] responseFilters,
            ClientConfigSettings clientConfigSettings) {
        super(channel, requestFilters, responseFilters);
        this.clientConfigSettings = clientConfigSettings;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityRestProxy
     * #createProxyData()
     */
    @Override
    protected EntityProxyData createProxyData() {
        return new EntityProxyData() {
            @Override
            public URI getServiceUri() {
                return mediaUriFilter.getBaseURI();
            }
        };
    }


}
