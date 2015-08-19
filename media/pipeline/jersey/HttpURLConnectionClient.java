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

import javax.net.ssl.SSLContext;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;

//import    ;
//import com.sun.jersey.api.client.config.ClientConfig;

public class HttpURLConnectionClient extends JerseyClient {
    private final HttpURLConnectionClientHandler rootHandler;

    public HttpURLConnectionClient(HttpURLConnectionClientHandler handler,
            ClientConfig config) {
        super(config, (SSLContext) null, null);
        this.rootHandler = handler;
    }

    public static HttpURLConnectionClient create(ClientConfig config) {
        return new HttpURLConnectionClient(new HttpURLConnectionClientHandler(
                config), config);
    }

    public HttpURLConnectionClientHandler getRootHandler() {
        return rootHandler;
    }
}
