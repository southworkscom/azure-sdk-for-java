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

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;


public class SetMediaUriFilter implements ClientRequestFilter { // extends IdempotentClientFilter {
    private final ResourceLocationManager locationManager;

    public SetMediaUriFilter(ResourceLocationManager locationManager) {
        System.out.println("SetMediaUriFilter DONE!" + locationManager.toString());
        this.locationManager = locationManager;
    }

    public URI getBaseURI() {
        return this.locationManager.getBaseURI();
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        URI originalURI = requestContext.getUri();
        System.out.println("ORIURI:" + originalURI.toString());
        System.out.println("RDTURI:" + locationManager.getRedirectedURI(originalURI));
        requestContext.setUri(locationManager.getRedirectedURI(originalURI));
    }
}
