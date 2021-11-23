package com.switchfully.eurder.dto.itemgroup;

public class CreateItemGroupDTO {
    private Integer itemId;
    private int amountToOrder;


    public Integer getItemId() {
        return itemId;
    }

    public CreateItemGroupDTO setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }



}
