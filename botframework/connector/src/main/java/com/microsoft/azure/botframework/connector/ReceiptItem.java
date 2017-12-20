/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.botframework.connector;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An item on a receipt card.
 */
public class ReceiptItem {
    /**
     * Title of the line item.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * Subtitle to be displayed under the line item’s title.
     */
    @JsonProperty(value = "subtitle")
    private String subtitle;

    /**
     * Description of the line item.
     */
    @JsonProperty(value = "text")
    private String text;

    /**
     * Thumbnail image to display next to the line item.
     */
    @JsonProperty(value = "image")
    private CardImage image;

    /**
     * A currency-formatted string that specifies the total price of all units
     * purchased.
     */
    @JsonProperty(value = "price")
    private String price;

    /**
     * A numeric string that specifies the number of units purchased.
     */
    @JsonProperty(value = "quantity")
    private String quantity;

    /**
     * This action will be activated when user taps on the Item bubble.
     */
    @JsonProperty(value = "tap")
    private CardAction tap;

    /**
     * Get the title value.
     *
     * @return the title value
     */
    public String title() {
        return this.title;
    }

    /**
     * Set the title value.
     *
     * @param title the title value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the subtitle value.
     *
     * @return the subtitle value
     */
    public String subtitle() {
        return this.subtitle;
    }

    /**
     * Set the subtitle value.
     *
     * @param subtitle the subtitle value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    /**
     * Get the text value.
     *
     * @return the text value
     */
    public String text() {
        return this.text;
    }

    /**
     * Set the text value.
     *
     * @param text the text value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get the image value.
     *
     * @return the image value
     */
    public CardImage image() {
        return this.image;
    }

    /**
     * Set the image value.
     *
     * @param image the image value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withImage(CardImage image) {
        this.image = image;
        return this;
    }

    /**
     * Get the price value.
     *
     * @return the price value
     */
    public String price() {
        return this.price;
    }

    /**
     * Set the price value.
     *
     * @param price the price value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withPrice(String price) {
        this.price = price;
        return this;
    }

    /**
     * Get the quantity value.
     *
     * @return the quantity value
     */
    public String quantity() {
        return this.quantity;
    }

    /**
     * Set the quantity value.
     *
     * @param quantity the quantity value to set
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withQuantity(String quantity) {
        this.quantity = quantity;
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
     * @return the ReceiptItem object itself.
     */
    public ReceiptItem withTap(CardAction tap) {
        this.tap = tap;
        return this;
    }

}
