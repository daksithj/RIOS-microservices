package com.ds.rios.retailshopservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResponse {
    @JsonProperty("_embedded")
    private ItemEmbedded embedded;

    public ItemEmbedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(ItemEmbedded embedded) {
        this.embedded = embedded;
    }
}
