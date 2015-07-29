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

package com.microsoft.windowsazure.services.media.models;

import java.util.List;

import com.microsoft.windowsazure.services.media.entityoperations.DefaultDeleteOperation;
import com.microsoft.windowsazure.services.media.entityoperations.DefaultEntityActionOperation;
import com.microsoft.windowsazure.services.media.entityoperations.DefaultGetOperation;
import com.microsoft.windowsazure.services.media.entityoperations.DefaultListOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityActionOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityCreateOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityDeleteOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityGetOperation;
import com.microsoft.windowsazure.services.media.entityoperations.EntityOperationBase;
import com.microsoft.windowsazure.services.media.entityoperations.EntityOperationSingleResultBase;
import com.microsoft.windowsazure.services.media.entityoperations.EntityProxyData;
import com.microsoft.windowsazure.services.media.entityoperations.EntityUpdateOperation;
import com.microsoft.windowsazure.services.media.implementation.content.CrossSiteAccessPoliciesType;
import com.microsoft.windowsazure.services.media.implementation.content.StreamingEndpointAccessControlType;
import com.microsoft.windowsazure.services.media.implementation.content.StreamingEndpointCacheControlType;
import com.microsoft.windowsazure.services.media.implementation.content.StreamingEndpointType;
import com.sun.jersey.api.client.GenericType;

/**
 * Class for creating operations to manipulate Asset entities.
 * 
 */
public final class StreamingEndpoint {

    /** The Constant ENTITY_SET. */
    private static final String ENTITY_SET = "StreamingEndpoints";

    // Prevent instantiation
    /**
     * Instantiates a new asset.
     */
    private StreamingEndpoint() {
    }

    /**
     * Creates an Asset Creator.
     * 
     * @return the creator
     */
    public static Creator create() {
        return new Creator();
    }

    /**
     * The Class Creator.
     */
    public static class Creator extends
            EntityOperationSingleResultBase<StreamingEndpointInfo> implements
            EntityCreateOperation<StreamingEndpointInfo> {

        private String name;
        private String description;
        private int scaleUnits;
        private boolean cdnEnabled;
        private List<String> customHostNames;
        private StreamingEndpointAccessControlType streamingEndpointAccessControl;
        private StreamingEndpointCacheControlType streamingEndpointCacheControl;
        private CrossSiteAccessPoliciesType crossSiteAccessPolicies;

        /**
         * Instantiates a new creator.
         */
        public Creator() {
            super(ENTITY_SET, StreamingEndpointInfo.class);
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.microsoft.windowsazure.services.media.entityoperations.
         * EntityCreateOperation#getRequestContents()
         */
        @Override
        public Object getRequestContents() {
            StreamingEndpointType streamingEndpointType = new StreamingEndpointType();
            streamingEndpointType.setName(name);
            streamingEndpointType.setDescription(description);
            streamingEndpointType.setCdnEnabled(cdnEnabled);
            streamingEndpointType.setCustomHostName(customHostNames);
            streamingEndpointType.setCrossSiteAccessPolicies(crossSiteAccessPolicies);
            streamingEndpointType.setScaleUnits(scaleUnits);
            streamingEndpointType.setStreamingEndpointAccessControl(streamingEndpointAccessControl);
            streamingEndpointType.setStreamingEndpointCacheControl(streamingEndpointCacheControl);
            return streamingEndpointType;
        }

        /**
         * Set the name of the streaming endpoint to be created.
         * 
         * @param name
         *            The name
         * @return The creator object (for call chaining)
         */
        public Creator setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the description of the streaming endpoint to be created.
         * 
         * @param description
         *            The description
         * @return The creator object (for call chaining)
         */
        public Creator setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Set the scale units of the streaming endpoint to be created.
         * 
         * @param scaleUnits
         *            the scale units
         * @return The creator object (for call chaining)
         */
        public Creator setScaleUnits(int scaleUnits) {
            this.scaleUnits = scaleUnits;
            return this;
        }

        /**
         * Set if CDN is enabled on the streaming endpoint to be created.
         * 
         * @param cdnEnabled
         *            true if CDN is enabled
         * @return The creator object (for call chaining)
         */
        public Creator setCdnEnabled(boolean cdnEnabled) {
            this.cdnEnabled = cdnEnabled;
            return this;
        }

        /**
         * Set the access control policies of the streaming endpoint to be created.
         * 
         * @param streamingEndpointAccessControl
         *            the access control policies
         * @return The creator object (for call chaining)
         */
        public Creator setStreamingEndpointAccessControl(StreamingEndpointAccessControlType streamingEndpointAccessControl) {
            this.streamingEndpointAccessControl = streamingEndpointAccessControl;
            return this;
        }

        /**
         * Set the list of custom host names of the streaming endpoint to be created.
         * 
         * @param customHostNames
         *            the list of custom host names
         * @return The creator object (for call chaining)
         */
        public Creator setCustomHostNames(List<String> customHostNames) {
            this.customHostNames = customHostNames;
            return this;
        }

        /**
         * Set the streaming endpoint cache control of the streaming endpoint to be created.
         * 
         * @param streamingEndpointCacheControl
         *            the streaming endpoint cache control
         * @return The creator object (for call chaining)
         */
        public Creator setStreamingEndpointCacheControl(StreamingEndpointCacheControlType streamingEndpointCacheControl) {
            this.streamingEndpointCacheControl = streamingEndpointCacheControl;
            return this;
        }

        /**
         * Set the cross site access policies of the streaming endpoint to be created.
         * 
         * @param crossSiteAccessPolicies
         *            the cross site access policies
         * @return The creator object (for call chaining)
         */
        public Creator setSrossSiteAccessPolicies(CrossSiteAccessPoliciesType crossSiteAccessPolicies) {
            this.crossSiteAccessPolicies = crossSiteAccessPolicies;
            return this;
        }
        
    }

    /**
     * Create an operation object that will get the state of the given asset.
     * 
     * @param assetId
     *            id of asset to retrieve
     * @return the get operation
     */
    public static EntityGetOperation<StreamingEndpointInfo> get(String streamingEndpointId) {
        return new DefaultGetOperation<StreamingEndpointInfo>(ENTITY_SET, streamingEndpointId,
                StreamingEndpointInfo.class);
    }

    /**
     * Create an operation that will list all the assets.
     * 
     * @return The list operation
     */
    public static DefaultListOperation<StreamingEndpointInfo> list() {
        return new DefaultListOperation<StreamingEndpointInfo>(ENTITY_SET,
                new GenericType<ListResult<StreamingEndpointInfo>>() {
                });
    }

    /**
     * Create an operation that will update the given asset.
     * 
     * @param assetId
     *            id of the asset to update
     * @return the update operation
     */
    public static Updater update(String streamingEndpointId) {
        return new Updater(streamingEndpointId);
    }
    
    public static Updater update(StreamingEndpointInfo streamingEndpointInfo) {
        return new Updater(streamingEndpointInfo);
    }

    /**
     * The Class Updater.
     */
    public static class Updater extends EntityOperationBase implements
            EntityUpdateOperation {

        private String description = null;
        private boolean cdnEnabled;
        private List<String> customHostNames  = null;
        private StreamingEndpointAccessControlType streamingEndpointAccessControl  = null;
        private StreamingEndpointCacheControlType streamingEndpointCacheControl = null;
        private CrossSiteAccessPoliciesType crossSiteAccessPolicies = null;

        /**
         * Instantiates a new updater.
         * 
         * @param assetId
         *            the asset id
         */
        protected Updater(String streamingEndpointId) {
            super(new EntityOperationBase.EntityIdUriBuilder(ENTITY_SET,
                    streamingEndpointId));
        }
        
        protected Updater(StreamingEndpointInfo streamingEndpointInfo) {
            super(new EntityOperationBase.EntityIdUriBuilder(ENTITY_SET,
                    streamingEndpointInfo.getId()));
            this.setCdnEnabled(streamingEndpointInfo.isCdnEnabled());
            this.setCustomHostNames(streamingEndpointInfo.getCustomHostNames());
            this.setDescription(streamingEndpointInfo.getDescription());
            this.setSrossSiteAccessPolicies(streamingEndpointInfo.getCrossSiteAccessPolicies());
            this.setStreamingEndpointAccessControl(streamingEndpointInfo.getStreamingEndpointAccessControl());
            this.setStreamingEndpointCacheControl(streamingEndpointInfo.getStreamingEndpointCacheControl());
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.microsoft.windowsazure.services.media.entityoperations.
         * EntityOperation
         * #setProxyData(com.microsoft.windowsazure.services.media
         * .entityoperations.EntityProxyData)
         */
        @Override
        public void setProxyData(EntityProxyData proxyData) {
            // Deliberately empty
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.microsoft.windowsazure.services.media.entityoperations.
         * EntityUpdateOperation#getRequestContents()
         */
        @Override
        public Object getRequestContents() {
            StreamingEndpointType streamingEndpointType = new StreamingEndpointType();
            streamingEndpointType.setDescription(description);
            streamingEndpointType.setCdnEnabled(cdnEnabled);
            streamingEndpointType.setCustomHostName(customHostNames);
            streamingEndpointType.setCrossSiteAccessPolicies(crossSiteAccessPolicies);
            streamingEndpointType.setStreamingEndpointAccessControl(streamingEndpointAccessControl);
            streamingEndpointType.setStreamingEndpointCacheControl(streamingEndpointCacheControl);
            return streamingEndpointType;
        }

        /**
         * Set the new description of the streaming endpoint to be updated.
         * 
         * @param description
         *            The description
         * @return The creator object (for call chaining)
         */
        public Updater setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Set the new value for CDN enabled on the streaming endpoint to be updated.
         * 
         * @param cdnEnabled
         *            true if CDN is enabled
         * @return The creator object (for call chaining)
         */
        public Updater setCdnEnabled(boolean cdnEnabled) {
            this.cdnEnabled = cdnEnabled;
            return this;
        }

        /**
         * Set the new access control policies of the streaming endpoint to be updated.
         * 
         * @param streamingEndpointAccessControl
         *            the access control policies
         * @return The creator object (for call chaining)
         */
        public Updater setStreamingEndpointAccessControl(StreamingEndpointAccessControlType streamingEndpointAccessControl) {
            this.streamingEndpointAccessControl = streamingEndpointAccessControl;
            return this;
        }

        /**
         * Set the new list of custom host names of the streaming endpoint to be updated.
         * 
         * @param customHostNames
         *            the list of custom host names
         * @return The creator object (for call chaining)
         */
        public Updater setCustomHostNames(List<String> customHostNames) {
            this.customHostNames = customHostNames;
            return this;
        }

        /**
         * Set the new streaming endpoint cache control of the streaming endpoint to be updated.
         * 
         * @param streamingEndpointCacheControl
         *            the streaming endpoint cache control
         * @return The creator object (for call chaining)
         */
        public Updater setStreamingEndpointCacheControl(StreamingEndpointCacheControlType streamingEndpointCacheControl) {
            this.streamingEndpointCacheControl = streamingEndpointCacheControl;
            return this;
        }

        /**
         * Set the new cross site access policies of the streaming endpoint to be updated.
         * 
         * @param crossSiteAccessPolicies
         *            the cross site access policies
         * @return The creator object (for call chaining)
         */
        public Updater setSrossSiteAccessPolicies(CrossSiteAccessPoliciesType crossSiteAccessPolicies) {
            this.crossSiteAccessPolicies = crossSiteAccessPolicies;
            return this;
        }
    }

    /**
     * Create an operation to delete the given streaming endpoint
     * 
     * @param assetId
     *            id of asset to delete
     * @return the delete operation
     */
    public static EntityDeleteOperation delete(String streamingEndpointId) {
        return new DefaultDeleteOperation(ENTITY_SET, streamingEndpointId);
    }

    public static EntityActionOperation start(String streamingEndpointId) {
        return new DefaultEntityActionOperation(ENTITY_SET, streamingEndpointId, "Start");
    }
    
    public static EntityActionOperation stop(String streamingEndpointId) {
        return new DefaultEntityActionOperation(ENTITY_SET, streamingEndpointId, "Stop");
    }
    
    public static EntityActionOperation scale(String streamingEndpointId, int scaleUnits) {
        DefaultEntityActionOperation operation = new DefaultEntityActionOperation(ENTITY_SET, streamingEndpointId, "Scale");
        operation.addBodyParameter("scaleUnits", scaleUnits);
        return operation;
    }
}
