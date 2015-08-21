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

package com.microsoft.windowsazure.services.media.entityoperations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;

import javax.ws.rs.client.Invocation.Builder;
import com.microsoft.windowsazure.core.pipeline.PipelineHelpers;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.media.models.ListResult;

/**
 * The Class EntityRestProxy.
 */
public abstract class EntityRestProxy implements EntityContract {

    /** The executor service. */
    private final ExecutorService executorService;
    /** The channel. */
    private final Client channel;
    /** The filters. */
    private final ClientRequestFilter[] requestFilters;
    private final ClientResponseFilter[] responseFilters;

    /**
     * Instantiates a new entity rest proxy.
     * 
     * @param channel
     *            the channel
     * @param filters
     *            the filters
     */
    public EntityRestProxy(Client channel, ClientRequestFilter[] requestFilters, ClientResponseFilter[] responseFilters) {
        this.channel = channel;
        this.requestFilters = requestFilters;
        this.responseFilters = responseFilters;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Gets the channel.
     * 
     * @return the channel
     */
    protected Client getChannel() {
        return channel;
    }

    /**
     * Gets the executor service.
     * 
     * @return the executor service
     */
    protected ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Gets the filters.
     * 
     * @return the filters
     */
    protected ClientRequestFilter[] getRequestFilters() {
        return requestFilters;
    }
    
    protected ClientResponseFilter[] getResponseFilters() {
        return responseFilters;
    }

    /**
     * Get the proxy data to pass to operations.
     * 
     * @return The proxy data.
     */
    protected abstract EntityProxyData createProxyData();

    /**
     * Gets the resource.
     * 
     * @param entityName
     *            the entity name
     * @return the resource
     */
    private WebTarget getTarget(String entityName) {
        WebTarget resource = channel.target(entityName);
        for (ClientRequestFilter filter : requestFilters) {
            resource.register(filter);
        }
        for (ClientResponseFilter filter : responseFilters) {
            resource.register(filter);
        }
        return resource;
    }

    /**
     * Gets the resource.
     * 
     * @param operation
     *            the operation
     * @return the resource
     * @throws ServiceException
     *             the service exception
     */
    private Builder getTarget(EntityOperation operation)
            throws ServiceException {
        return getTarget(operation.getUri()).request(operation.getAcceptType());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #create(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityCreateOperation)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(EntityCreateOperation<T> creator)
            throws ServiceException {
        creator.setProxyData(createProxyData());
        Object rawResponse = getTarget(creator).post(
                Entity.entity(creator.getRequestContents(), creator.getContentType()),
                creator.getResponseClass());
        Object processedResponse = creator.processResponse(rawResponse);
        return (T) processedResponse;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #get(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityGetOperation)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(EntityGetOperation<T> getter) throws ServiceException {
        getter.setProxyData(createProxyData());
        Object rawResponse = getTarget(getter).get(getter.getResponseClass());
        Object processedResponse = getter.processResponse(rawResponse);
        return (T) processedResponse;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #list(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityListOperation)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> ListResult<T> list(EntityListOperation<T> lister)
            throws ServiceException {
        lister.setProxyData(createProxyData());
        WebTarget target = getTarget(lister.getUri());
        
        MultivaluedMap<String, String> params = lister.getQueryParameters();
        for(String key : params.keySet()) {
            for(String value : params.get(key)) {
                target = target.queryParam(key , value);
            }
        }        
        
        Object rawResponse = target.request(lister.getContentType())
                                    .accept(lister.getAcceptType())
                                    
                                    .get(lister.getResponseGenericType());
        
        Object processedResponse = lister.processResponse(rawResponse);
        return (ListResult<T>) processedResponse;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #update(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityUpdateOperation)
     */
    @Override
    public String update(EntityUpdateOperation updater) throws ServiceException {
        updater.setProxyData(createProxyData());
        Response clientResponse = getTarget(updater).header("X-HTTP-METHOD",
                "MERGE").post(
                        Entity.entity(updater.getRequestContents(), updater.getContentType()));
        PipelineHelpers.throwIfNotSuccess(clientResponse);
        updater.processResponse(clientResponse);
        if (clientResponse.getHeaders().containsKey("operation-id")) {
            List<Object> operationIds = clientResponse.getHeaders().get("operation-id");
            if (operationIds.size() >= 0) {
                return operationIds.get(0).toString();
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #delete(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityDeleteOperation)
     * @return operation-id if any otherwise null.
     */
    @Override
    public String delete(EntityDeleteOperation deleter) throws ServiceException {
        deleter.setProxyData(createProxyData());
        Response clientResponse =  getTarget(deleter.getUri())
                .request().delete();
        PipelineHelpers.throwIfNotSuccess(clientResponse);
        if (clientResponse.getHeaders().containsKey("operation-id")) {
            List<Object> operationIds = clientResponse.getHeaders().get("operation-id");
            if (operationIds.size() >= 0) {
                return operationIds.get(0).toString();
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #action(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityActionOperation)
     */
    @Override
    public <T> T action(EntityTypeActionOperation<T> entityTypeActionOperation)
            throws ServiceException {
        entityTypeActionOperation.setProxyData(createProxyData());
        WebTarget target = getTarget(entityTypeActionOperation.getUri());
        
        MultivaluedMap<String, String> params = entityTypeActionOperation.getQueryParameters();
        for(String key : params.keySet()) {
            for(String value : params.get(key)) {
                target = target.queryParam(key , value);
            }
        }
                
        Builder builder = target.request(MediaType.APPLICATION_XML);
        
        builder.accept(MediaType.APPLICATION_XML_TYPE);

        Response clientResponse = builder.method(entityTypeActionOperation.getVerb(), 
                Entity.entity(entityTypeActionOperation.getRequestContents(), 
                        MediaType.APPLICATION_XML_TYPE));
     
        return entityTypeActionOperation.processTypeResponse(clientResponse);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #action(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityActionOperation)
     */
    @Override
    public String action(EntityActionOperation entityActionOperation)
            throws ServiceException {
        Response clientResponse = getActionClientResponse(entityActionOperation);
        entityActionOperation.processResponse(clientResponse);
        
        PipelineHelpers.throwIfNotSuccess(clientResponse);
        
        if (clientResponse.getHeaders().containsKey("operation-id")) {
            List<Object> operationIds = clientResponse.getHeaders().get("operation-id");
            if (operationIds.size() >= 0) {
                return operationIds.get(0).toString();
            }
        }
        return null;
    }

    /**
     * Gets the action client response.
     * 
     * @param entityActionOperation
     *            the entity action operation
     * @return the action client response
     */
    private Response getActionClientResponse(
            EntityActionOperation entityActionOperation) {
        entityActionOperation.setProxyData(createProxyData());
        WebTarget target = getTarget(entityActionOperation.getUri());
        
        MultivaluedMap<String, String> params = entityActionOperation.getQueryParameters();
        for(String key : params.keySet()) {
            for(String value : params.get(key)) {
                target = target.queryParam(key , value);
            }
        }
        
        Builder builder = target.request(entityActionOperation.getAcceptType());
        
        if (entityActionOperation.getRequestContents() != null) {
            return builder.method(entityActionOperation.getVerb(), 
                    Entity.entity(entityActionOperation.getRequestContents(), 
                            entityActionOperation.getContentType()));
        } else {
            builder = builder.header("Content-Length", "0");
            return builder.method(entityActionOperation.getVerb());
        }
        
    }
}
