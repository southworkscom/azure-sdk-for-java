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

import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.RedirectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.exception.ServiceExceptionFactory;
import com.microsoft.windowsazure.services.media.MediaContract;
import com.microsoft.windowsazure.services.media.UniformInterfaceException;
import com.microsoft.windowsazure.services.media.entityoperations.EntityActionOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityCreateOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityDeleteOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityGetOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityListOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityTypeActionOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityUpdateOperation;
import com.microsoft.windowsazure.services.media.models.ListResult;

/**
 * /** Wrapper implementation of <code>MediaEntityContract</code> that
 * translates exceptions into ServiceExceptions.
 * 
 */
public class MediaExceptionProcessor implements MediaContract {

    /** The service. */
    private final MediaContract service;

    /** The log. */
    private static Log log = LogFactory.getLog(MediaContract.class);

    /**
     * Instantiates a new media exception processor.
     * 
     * @param service
     *            the service
     */
    public MediaExceptionProcessor(MediaContract service) {
        this.service = service;
    }

    /**
     * Instantiates a new media exception processor.
     * 
     * @param service
     *            the service
     */
    @Inject
    public MediaExceptionProcessor(MediaRestProxy service) {
        this.service = service;
    }
    /**
     * Process a catch.
     * 
     * @param e
     *            the e
     * @return the service exception
     */
    private ServiceException processCatch(ServiceException e) {
        log.warn(e.getMessage(), e.getCause());
        return ServiceExceptionFactory.process("MediaServices", e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #create(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityCreateOperation)
     */
    @Override
    public <T> T create(EntityCreateOperation<T> creator)
            throws ServiceException {
        try {
            return service.create(creator);
        } catch (RedirectionException e) {
            return service.create(creator);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
   
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #get(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityGetOperation)
     */
    @Override
    public <T> T get(EntityGetOperation<T> getter) throws ServiceException {
        try {
            return service.get(getter);
        } catch (RedirectionException e) {
            return service.get(getter);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #list(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityListOperation)
     */
    @Override
    public <T> ListResult<T> list(EntityListOperation<T> lister)
            throws ServiceException {
        try {
            return service.list(lister);
        } catch (RedirectionException e) {
            return service.list(lister);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
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
        try {
            return service.update(updater);
        } catch (RedirectionException e) {
            return service.update(updater);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #delete(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityDeleteOperation)
     * 
     * @return operation-id if any otherwise null.
     */
    @Override
    public String delete(EntityDeleteOperation deleter) throws ServiceException {
        try {
            return service.delete(deleter);
        } catch (RedirectionException e) {
            return service.delete(deleter);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
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
        try {
            return service.action(entityActionOperation);
        } catch (RedirectionException e) {
            return service.action(entityActionOperation);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.microsoft.windowsazure.services.media.entityoperations.EntityContract
     * #action(com.microsoft.windowsazure.services.media.entityoperations.
     * EntityTypeActionOperation)
     */
    @Override
    public <T> T action(EntityTypeActionOperation<T> entityTypeActionOperation)
            throws ServiceException {
        try {
            return service.action(entityTypeActionOperation);
        } catch (RedirectionException e) {
            return service.action(entityTypeActionOperation);
        } catch (UniformInterfaceException e) {
            throw processCatch(new ServiceException(e));
        } catch (ClientErrorException e) {
            throw processCatch(new ServiceException(e));
        }
    }

}
