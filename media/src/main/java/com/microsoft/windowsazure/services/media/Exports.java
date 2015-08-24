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
package com.microsoft.windowsazure.services.media;

import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.microsoft.windowsazure.core.Builder;
import com.microsoft.windowsazure.core.UserAgentFilter;
import com.microsoft.windowsazure.services.media.implementation.DocumentBodyWriter;
import com.microsoft.windowsazure.services.media.implementation.MediaContentProvider;
import com.microsoft.windowsazure.services.media.implementation.MediaExceptionProcessor;
import com.microsoft.windowsazure.services.media.implementation.MediaRestProxy;
import com.microsoft.windowsazure.services.media.implementation.MultipartBodyWriter;
import com.microsoft.windowsazure.services.media.implementation.OAuthContract;
import com.microsoft.windowsazure.services.media.implementation.OAuthFilter;
import com.microsoft.windowsazure.services.media.implementation.OAuthRestProxy;
import com.microsoft.windowsazure.services.media.implementation.OAuthTokenManager;
import com.microsoft.windowsazure.services.media.implementation.ODataEntityCollectionProvider;
import com.microsoft.windowsazure.services.media.implementation.ODataEntityProvider;
import com.microsoft.windowsazure.services.media.implementation.RedirectFilter;
import com.microsoft.windowsazure.services.media.implementation.ResourceLocationManager;
import com.microsoft.windowsazure.services.media.implementation.SetMediaUriFilter;
import com.microsoft.windowsazure.services.media.implementation.VersionHeadersFilter;

public class Exports implements Builder.Exports {
    private static Object lock = new Object();
    private static ResourceLocationManager resourceLocationManager;

    /**
     * register the Media services.
     */
    @Override
    public void register(Builder.Registry registry) {
        registry.add(MediaContract.class, MediaExceptionProcessor.class);
        registry.add(MediaRestProxy.class);
        registry.add(OAuthContract.class, OAuthRestProxy.class);
        registry.add(OAuthTokenManager.class);
        registry.add(OAuthFilter.class);
        registry.add(RedirectFilter.class);
        registry.add(SetMediaUriFilter.class);
        registry.add(VersionHeadersFilter.class);
        registry.add(UserAgentFilter.class);
        
        // register singleton factory.
        registry.add(new Builder.Factory<ResourceLocationManager>() {
            @Override
            public <S> ResourceLocationManager create(String profile, Class<S> service,
                    Builder builder, Map<String, Object> properties) {
                
                synchronized (lock) {
                    if (resourceLocationManager == null) {
                        try {
                            resourceLocationManager = new ResourceLocationManager((String) properties.get(MediaConfiguration.URI));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                return resourceLocationManager;
            }
        });


        registry.alter(MediaContract.class, ClientConfig.class,
                new Builder.Alteration<ClientConfig>() {
                    @SuppressWarnings("rawtypes")
                    @Override 
                    public ClientConfig alter(String profile,
                            ClientConfig instance, Builder builder,
                            Map<String, Object> properties) {

                        instance.property(ClientProperties.FOLLOW_REDIRECTS, false);
                        try {
                            instance.register(new ODataEntityProvider());
                            instance.register(new ODataEntityCollectionProvider());
                            instance.register(new MediaContentProvider());
                            instance.register(new MultipartBodyWriter());
                            instance.register(new DocumentBodyWriter());
                        } catch (JAXBException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        return instance;
                    }
                });
    }
    
}
