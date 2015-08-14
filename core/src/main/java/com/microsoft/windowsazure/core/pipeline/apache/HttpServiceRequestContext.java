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

package com.microsoft.windowsazure.core.pipeline.apache;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
//import com.microsoft.windowsazure.core.pipeline.filter.ServiceRequestContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

public class HttpServiceRequestContext implements ClientRequestContext {
    private final HttpRequest clientRequest;
    private final HttpContext httpContext;

    public HttpServiceRequestContext(HttpRequest clientRequest,
            HttpContext httpContext) {
        this.clientRequest = clientRequest;
        this.httpContext = httpContext;
    }

    @Override
    public Object getProperty(final String name) {
        return httpContext.getAttribute(name);
    }

    @Override
    public void setProperty(final String name, final Object value) {
        httpContext.setAttribute(name, value);
    }

    @Override
    public URI getUri() {
        try {
            return new URI(clientRequest.getRequestLine().getUri());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public void setUri(final URI uri) {
        // Do nothing. not supported
    }

    @Override
    public String getMethod() {
        return clientRequest.getRequestLine().getMethod();
    }

    @Override
    public void setMethod(String method) {
        // Do nothing. not supported
    }

    @Override
    public Object getEntity() {
        // Do nothing. not supported
        return null;
    }

    @Override
    public void setEntity(final Object entity) {
        // Do nothing. not supported
    }

    @Override
    public String getHeader(final String name) {
        final Header first = clientRequest.getFirstHeader(name);
        return first != null ? first.getValue() : null;
    }

    @Override
    public void setHeader(final String name, final String value) {
        clientRequest.setHeader(name, value);
    }

    @Override
    public void removeHeader(final String name) {
        clientRequest.removeHeaders(name);
    }

    @Override
    public Collection<String> getPropertyNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeProperty(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public MultivaluedMap<String, Object> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getHeaderString(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Locale getLanguage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MediaType getMediaType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MediaType> getAcceptableMediaTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Locale> getAcceptableLanguages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Cookie> getCookies() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasEntity() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Class<?> getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type getEntityType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEntity(Object entity, Annotation[] annotations, MediaType mediaType) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Annotation[] getEntityAnnotations() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OutputStream getEntityStream() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEntityStream(OutputStream outputStream) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Client getClient() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void abortWith(Response response) {
        // TODO Auto-generated method stub
        
    }
}