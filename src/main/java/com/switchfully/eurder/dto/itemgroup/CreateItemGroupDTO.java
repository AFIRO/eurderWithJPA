package com.switchfully.eurder.dto.itemgroup;

public class CreateItemGroupDTO {
    private String itemId;
    private String amountToOrder;


    public String getItemId() {
        return itemId;
    }

    public CreateItemGroupDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getAmountToOrder() {
        return amountToOrder;
    }

    public CreateItemGroupDTO setAmountToOrder(String amountToOrder) {
        this.amountToOrder = amountToOrder;
        return this;
    }
}
