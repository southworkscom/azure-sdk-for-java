/*
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

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.microsoft.windowsazure.exception.ServiceException;

/**
 * The Jersey filter for OAuth.
 * 
 */
public class OAuthFilter  implements ClientRequestFilter {
    private final OAuthTokenManager oAuthTokenManager;

    /**
     * Creates an <code>OAuthFilter</code> object with specified
     * <code>OAuthTokenManager</code> instance.
     * 
     * @param oAuthTokenManager
     */
    public OAuthFilter(OAuthTokenManager oAuthTokenManager) {
        this.oAuthTokenManager = oAuthTokenManager;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        
        String accessToken;
        try {
            accessToken = oAuthTokenManager.getAccessToken();
        } catch (ServiceException e) {
            // must wrap exception because of base class signature
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            // must wrap exception because of base class signature
            throw new RuntimeException(e);
        }

        requestContext.getHeaders()
                .add("Authorization", "Bearer " + accessToken);
    }

}
