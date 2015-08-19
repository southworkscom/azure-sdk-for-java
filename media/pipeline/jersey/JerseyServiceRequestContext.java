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

package com.microsoft.windowsazure.core.pipeline.jersey;

import com.microsoft.windowsazure.core.pipeline.filter.ServiceRequestContext;
//import com.sun.jersey.api.client.ClientRequest;
import java.net.URI;
import java.util.List;

import org.glassfish.jersey.client.ClientRequest;

public class JerseyServiceRequestContext implements ServiceRequestContext {
    private ClientRequest clientRequest;

    public JerseyServiceRequestContext(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    @Override
    public Object getProperty(String name) {
        return clientRequest.getProperty(name);
    }

    @Override
    public void setProperty(String name, Object value) {
        clientRequest.setProperty(name, value);
    }

    @Override
    public URI getURI() {
        return clientRequest.getUri();
    }

    @Override
    public void setURI(URI uri) {
        clientRequest.setUri(uri);
    }

    @Override
    public String getMethod() {
        return clientRequest.getMethod();
    }

    @Override
    public void setMethod(String method) {
        clientRequest.setMethod(method);
    }

    @Override
    public Object getEntity() {
        return clientRequest.getEntity();
    }

    @Override
    public void setEntity(Object entity) {
        clientRequest.setEntity(entity);
    }

    @Override
    public String getHeader(String name) {
        List<Object> headers = clientRequest.getHeaders().get(name);
        if (headers != null && headers.size() > 0) {
            return (String) headers.get(0);
        }

        return null;
    }

    @Override
    public void setHeader(String name, String value) {
        clientRequest.getHeaders().add(name, value);
    }

    @Override
    public void removeHeader(String name) {
        clientRequest.getHeaders().remove(name);
    }
}