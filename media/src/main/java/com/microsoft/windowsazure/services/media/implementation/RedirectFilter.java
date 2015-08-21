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
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.RedirectionException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class RedirectFilter implements ClientResponseFilter { // extends IdempotentClientFilter {
    private final ResourceLocationManager locationManager;

    public RedirectFilter(ResourceLocationManager locationManager) {
        System.out.println("REDIRECT FILTER DONE!" + locationManager.toString());
        this.locationManager = locationManager;
    }

    public URI getBaseURI() {
        return this.locationManager.getBaseURI();
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        if (!responseContext.getStatusInfo().equals(Response.Status.MOVED_PERMANENTLY)) {
            return;
        }
        
        this.locationManager.setRedirectedURI(responseContext.getLocation());
        
        System.out.println("Location:" + responseContext.getLocation().toString());
        
        throw new RedirectionException(Response.Status.FOUND, responseContext.getLocation());
        
        /*
        Builder builder;
        // rebuild request
        if (requestContext.getMediaType() != null) {
            builder = requestContext.getClient().target(requestContext.getUri()).request(requestContext.getMediaType());
        } else {
            builder = requestContext.getClient().target(requestContext.getUri()).request();
        }
        
        // rebuild the media types.
        for(MediaType media : requestContext.getAcceptableMediaTypes()) {
            builder.accept(media);
        }
        
        // rebuild de content type and body
        Response realResponse;
        if (requestContext.hasEntity()) {
            Object entity = requestContext.getEntity();            
            realResponse = builder.method(requestContext.getMethod(), Entity.entity(entity, requestContext.getMediaType()));
        } else {
            realResponse = builder.method(requestContext.getMethod());
        }
        
        responseContext.setEntityStream((InputStream) realResponse.getEntity());
        responseContext.setStatusInfo(realResponse.getStatusInfo());
        responseContext.setStatus(realResponse.getStatus());        
        */
    }
}
