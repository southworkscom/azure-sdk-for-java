/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator 0.17.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.keyvault.models;

import com.microsoft.azure.keyvault.webkey.JsonWebKeyEncryptionAlgorithm;
import com.microsoft.rest.Base64Url;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The key operations parameters.
 */
public class KeyOperationsParameters {
    /**
     * algorithm identifier. Possible values include: 'RSA-OAEP', 'RSA1_5'.
     */
    @JsonProperty(value = "alg", required = true)
    private JsonWebKeyEncryptionAlgorithm algorithm;

    /**
     * The value property.
     */
    @JsonProperty(required = true)
    private Base64Url value;

    /**
     * Get the algorithm value.
     *
     * @return the algorithm value
     */
    public JsonWebKeyEncryptionAlgorithm algorithm() {
        return this.algorithm;
    }

    /**
     * Set the algorithm value.
     *
     * @param algorithm the algorithm value to set
     * @return the KeyOperationsParameters object itself.
     */
    public KeyOperationsParameters withAlgorithm(JsonWebKeyEncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    /**
     * Get the value value.
     *
     * @return the value value
     */
    public byte[] value() {
        if (this.value == null) {
            return null;
        }
        return this.value.getDecodedBytes();
    }

    /**
     * Set the value value.
     *
     * @param value the value value to set
     * @return the KeyOperationsParameters object itself.
     */
    public KeyOperationsParameters withValue(byte[] value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = Base64Url.encode(value);
        }
        return this;
    }

}