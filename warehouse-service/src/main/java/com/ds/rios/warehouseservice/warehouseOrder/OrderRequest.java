package com.ds.rios.warehouseservice.warehouseOrder;

import java.util.List;

public class OrderRequest {
    private Long retailId;
    private List<Item> items;

    protected OrderRequest(){}

    public OrderRequest(Long retailId, List<Item> items) {
        this.retailId = retailId;
        this.items = items;
    }

    public Long getRetailId() {
        return retailId;
    }

    public void setRetailId(Long retailId) {
        this.retailId = retailId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private Long itemId;
        private int quantity;

        protected Item(){}

        public Item(Long itemId, int quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
