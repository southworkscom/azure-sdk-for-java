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

package com.microsoft.azure.management.sql.models;

/**
* Represents the properties of an Azure SQL Database secure connection policy.
*/
public class DatabaseSecureConnectionPolicyProperties {
    private String proxyDnsName;
    
    /**
    * Optional. Gets the Data Security Proxy DNS name.
    * @return The ProxyDnsName value.
    */
    public String getProxyDnsName() {
        return this.proxyDnsName;
    }
    
    /**
    * Optional. Gets the Data Security Proxy DNS name.
    * @param proxyDnsNameValue The ProxyDnsName value.
    */
    public void setProxyDnsName(final String proxyDnsNameValue) {
        this.proxyDnsName = proxyDnsNameValue;
    }
    
    private String proxyPort;
    
    /**
    * Optional. Gets the Data Security Proxy port number.
    * @return The ProxyPort value.
    */
    public String getProxyPort() {
        return this.proxyPort;
    }
    
    /**
    * Optional. Gets the Data Security Proxy port number.
    * @param proxyPortValue The ProxyPort value.
    */
    public void setProxyPort(final String proxyPortValue) {
        this.proxyPort = proxyPortValue;
    }
    
    private String securityEnabledAccess;
    
    /**
    * Optional. Gets whether block direct access is applied.
    * @return The SecurityEnabledAccess value.
    */
    public String getSecurityEnabledAccess() {
        return this.securityEnabledAccess;
    }
    
    /**
    * Optional. Gets whether block direct access is applied.
    * @param securityEnabledAccessValue The SecurityEnabledAccess value.
    */
    public void setSecurityEnabledAccess(final String securityEnabledAccessValue) {
        this.securityEnabledAccess = securityEnabledAccessValue;
    }
}
