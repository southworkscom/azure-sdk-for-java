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

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Class used for injecting settings into the various places that need it.
 * 
 */
public class ClientConfigSettings {
    private static final int DEFAULT_TIMEOUT_MS = 90 * 1000;

    private final Integer connectTimeout;
    private final Integer readTimeout;
    private final boolean shouldLog;

    /**
     * Construct a {@link ClientConfigSettings} object with the default
     * settings.
     */
    public ClientConfigSettings() {
        connectTimeout = Integer.valueOf(null);
        readTimeout = Integer.valueOf(null);
        shouldLog = false;
    }

    /**
     * Construct a {@link ClientConfigSettings} object wit the given settings.
     * 
     * @param connectTimeout
     *            Connection timeout in milliseconds
     * @param readTimeout
     *            read timeout in milliseconds
     * @param shouldLog
     *            if true, add logging filter to clients.
     */
    public ClientConfigSettings(Object connectTimeout, Object readTimeout,
            boolean shouldLog) {
        this.connectTimeout = getTimeout(connectTimeout);
        this.readTimeout = getTimeout(readTimeout);
        this.shouldLog = shouldLog;
    }

    /**
     * Update the given {@link ClientConfig} object with the appropriate
     * settings from configuration.
     * 
     * @param clientConfig
     *            object to update.
     */
    public void applyConfig(ClientConfig clientConfig) {
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
        clientConfig.property(ClientProperties.READ_TIMEOUT, readTimeout);
    }

    /**
     * Update the given client object with the appropriate settings from
     * configuration.
     * 
     * @param client The client.
     */
    public void applyConfig(Client client) {
        if (shouldLog) {
            client.register(new LoggingFilter());
        }        
    }

    private Integer getTimeout(Object timeoutValue) {
        if (timeoutValue == null) {
            return new Integer(DEFAULT_TIMEOUT_MS);
        }

        if (timeoutValue instanceof Integer) {
            return (Integer) timeoutValue;
        }

        if (timeoutValue instanceof String) {
            return Integer.valueOf((String) timeoutValue);
        }

        throw new IllegalArgumentException("timeoutValue");
    }
}
