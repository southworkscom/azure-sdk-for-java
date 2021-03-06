/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.network.models;

/**
* Parameters supplied to the set IP Forwarding operation.
*/
public class IPForwardingSetParameters {
    private String state;
    
    /**
    * Required. Gets or sets state of IPForwarding for a role or network
    * interface.Allowed values are "Enabled", "Disabled".
    * @return The State value.
    */
    public String getState() {
        return this.state;
    }
    
    /**
    * Required. Gets or sets state of IPForwarding for a role or network
    * interface.Allowed values are "Enabled", "Disabled".
    * @param stateValue The State value.
    */
    public void setState(final String stateValue) {
        this.state = stateValue;
    }
    
    /**
    * Initializes a new instance of the IPForwardingSetParameters class.
    *
    */
    public IPForwardingSetParameters() {
    }
    
    /**
    * Initializes a new instance of the IPForwardingSetParameters class with
    * required arguments.
    *
    * @param state Gets or sets state of IPForwarding for a role or network
    * interface.Allowed values are "Enabled", "Disabled".
    */
    public IPForwardingSetParameters(String state) {
        if (state == null) {
            throw new NullPointerException("state");
        }
        this.setState(state);
    }
}
