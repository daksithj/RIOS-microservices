package com.ds.rios.retailshopservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {
    @JsonProperty("_embedded")
    private OrderEmbedded orderEmbedded;

    public OrderEmbedded getOrderEmbedded() {
        return orderEmbedded;
    }

    public void setOrderEmbedded(OrderEmbedded orderEmbedded) {
        this.orderEmbedded = orderEmbedded;
    }
}
