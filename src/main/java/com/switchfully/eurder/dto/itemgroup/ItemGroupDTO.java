package com.switchfully.eurder.dto.itemgroup;

import com.switchfully.eurder.dto.item.ItemDTO;

import java.time.LocalDate;

public class ItemGroupDTO {
    private ItemDTO item;
    private int amountToOrder;
    private double costForItemGroup;
    private LocalDate shippingDate;

    public ItemDTO getItem() {
        return item;
    }

    public ItemGroupDTO setItem(ItemDTO item) {
        this.item = item;
        return this;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public ItemGroupDTO setAmountToOrder(int amountToOrder) {
        this.amountToOrder = amountToOrder;
        return this;
    }

    public double getCostForItemGroup() {
        return costForItemGroup;
    }

    public ItemGroupDTO setCostForItemGroup(double costForItemGroup) {
        this.costForItemGroup = costForItemGroup;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public ItemGroupDTO setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }
}
