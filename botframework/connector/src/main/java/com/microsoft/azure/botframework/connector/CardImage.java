/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.botframework.connector;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An image on a card.
 */
public class CardImage {
    /**
     * URL Thumbnail image for major content property.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * Image description intended for screen readers.
     */
    @JsonProperty(value = "alt")
    private String alt;

    /**
     * Action assigned to specific Attachment. E.g.navigate to specific URL or
     * play/open media content.
     */
    @JsonProperty(value = "tap")
    private CardAction tap;

    /**
     * Get the url value.
     *
     * @return the url value
     */
    public String url() {
        return this.url;
    }

    /**
     * Set the url value.
     *
     * @param url the url value to set
     * @return the CardImage object itself.
     */
    public CardImage withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the alt value.
     *
     * @return the alt value
     */
    public String alt() {
        return this.alt;
    }

    /**
     * Set the alt value.
     *
     * @param alt the alt value to set
     * @return the CardImage object itself.
     */
    public CardImage withAlt(String alt) {
        this.alt = alt;
        return this;
    }

    /**
     * Get the tap value.
     *
     * @return the tap value
     */
    public CardAction tap() {
        return this.tap;
    }

    /**
     * Set the tap value.
     *
     * @param tap the tap value to set
     * @return the CardImage object itself.
     */
    public CardImage withTap(CardAction tap) {
        this.tap = tap;
        return this;
    }

}
