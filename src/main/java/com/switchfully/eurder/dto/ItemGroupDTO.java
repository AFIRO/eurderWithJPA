package com.switchfully.eurder.dto;

import com.switchfully.eurder.entities.Item;

import java.time.LocalDate;

public class ItemGroupDTO {
    private ItemDTO item;
    private int amountToOrder;
    private double costForItemGroup;
    private LocalDate shippingDate;

    public ItemGroupDTO setItem(ItemDTO item) {
        this.item = item;
        return this;
    }

    public ItemGroupDTO setAmountToOrder(int amountToOrder) {
        this.amountToOrder = amountToOrder;
        return this;
    }

    public ItemGroupDTO setCostForItemGroup(double costForItemGroup) {
        this.costForItemGroup = costForItemGroup;
        return this;
    }

    public ItemGroupDTO setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public ItemDTO getItem() {
        return item;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public double getCostForItemGroup() {
        return costForItemGroup;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
