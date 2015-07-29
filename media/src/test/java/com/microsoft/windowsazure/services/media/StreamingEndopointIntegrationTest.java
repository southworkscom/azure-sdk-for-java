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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.microsoft.windowsazure.services.media.models.ListResult;
import com.microsoft.windowsazure.services.media.models.OperationState;
import com.microsoft.windowsazure.services.media.models.StreamingEndpoint;
import com.microsoft.windowsazure.services.media.models.StreamingEndpointInfo;
import com.microsoft.windowsazure.services.media.models.StreamingEndpointState;

public class StreamingEndopointIntegrationTest extends IntegrationTestBase {

    @Test
    public void streamingEndpointCreateListByNameAndDelete() throws Exception {
        // Arrange
        String expectedName = testStreamingEndPointPrefix + "ListByNameTest";
        StreamingEndpointInfo streamingEndpointInfo = service.create(StreamingEndpoint.create().setName(expectedName));
        
        OperationUtils.await(service, streamingEndpointInfo);
        
        // Act
        ListResult<StreamingEndpointInfo> listStreamingEndpointResult = service.list(StreamingEndpoint.list()
                .set("$filter", "(Name eq '" + expectedName + "')"));

        // Assert
        assertNotNull(listStreamingEndpointResult);
        assertEquals(1, listStreamingEndpointResult.size());
        StreamingEndpointInfo info = listStreamingEndpointResult.get(0);
        assertNotNull(info);
        assertEquals(info.getName(), expectedName);
        
        // Cleanup
        String deleteOpId = service.delete(StreamingEndpoint.delete(info.getId()));
        OperationUtils.await(service, deleteOpId);
    }
    
    @Test
    public void streamingEndpointCreateStartStopDeleteTest() throws Exception {
        // Arrange
        String expectedName = testStreamingEndPointPrefix + "Startable";
        StreamingEndpointInfo streamingEndpointInfo = service.create(StreamingEndpoint.create().setName(expectedName));
        
        OperationUtils.await(service, streamingEndpointInfo);
        
        // Act
        String startingOpId = service.action(StreamingEndpoint.start(streamingEndpointInfo.getId()));
        OperationState state = OperationUtils.await(service, startingOpId);
        
        // Assert
        assertEquals(state, OperationState.Succeeded);        
        streamingEndpointInfo = service.get(StreamingEndpoint.get(streamingEndpointInfo.getId()));
        assertNotNull(streamingEndpointInfo);        
        assertEquals(StreamingEndpointState.Running, streamingEndpointInfo.getState());
        
        // Act 2
        startingOpId = service.action(StreamingEndpoint.stop(streamingEndpointInfo.getId())); 
        state = OperationUtils.await(service, startingOpId);
        
        // Assert 2
        assertEquals(state, OperationState.Succeeded);
        
        // Cleanup
        String deleteOpId = service.delete(StreamingEndpoint.delete(streamingEndpointInfo.getId()));
        state = OperationUtils.await(service, deleteOpId);
        // Assert Cleanup
        assertEquals(state, OperationState.Succeeded);
    }
    
    
    @Test
    public void streamingEndpointCreateStartScaleStopDeleteTest() throws Exception {
        // Arrange
        int expectedScaleUnits = 2;
        String expectedName = testStreamingEndPointPrefix + "Scalable";
        StreamingEndpointInfo streamingEndpointInfo = service.create(StreamingEndpoint.create().setName(expectedName));
        
        OperationUtils.await(service, streamingEndpointInfo);
        
        // Act
        String startingOpId = service.action(StreamingEndpoint.start(streamingEndpointInfo.getId()));
        OperationState state = OperationUtils.await(service, startingOpId);
        
        // Assert
        assertEquals(state, OperationState.Succeeded);        
        streamingEndpointInfo = service.get(StreamingEndpoint.get(streamingEndpointInfo.getId()));
        assertNotNull(streamingEndpointInfo);        
        assertEquals(StreamingEndpointState.Running, streamingEndpointInfo.getState());
        
        startingOpId = service.action(StreamingEndpoint.scale(streamingEndpointInfo.getId(), expectedScaleUnits)); 
        state = OperationUtils.await(service, startingOpId);
        // Assert 3
        assertEquals(state, OperationState.Succeeded);
        streamingEndpointInfo = service.get(StreamingEndpoint.get(streamingEndpointInfo.getId()));
        assertNotNull(streamingEndpointInfo);
        assertEquals(expectedScaleUnits, streamingEndpointInfo.getScaleUnits());
        
        // Act 3
        startingOpId = service.action(StreamingEndpoint.stop(streamingEndpointInfo.getId())); 
        state = OperationUtils.await(service, startingOpId);
        // Assert 3
        assertEquals(state, OperationState.Succeeded);
        
        // Cleanup
        String deleteOpId = service.delete(StreamingEndpoint.delete(streamingEndpointInfo.getId()));
        state = OperationUtils.await(service, deleteOpId);
        // Assert Cleanup
        assertEquals(state, OperationState.Succeeded);
    }
    
    @Test
    public void streamingEndpointEnableCDNTest() throws Exception {
        // Arrange
        int expectedScaleUnits = 1;
        String expectedName = testStreamingEndPointPrefix + "EnableCDN";
        
        // Act 1
        StreamingEndpointInfo streamingEndpointInfo = service.create(StreamingEndpoint.create().setName(expectedName));
        OperationState state = OperationUtils.await(service, streamingEndpointInfo);
        // Assert 1
        assertEquals(state, OperationState.Succeeded); 
        
        // Act 2
        String opId = service.action(StreamingEndpoint.scale(streamingEndpointInfo.getId(), expectedScaleUnits));
        state = OperationUtils.await(service, opId);
        // Assert 2
        assertEquals(state, OperationState.Succeeded); 
        
        // Act 3
        opId = service.update(StreamingEndpoint.update(streamingEndpointInfo).setCdnEnabled(true));
        state = OperationUtils.await(service, opId);
        // Assert 3
        assertEquals(state, OperationState.Succeeded); 
        
        // Act 4
        streamingEndpointInfo = service.get(StreamingEndpoint.get(streamingEndpointInfo.getId()));
        // Assert 4
        assertTrue(streamingEndpointInfo.isCdnEnabled());
        
        // Cleanup
        String deleteOpId = service.delete(StreamingEndpoint.delete(streamingEndpointInfo.getId()));
        state = OperationUtils.await(service, deleteOpId);
        // Assert Cleanup
        assertEquals(state, OperationState.Succeeded);
    }
    
    

}
